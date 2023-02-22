package com.springbootlab0.approach_1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.springbootlab0.approach_1.utils.FakeDataGenerator;

import static org.junit.jupiter.api.Assertions.*;

class CDTest {

    private CD testCD;

    @BeforeEach
    void setUp() {
        this.testCD = FakeDataGenerator.createFakeCD();
    }

    @Test
    void testNoArgsConstructorInitializesFieldsAsExpected() {
        this.testCD = new CD();
        assertNotNull(this.testCD);
        assertNotNull(this.testCD.getId());
        assertNull(this.testCD.getTitle());
        assertNull(this.testCD.getPublicationDate());
        assertEquals(0, this.testCD.getDuration());
        assertEquals(0, this.testCD.getNumberOfTracks());
    }

    @Test
    void testAllArgsConstructorPopulatesAllFields() {
        assertNotNull(this.testCD.getId());
        assertNotNull(this.testCD.getTitle());
        assertNotNull(this.testCD.getPublicationDate());
        assertTrue(this.testCD.getDuration() > 0);
        assertTrue(this.testCD.getNumberOfTracks() > 0);
    }

    @Test
    void testToString() {
        assertNotNull(this.testCD.toString());
    }
}