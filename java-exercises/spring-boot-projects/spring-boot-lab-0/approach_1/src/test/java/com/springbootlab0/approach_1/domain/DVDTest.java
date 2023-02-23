package com.springbootlab0.approach_1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.springbootlab0.approach_1.utils.FakeDataGenerator;

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
        assertNull(this.testDVD.getStatus());
        assertEquals(0, this.testDVD.getDuration());
    }

    @Test
    void testConstructorPopulatesAllFields() {
        assertNotNull(this.testDVD.getId());
        assertNotNull(this.testDVD.getTitle());
        assertNotNull(this.testDVD.getPublicationDate());
        assertNotNull(this.testDVD.getStatus());
        assertTrue(this.testDVD.getDuration() > 0);
    }

    @Test
    void testToString() {
        assertNotNull(this.testDVD.toString());
    }
}