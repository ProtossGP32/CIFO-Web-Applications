package com.springbootlab0.approach_1.utils;

import com.github.javafaker.Faker;
import com.springbootlab0.approach_1.domain.*;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class FakeDataGenerator {
    private static final Faker faker = new Faker();

    public static List<Publication> createFakePublications(int numPublications) {
        List<Publication> fakePublications = new ArrayList<>();
        int factoryNum;
        for (int i = 0; i < numPublications; i++) {
            factoryNum = faker.number().numberBetween(0, 3);
            switch (factoryNum) {
                case 0 ->
                    // 0 - New book
                        fakePublications.add(createFakeBook());
                case 1 ->
                    // 1 - New CD
                        fakePublications.add(createFakeCD());
                case 2 ->
                    // 2 - New DVD
                        fakePublications.add(createFakeDVD());
                default -> System.out.println("unknown value");
            }
        }

        return fakePublications;
    }

    public static List<Book> createFakeBooks(int numBooks) {
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
                Status.AVAILABLE,
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
                Status.AVAILABLE,
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
                Status.AVAILABLE,
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

    public static List<LibraryMember> createFakeLibraryMembers(int numMembers) {
        List<LibraryMember> fakeMembers = new ArrayList<>();
        int factoryNum;
        for (int i = 0; i < numMembers; i++) {
            factoryNum = faker.number().numberBetween(0, 2);
            switch (factoryNum) {
                case 0 ->
                    // 0 - New User
                        fakeMembers.add(createFakeUser());
                case 1 ->
                    // 1 - New Librarian
                        fakeMembers.add(createFakeLibrarian());
                default -> System.out.println("unknown value");
            }
        }

        return fakeMembers;
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
