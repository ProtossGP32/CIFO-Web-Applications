package com.springbootlab0.approach_1.domain;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.springbootlab0.approach_1.utils.FakeDataGenerator;

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
    @Disabled("Still no use-case for this")
    void createAccount() {
        this.fakeLibraryMember.createAccount();
        fail("Pending to implement");
    }

    @Test
    @Disabled("Still no use-case for this")
    void searchPublications() {
        this.fakeLibraryMember.searchPublications(this.faker.book().title());
        fail("Pending to implement");
    }
}