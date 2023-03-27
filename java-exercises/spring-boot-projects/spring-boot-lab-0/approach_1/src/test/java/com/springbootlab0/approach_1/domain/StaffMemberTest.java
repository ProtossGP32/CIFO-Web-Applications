package com.springbootlab0.approach_1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
    @Disabled("Still no use-case for this")
    void addPublication() {
        this.fakeStaffMember.addPublication(this.fakeItem);
        fail("Pending to implement");
    }

    @Test
    @Disabled("Still no use-case for this")
    void removePublication() {
        this.fakeStaffMember.removePublication(this.fakeItem);
        fail("Pending to implement");
    }

    @Test
    @Disabled("Still no use-case for this")
    void updatePublication() {
        this.fakeStaffMember.updatePublication(this.fakeItem);
        fail("Pending to implement");
    }

    @Test
    @Disabled("Still no use-case for this")
    void registerMember() {
        this.fakeStaffMember.registerMember(this.fakeLibraryMember);
        fail("Pending to implement");
    }

    @Test
    @Disabled("Still no use-case for this")
    void removeMember() {
        this.fakeStaffMember.removeMember(this.fakeLibraryMember);
        fail("Pending to implement");
    }

    @Test
    @Disabled("Still no use-case for this")
    void updateMember() {
        this.fakeStaffMember.updateMember(this.fakeLibraryMember);
        fail("Pending to implement");
    }

    @Test
    @Disabled("Still no use-case for this")
    void generateReports() {
        this.fakeStaffMember.generateReports();
        fail("Pending to implement");
    }
}