package com.springbootlab0.approach_1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.springbootlab0.approach_1.utils.FakeDataGenerator;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User fakeUser;

    @BeforeEach
    void setUp() {
        this.fakeUser = FakeDataGenerator.createFakeUser();
    }

    @Test
    void testNoArgsConstructorInitializesFieldsAsExpected() {
        this.fakeUser = new User();
        assertNotNull(this.fakeUser);
        // Only ID should be different from null
        assertNotNull(this.fakeUser.getId());
        assertEquals(UUID.fromString(this.fakeUser.getId()).toString(), this.fakeUser.getId());
        // The rest should be null
        assertNull(this.fakeUser.getFirstName());
        assertNull(this.fakeUser.getLastName());
        assertNull(this.fakeUser.getNationality());
        assertNull(this.fakeUser.getBirthDate());
        assertNull(this.fakeUser.getAddress());
        assertNull(this.fakeUser.getPhoneNumber());
        assertNull(this.fakeUser.getMail());
        assertNull(this.fakeUser.getPassword());
    }

    @Test
    void testAllArgsConstructorPopulatesAllFields() {
        assertNotNull(this.fakeUser);
        // Only ID should be different from null
        assertNotNull(this.fakeUser.getId());
        assertEquals(UUID.fromString(this.fakeUser.getId()).toString(), this.fakeUser.getId());
        // The rest should be null
        assertNotNull(this.fakeUser.getFirstName());
        assertNotNull(this.fakeUser.getLastName());
        assertNotNull(this.fakeUser.getNationality());
        assertNotNull(this.fakeUser.getBirthDate());
        assertNotNull(this.fakeUser.getAddress());
        assertNotNull(this.fakeUser.getPhoneNumber());
        assertNotNull(this.fakeUser.getMail());
        assertNotNull(this.fakeUser.getPassword());
    }

    @Test
    @Disabled
    void createBorrow() {
        this.fakeUser.createBorrow();
        fail("Pending to implement");
    }

    @Test
    @Disabled
    void findBorrow() {
        this.fakeUser.findBorrow();
        fail("Pending to implement");
    }

    @Test
    @Disabled
    void removeBorrow() {
        this.fakeUser.removeBorrow();
        fail("Pending to implement");
    }

    @Test
    @Disabled
    void updateBorrow() {
        this.fakeUser.updateBorrow();
        fail("Pending to implement");
    }

    @Test
    @Disabled
    void listBorrows() {
        this.fakeUser.listBorrows();
        fail("Pending to implement");
    }

    @Test
    void testToString() {
        assertNotNull(this.fakeUser.toString());
    }
}