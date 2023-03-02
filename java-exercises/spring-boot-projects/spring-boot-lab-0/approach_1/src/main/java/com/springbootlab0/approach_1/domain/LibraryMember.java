package com.springbootlab0.approach_1.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import com.springbootlab0.approach_1.utils.Helper;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

// Lombok annotations
@Getter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
// JPA Annotations
@Entity
@Table(name="LIBRARYMEMBER_TABLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MEMBER_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class LibraryMember extends Person implements GeneralOperations {
    @Id
    //@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="LIBRARYMEMBER_ID", updatable = false, nullable = false)
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
        System.out.println("[" + this.getClass().getSimpleName() + "] Creating account...");
    }

    @Override
    public void searchPublications(String query) {
        System.out.println("[" + this.getClass().getSimpleName() + "] Searching publications...");
    }
}
