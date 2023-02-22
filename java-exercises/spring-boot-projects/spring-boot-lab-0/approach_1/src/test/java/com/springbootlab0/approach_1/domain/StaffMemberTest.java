package com.springbootlab0.approach_1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.springbootlab0.approach_1.utils.FakeDataGenerator;

import static org.junit.jupiter.api.Assertions.*;

class StaffMemberTest {

    StaffMember fakeStaffMember;
    LibraryMember fakeLibraryMember;
    Publication fakeItem;
    @BeforeEach
    void setUp() {
        // Fake members
        this.fakeStaffMember = FakeDataGenerator.createFakeLibrarian();
        this.fakeLibraryMember = FakeDataGenerator.createFakeUser();
        // Fake publications
        this.fakeItem = FakeDataGenerator.createFakeBook();
    }

    @Test
    void addPublication() {
        this.fakeStaffMember.addPublication(this.fakeItem);
        fail("Pending to implement");
    }

    @Test
    void removePublication() {
        this.fakeStaffMember.removePublication(this.fakeItem);
        fail("Pending to implement");
    }

    @Test
    void updatePublication() {
        this.fakeStaffMember.updatePublication(this.fakeItem);
        fail("Pending to implement");
    }

    @Test
    void registerMember() {
        this.fakeStaffMember.registerMember(this.fakeLibraryMember);
        fail("Pending to implement");
    }

    @Test
    void removeMember() {
        this.fakeStaffMember.removeMember(this.fakeLibraryMember);
        fail("Pending to implement");
    }

    @Test
    void updateMember() {
        this.fakeStaffMember.updateMember(this.fakeLibraryMember);
        fail("Pending to implement");
    }

    @Test
    void generateReports() {
        this.fakeStaffMember.generateReports();
        fail("Pending to implement");
    }
}