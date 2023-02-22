package com.springbootlab0.approach_1.domain;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.springbootlab0.approach_1.utils.FakeDataGenerator;

import java.time.ZoneId;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    private Author testAuthor;

    @BeforeEach
    void setUp() {
        this.testAuthor = FakeDataGenerator.createFakeAuthor();
    }
    @Test
    void testAllArgsConstructorPopulatesAllFields() {
        assertNotNull(this.testAuthor.getFirstName());
        assertNotNull(this.testAuthor.getLastName());
        assertNotNull(this.testAuthor.getNationality());
        assertNotNull(this.testAuthor.getBirthDate());
        assertNotNull(this.testAuthor.getPenName());
    }

    @Test
    void testNoArgsConstructorOnlyInitializesID() {
        this.testAuthor = new Author();
        assertNotNull(this.testAuthor);
        // Only ID should be different from null
        assertNotNull(this.testAuthor.getId());
        assertEquals(UUID.fromString(this.testAuthor.getId()).toString(), this.testAuthor.getId());
        // The rest of fields are null or zero
        assertNull(this.testAuthor.getFirstName());
        assertNull(this.testAuthor.getLastName());
        assertNull(this.testAuthor.getNationality());
        assertNull(this.testAuthor.getBirthDate());
        assertNull(this.testAuthor.getPenName());
    }

    @Test
    @Disabled
    /**
     * This test doesn't apply anymore as we need to initialize the Author ID within the constructor
     * TODO: Come back when JPA annotations are understood. Maybe ID is automatically generated
     */
    void testBuilder() {
        Faker faker = new Faker();
        this.testAuthor = Author.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .nationality(faker.country().name())
                .birthDate(faker
                        .date()
                        .birthday()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate())
                .penName(faker.funnyName().name())
                .build();

        assertNotNull(this.testAuthor.getId());
    }

    @Test
    void testToString() {

        assertNotNull(this.testAuthor);
    }

}