package com.springbootlab0.approach_1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@Entity(name = "User")
@DiscriminatorValue(value = "USER")
public class User extends LibraryMember implements BorrowOperations{

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "PASSWORD")
    private String password;

    public User() {
        super();
    }
    public User(String firstName, String lastName, String nationality, LocalDate birthDate, String address, String phoneNumber, String mail, String password) {
        super(firstName, lastName, nationality, birthDate, address, phoneNumber);
        this.mail = mail;
        this.password = password;
    }
    @Override
    public void createBorrow() {
        // Create a user's borrow just asking for the book information
    }

    @Override
    public void findBorrow() {
        // Find a user's borrow just asking for the book information
    }

    @Override
    public void removeBorrow() {
        // Remove a user's borrow just asking for the borrow information
    }

    @Override
    public void updateBorrow() {
        // Create a user's borrow just asking for the book information
    }

    @Override
    public void listBorrows() {
        // List all user's borrow
    }
}
