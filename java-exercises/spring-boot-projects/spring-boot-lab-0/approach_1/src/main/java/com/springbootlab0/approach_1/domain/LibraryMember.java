package com.springbootlab0.approach_1.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import com.springbootlab0.approach_1.utils.Helper;

import java.time.LocalDate;

@Getter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@Entity(name="LibraryMember")
@Table(name="LIBRARYMEMBER_TABLE")
abstract class LibraryMember extends Person implements GeneralOperations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="LIBRARYMEMBER_ID")
    private String id;
    @Column(name="LIBRARYMEMBER_ADDRESS")
    private String address;
    @Column(name="LIBRARYMEMBER_PHONENUMBER")
    private String phoneNumber;

    public LibraryMember() {
        super();
        this.id = Helper.createUUID();
    }

    public LibraryMember(String firstName, String lastName, String nationality, LocalDate birthDate, String address, String phoneNumber) {
        super(firstName, lastName, nationality, birthDate);
        this.id = Helper.createUUID();
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void createAccount() {
        System.out.println("Creating account...");
    }

    @Override
    public void searchPublications(String query) {
        System.out.println("Searching publications...");
    }
}
