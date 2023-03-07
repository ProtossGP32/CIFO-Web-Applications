package com.springbootlab0.approach_1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@ToString(callSuper = true)
// JPA annotations
@MappedSuperclass
abstract class StaffMember extends LibraryMember implements StaffOperations {
    private double salary;

    public StaffMember() {
        super();
    }

    public StaffMember(
            String firstName,
            String lastName,
            String nationality,
            LocalDate birthdate,
            String address,
            String phoneNumber,
            double salary
    ) {
        super(
                firstName,
                lastName,
                nationality,
                birthdate,
                address,
                phoneNumber
        );
        this.salary = salary;
    }

    @Override
    public void addPublication(Publication item) {
        System.out.println("Add publication");
    }

    @Override
    public void removePublication(Publication item) {
        System.out.println("Remove publication");
    }

    @Override
    public void updatePublication(Publication item) {
        System.out.println("Update publication");
    }

    @Override
    public void registerMember(LibraryMember user) {
        System.out.println("Register member");
    }

    @Override
    public void removeMember(LibraryMember user) {
        System.out.println("Remove member");
    }

    @Override
    public void updateMember(LibraryMember user) {

    }

    @Override
    public void generateReports() {

    }
}
