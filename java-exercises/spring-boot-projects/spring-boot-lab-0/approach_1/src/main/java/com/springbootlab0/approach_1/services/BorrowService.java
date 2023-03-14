package com.springbootlab0.approach_1.services;

import com.springbootlab0.approach_1.domain.Borrow;
import com.springbootlab0.approach_1.domain.LibraryMember;
import com.springbootlab0.approach_1.domain.Publication;
import com.springbootlab0.approach_1.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    /**
     * Return all borrows from the database
     * @return all borrows as an Iterable
     */
    public Iterable<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

}
