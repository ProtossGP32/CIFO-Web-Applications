package com.springbootlab0.approach_1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.springbootlab0.approach_1.utils.FakeDataGenerator;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianTest {

    private Librarian fakeLibrarian;
    
    @BeforeEach
    void setUp() {
        this.fakeLibrarian = FakeDataGenerator.createFakeLibrarian();
    }

    @Test
    void testNoArgsConstructorInitializesFieldsAsExpected() {
        this.fakeLibrarian = new Librarian();
        assertNotNull(this.fakeLibrarian);
        // Only ID should be different from null
        assertNotNull(this.fakeLibrarian.getId());
        assertEquals(UUID.fromString(this.fakeLibrarian.getId()).toString(), this.fakeLibrarian.getId());
        // The rest should be null
        assertNull(this.fakeLibrarian.getFirstName());
        assertNull(this.fakeLibrarian.getLastName());
        assertNull(this.fakeLibrarian.getNationality());
        assertNull(this.fakeLibrarian.getBirthDate());
        assertNull(this.fakeLibrarian.getAddress());
        assertNull(this.fakeLibrarian.getPhoneNumber());
        assertEquals(0, this.fakeLibrarian.getSalary(), 0.01);
    }

    @Test
    void testAllArgsConstructorPopulatesAllFields() {
        assertNotNull(this.fakeLibrarian);
        // Only ID should be different from null
        assertNotNull(this.fakeLibrarian.getId());
        assertEquals(UUID.fromString(this.fakeLibrarian.getId()).toString(), this.fakeLibrarian.getId());
        // The rest should be null
        assertNotNull(this.fakeLibrarian.getFirstName());
        assertNotNull(this.fakeLibrarian.getLastName());
        assertNotNull(this.fakeLibrarian.getNationality());
        assertNotNull(this.fakeLibrarian.getBirthDate());
        assertNotNull(this.fakeLibrarian.getAddress());
        assertNotNull(this.fakeLibrarian.getPhoneNumber());
        assertTrue(this.fakeLibrarian.getSalary() > 0);
    }
    @Test
    void createBorrow() {
        this.fakeLibrarian.createBorrow();
        fail("Pending to implement");
    }

    @Test
    void findBorrow() {
        this.fakeLibrarian.findBorrow();
        fail("Pending to implement");
    }

    @Test
    void removeBorrow() {
        this.fakeLibrarian.removeBorrow();
        fail("Pending to implement");
    }

    @Test
    void updateBorrow() {
        this.fakeLibrarian.updateBorrow();
        fail("Pending to implement");
    }

    @Test
    void listBorrows() {
        this.fakeLibrarian.listBorrows();
        fail("Pending to implement");
    }

    @Test
    void testToString() {
        assertNotNull(this.fakeLibrarian.toString());
    }
}