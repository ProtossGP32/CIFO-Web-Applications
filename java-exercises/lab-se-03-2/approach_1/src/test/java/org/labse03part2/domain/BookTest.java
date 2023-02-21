package org.labse03part2.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.labse03part2.utils.FakeDataGenerator;

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
        assertNull(this.testBook.getPublicationDate());
        assertNull(this.testBook.getISBN());
        assertEquals(0, this.testBook.getPages());
        assertNull(this.testBook.getGenre());
    }

    @Test
    void testConstructorPopulatesAllFields() {
        assertNotNull(this.testBook.getId());
        assertEquals(UUID.fromString(this.testBook.getId()).toString(), this.testBook.getId());
        assertNotNull(this.testBook.getTitle());
        assertNotNull(this.testBook.getPublicationDate());
        assertNotNull(this.testBook.getISBN());
        assertEquals(0, this.testBook.getPages());
        assertNotNull(this.testBook.getGenre());
    }

    @Test
    void testToString() {
        assertNotNull(this.testBook.toString());
    }
}