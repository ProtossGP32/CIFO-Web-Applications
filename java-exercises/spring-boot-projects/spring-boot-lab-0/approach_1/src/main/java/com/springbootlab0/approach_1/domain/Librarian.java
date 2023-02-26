package com.springbootlab0.approach_1.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@Entity(name = "Librarian")
@DiscriminatorValue(value = "LIBRARIAN")
public class Librarian extends StaffMember implements BorrowOperations {

    public Librarian() {
        super();
    }

    public Librarian(
            String firstName,
            String lastName,
            String nationality,
            LocalDate birthDate,
            String address,
            String phoneNumber,
            double salary
    ) {
        super(firstName, lastName, nationality, birthDate, address, phoneNumber, salary);
    }

    @Override
    public void createBorrow() {
        // Create a borrow by asking for user and book information
        System.out.println("A " + this.getClass().getSimpleName() + " is trying to create a borrow");
    }

    @Override
    public void findBorrow() {
        // Find a borrow by asking for user and book information
        System.out.println("A " + this.getClass().getSimpleName() + " is trying to find a borrow");
    }

    @Override
    public void removeBorrow() {
        // Remove a borrow by asking for user and book information
        System.out.println("A " + this.getClass().getSimpleName() + " is trying to remove a borrow");
    }

    @Override
    public void updateBorrow() {
        // Extend a borrow by asking for user and book information
        System.out.println("A " + this.getClass().getSimpleName() + " is trying to update a borrow");
    }

    @Override
    public void listBorrows() {
        // List all borrows by asking for user and book information
        System.out.println("A " + this.getClass().getSimpleName() + " is trying to list all borrows");
    }
}
