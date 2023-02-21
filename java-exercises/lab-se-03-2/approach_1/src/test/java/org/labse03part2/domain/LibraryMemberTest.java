package org.labse03part2.domain;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.labse03part2.utils.FakeDataGenerator;

import static org.junit.jupiter.api.Assertions.*;

class LibraryMemberTest {

    LibraryMember fakeLibraryMember;
    Faker faker;

    @BeforeEach
    void setUp() {
        this.fakeLibraryMember = FakeDataGenerator.createFakeLibrarian();
        this.faker = new Faker();
    }
    @Test
    void createAccount() {
        this.fakeLibraryMember.createAccount();
        fail("Pending to implement");
    }

    @Test
    void searchPublications() {
        this.fakeLibraryMember.searchPublications(this.faker.book().title());
        fail("Pending to implement");
    }
}