package com.springbootlab0.approach_1.services;

import com.springbootlab0.approach_1.domain.*;
import com.springbootlab0.approach_1.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {
    @Autowired
    BorrowRepository borrowRepository;
    @Autowired
    LibraryMemberService libraryMemberService;
    @Autowired
    PublicationService publicationService;

    // CRUD
    // - Get all borrows
    /**
     * Return all borrows from the database
     * @return all borrows as an Iterable
     */
    public Iterable<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    // - Get a borrow by its ID
    public Borrow getBorrowById(String id) {
        if (borrowRepository.existsById(id)) {
            Optional<Borrow> foundBorrow = borrowRepository.findById(id);
            if (foundBorrow.isPresent()) {
                return foundBorrow.get();
            }
        }
        return null;
    }

    // - Create a borrow or multiple borrows at once
    /**
     * Create a user's borrow with the given publication
     * @param borrowUser is the user that makes the borrow
     * @param borrowedPublication is the publication to be borrowed
     * @return a copy of the created borrow
     */
    public Borrow createBorrow(LibraryMember borrowUser, Publication borrowedPublication) {
        // TODO: Check that the user can still make borrows and that the publication is available
        // Create a new borrow
        Borrow createdBorrow = new Borrow(borrowUser, borrowedPublication);
        // Save the borrow
        // TODO: Check that the borrow has been correctly saved into the DB
        borrowRepository.save(createdBorrow);
        return createdBorrow;
    }

    /**
     * Create one borrow for each received publication ID
     * @param userId is the User ID that makes the borrows
     * @param publicationsToBorrow is a list of publication IDs to borrow
     * @return the list of created borrows
     */
    public List<Borrow> createMultipleBorrows(String userId, Iterable<String> publicationsToBorrow) {
        // Initialize an empty list of created borrows
        List<Borrow> createdBorrows = new ArrayList<>();
        // Retrieve the user information based on the received ID
        Optional<LibraryMember> borrowUser = libraryMemberService.findLibraryMemberById(userId);
        if (borrowUser.isPresent()) {
            // For each Publication ID, create a borrow
            for (String publicationId : publicationsToBorrow) {
                // Retrieve the publication
                Optional<Publication> publicationToBorrow = publicationService.findPublicationById(publicationId);
                if (publicationToBorrow.isPresent()) {
                    // Create the borrow
                    createdBorrows.add(createBorrow(borrowUser.get(), publicationToBorrow.get()));
                }
            }
        }
        // Return the list of created borrows
        return createdBorrows;
    }

    public boolean deleteBorrowById(String borrowId) {
        // TODO: change the Publication status to available again
        if (borrowRepository.existsById(borrowId)) {
            Optional<Borrow> borrowToDelete = borrowRepository.findById(borrowId);
            if (borrowToDelete.isPresent()) {
                // Change the Publication status
                Publication publication = borrowToDelete.get().getBorrowedPublication();
                publication.setStatus(Status.AVAILABLE);
                // Delete the borrow
                borrowRepository.deleteById(borrowId);
                return true;
            }

        }
        return false;
    }

    public Borrow deleteAndReturnBorrowById(String borrowId) {
        // TODO: change the Publication status to available again
        if (borrowRepository.existsById(borrowId)) {
            Optional<Borrow> borrowToDelete = borrowRepository.findById(borrowId);
            if (borrowToDelete.isPresent()) {
                // Change the Publication status
                Publication publication = borrowToDelete.get().getBorrowedPublication();
                publication.setStatus(Status.AVAILABLE);
                // Delete the borrow
                borrowRepository.deleteById(borrowId);
                return borrowToDelete.get();
            }
        }
        return null;
    }

    /**
     * Update a Borrow into the database
     * @param borrowToUpdate is the Borrow with the updated fields
     * @return a boolean value depending on the update results
     */
    public boolean updateBorrow(Borrow borrowToUpdate) {
        if (borrowRepository.existsById(borrowToUpdate.getId())) {
            // We'll update fields from the DB entity if they are different
            Borrow borrowFromDB = borrowRepository.findById(borrowToUpdate.getId()).get();
            // Check if borrow status has changed
            if (!borrowToUpdate.getBorrowStatus().equals(borrowFromDB.getBorrowStatus())) {
                // Check if Borrow has been closed
                switch (borrowToUpdate.getBorrowStatus()) {
                    case CLOSED -> {
                        // Set the Publication status to available
                        borrowFromDB.getBorrowedPublication().setStatus(Status.AVAILABLE);
                        publicationService.updatePublication(borrowToUpdate.getBorrowedPublication());
                        // Set the return date to the current date
                        borrowFromDB.setReturnedBorrowDate(LocalDate.now());
                    }
                    case LATE -> {
                        // TODO: define what to do when it is LATE
                    }
                    case IN_PROGRESS -> {
                        // TODO: define the case scenarios where a borrow changes to IN_PROGRESS again
                        // Set the publication status to BORROWED
                        if (!borrowFromDB.getBorrowedPublication().getStatus().equals(Status.BORROWED)) {
                            borrowFromDB.getBorrowedPublication().setStatus(Status.BORROWED);
                            // Update the dueBorrowDate 15 days from now
                            borrowFromDB.setDueBorrowDate(LocalDate.now().plusDays(15));
                            // Restart the return date
                            borrowFromDB.setReturnedBorrowDate(null);
                        } else {
                            // Can't change a borrow status from closed/lost to in_progress if its publication is already borrowed!!
                            return false;
                        }
                    }


                    default -> {
                        // Unknown borrow status!
                        return false;
                    }
                }
                // Update the status field in the DB entity
                borrowFromDB.setBorrowStatus(borrowToUpdate.getBorrowStatus());
            }
            // TODO: Check the rest of the updatable fields
            // Update the Borrow into the database
            borrowRepository.save(borrowFromDB);
            return true;
        }
        return false;
    }

    /**
     * Changes the Borrow status to CLOSED
     * @param borrowId
     * @return
     */
    public Borrow returnBorrowByID(String borrowId) {
        Optional<Borrow> borrowFromDB = borrowRepository.findById(borrowId);
        if (borrowFromDB.isPresent()) {
            Borrow currentBorrow = borrowFromDB.get();
            // TODO: create a deep copy of the borrow from DB
            Borrow borrowCopy = Borrow.createCopy(currentBorrow);
            // Change the borrow status to CLOSED
            borrowCopy.setBorrowStatus(BorrowStatus.CLOSED);
            // Update the borrow in the DB
            if (updateBorrow(borrowCopy)) {
                return borrowCopy;
            } else {
                // Return null if something went wrong on the update process
                return null;
            }
        }
        // Return null if the borrow is not present in the DB
        return null;
    }
}
