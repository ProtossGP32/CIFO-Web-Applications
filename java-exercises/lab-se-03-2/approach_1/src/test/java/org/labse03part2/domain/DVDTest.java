package org.labse03part2.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.labse03part2.utils.FakeDataGenerator;

import static org.junit.jupiter.api.Assertions.*;

class DVDTest {
    private DVD testDVD;

    @BeforeEach
    void setUp() {
        this.testDVD = FakeDataGenerator.createFakeDVD();
    }

    @Test
    void testNoArgsConstructorInitializesFieldsAsExpected() {
        this.testDVD = new DVD();
        assertNotNull(this.testDVD);
        assertNotNull(this.testDVD.getId());
        assertNull(this.testDVD.getTitle());
        assertNull(this.testDVD.getPublicationDate());
        assertEquals(0, this.testDVD.getDuration());
    }

    @Test
    void testConstructorPopulatesAllFields() {
        assertNotNull(this.testDVD.getId());
        assertNotNull(this.testDVD.getTitle());
        assertNotNull(this.testDVD.getPublicationDate());
        assertEquals(0, this.testDVD.getDuration());
    }

    @Test
    void testToString() {
        assertNotNull(this.testDVD.toString());
    }
}