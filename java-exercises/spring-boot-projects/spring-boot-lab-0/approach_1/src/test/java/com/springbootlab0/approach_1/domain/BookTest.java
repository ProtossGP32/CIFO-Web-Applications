package com.springbootlab0.approach_1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.springbootlab0.approach_1.utils.FakeDataGenerator;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book testBook;

    @BeforeEach
    void setUp() {
        this.testBook = FakeDataGenerator.createFakeBook();
    }

    @Test
    void testNoArgsConstructorInitializesFieldsAsExpected() {
        this.testBook = new Book();
        assertNotNull(this.testBook);
        // Only ID should be different from null
        assertNotNull(this.testBook.getId());
        assertEquals(UUID.fromString(this.testBook.getId()).toString(), this.testBook.getId());
        // The rest of fields are null or zero
        assertNull(this.testBook.getTitle());
        assertNull(this.testBook.getAuthor());
        assertNull(this.testBook.getPublicationDate());
        assertNull(this.testBook.getStatus());
        assertNull(this.testBook.getIsbn());
        assertEquals(0, this.testBook.getPages());
        assertNull(this.testBook.getGenre());
    }

    @Test
    void testConstructorPopulatesAllFields() {
        assertNotNull(this.testBook.getId());
        assertEquals(UUID.fromString(this.testBook.getId()).toString(), this.testBook.getId());
        assertNotNull(this.testBook.getTitle());
        assertNotNull(this.testBook.getAuthor());
        assertNotNull(this.testBook.getPublicationDate());
        assertNotNull(this.testBook.getStatus());
        assertNotNull(this.testBook.getIsbn());
        assertTrue(this.testBook.getPages() > 0);
        assertNotNull(this.testBook.getGenre());
    }

    @Test
    void testToString() {
        assertNotNull(this.testBook.toString());
    }
}