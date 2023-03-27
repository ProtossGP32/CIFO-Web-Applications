package com.springbootlab0.approach_1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.springbootlab0.approach_1.utils.FakeDataGenerator;

import static org.junit.jupiter.api.Assertions.*;

class PublicationTest {

    Publication fakePublication;
    @BeforeEach
    void setUp() {
        this.fakePublication = FakeDataGenerator.createFakeDVD();
    }

    @Test
    @Disabled
    void publish() {
        this.fakePublication.publish();
        fail("Pending to implement");
    }
}