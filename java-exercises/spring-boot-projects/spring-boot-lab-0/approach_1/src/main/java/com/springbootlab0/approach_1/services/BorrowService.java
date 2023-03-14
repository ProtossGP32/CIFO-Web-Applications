package com.springbootlab0.approach_1.services;

import com.springbootlab0.approach_1.domain.Borrow;
import com.springbootlab0.approach_1.domain.LibraryMember;
import com.springbootlab0.approach_1.domain.Publication;
import com.springbootlab0.approach_1.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowService {
    @Autowired
    BorrowRepository borrowRepository;

    // CRUD

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
     * Return all borrows from the database
     * @return all borrows as an Iterable
     */
    public Iterable<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

}
