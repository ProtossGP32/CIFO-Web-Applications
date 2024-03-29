package org.labse03part2.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.labse03part2.utils.FakeDataGenerator;

import static org.junit.jupiter.api.Assertions.*;

class PublicationTest {

    Publication fakePublication;
    @BeforeEach
    void setUp() {
        this.fakePublication = FakeDataGenerator.createFakeDVD();
    }

    @Test
    void publish() {
        this.fakePublication.publish();
        fail("Pending to implement");
    }
}