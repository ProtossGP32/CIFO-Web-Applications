package org.labse03part2.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.labse03part2.utils.FakeDataGenerator;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    private Author testAuthor;

    @BeforeEach
    void setUp() {
        this.testAuthor = FakeDataGenerator.createFakeAuthor();
    }
    @Test
    void testBuilder() {
        assertNotNull(this.testAuthor.getFirstName());
        assertNotNull(this.testAuthor.getLastName());
        assertNotNull(this.testAuthor.getNationality());
        assertNotNull(this.testAuthor.getBirthDate());
        assertNotNull(this.testAuthor.getPenName());
    }

    @Test
    void testToString() {

        assertNotNull(this.testAuthor);
    }

}