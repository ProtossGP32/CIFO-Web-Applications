package com.springbootlab0.approach_1.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
// JPA annotations
@MappedSuperclass
abstract class StaffMember extends LibraryMember implements StaffOperations {
    private double salary;

    protected StaffMember() {
        super();
    }

    protected StaffMember(
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StaffMember that)) return false;
        if (!super.equals(o)) return false;
        if (Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        return Double.compare(that.getSalary(), getSalary()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSalary());
    }
}
