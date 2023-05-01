package com.springbootlab0.approach_1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        if (Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        return getMail().equals(user.getMail()) && getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMail(), getPassword());
    }
}
