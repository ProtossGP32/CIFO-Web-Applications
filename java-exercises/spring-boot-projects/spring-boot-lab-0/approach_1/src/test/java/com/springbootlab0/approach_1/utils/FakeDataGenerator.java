package com.springbootlab0.approach_1.utils;

import com.github.javafaker.Faker;
import com.springbootlab0.approach_1.domain.*;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class FakeDataGenerator {
    private static final Faker faker = new Faker();

    public List<Book> createFakeBooks(int numBooks) {
        // Define local fake variables

        List<Book> fakeBooks = new ArrayList<>();
        for (int i = 0; i < numBooks; i++) {
            fakeBooks.add(createFakeBook());
        }
        return fakeBooks;
    }

    public static Book createFakeBook() {
        return new Book(
                faker.book().title(),
                createFakeAuthor(),
                faker.date().birthday()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate(),
                "physical",
                "available",
                faker.code().isbn13(),
                faker.number().numberBetween(10, 2000),
                faker.book().genre()
        );
    }

    public static CD createFakeCD() {
        return new CD(
                faker.book().title(),
                createFakeAuthor(),
                faker.date().birthday()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate(),
                "physical",
                "available",
                faker.number().numberBetween(10, 2000),
                faker.number().numberBetween(1, 20)
        );
    }

    public static DVD createFakeDVD() {
        return new DVD(
                faker.book().title(),
                createFakeAuthor(),
                faker.date().birthday()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate(),
                "physical",
                "available",
                faker.number().numberBetween(10, 2000)
        );
    }

    public static Author createFakeAuthor() {
        String[] authorName = faker.book().author().split(" ");
        return new Author(
                authorName[0],
                authorName[1],
                faker.country().name(),
                faker
                    .date()
                    .birthday()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate(),
                faker.funnyName().name());
    }

    public static User createFakeUser() {
        return new User(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.country().name(),
                faker
                        .date()
                        .birthday()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate(),
                faker.address().fullAddress(),
                faker.phoneNumber().phoneNumber(),
                faker.internet().safeEmailAddress(),
                faker.internet().password()
                );
    }

    public static Librarian createFakeLibrarian() {
        return new Librarian(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.country().name(),
                faker
                        .date()
                        .birthday()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate(),
                faker.address().fullAddress(),
                faker.phoneNumber().phoneNumber(),
                faker.number().randomDouble(2, 700, 3000)
        );
    }
}
