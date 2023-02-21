package org.labse03part2.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.labse03part2.utils.Helper;

import java.time.LocalDate;

@Getter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
abstract class LibraryMember extends Person implements GeneralOperations {

    private String id;
    private String address;
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
