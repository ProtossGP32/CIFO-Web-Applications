package org.labse03part1.logic;

import com.github.javafaker.Faker;
import org.labse03part1.domain.Author;
import org.labse03part1.domain.Book;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.labse03part1.utils.InterfaceUtils.askInt;
import static org.labse03part1.utils.InterfaceUtils.askString;

public class BookManager {
    private static final HashMap<String, Book> books = new HashMap<>();

    private enum bookOptionsEnum {
        ADD_BOOK("Add book") {
            @Override
            void action(Scanner reader) {
                addBook(reader);
            }

            void addBook(Scanner reader) {
                // Create a book
                String bookTitle = askString(reader, "[" + bookOptionsEnum.ADD_BOOK.getDescription() + "] Enter name of the new book ('Quit' to exit): ");
                // The book title is candidate to be inserted
                while (books.containsKey(bookTitle)) {
                    if (bookTitle.equals("Quit")) {
                        System.out.println("[" + bookOptionsEnum.ADD_BOOK.getDescription() + "] Add book cancelled");
                        break;
                    }
                    System.out.println("[" + bookOptionsEnum.ADD_BOOK.getDescription() + "] Book " + bookTitle + " already exists!");
                    bookTitle = askString(reader, "[" + bookOptionsEnum.ADD_BOOK.getDescription() + "] Enter name of the new book ('Quit' to exit): ");
                }

                // TODO: Secure the rest of the parameters
                int bookYear = askInt(reader, "[" + bookOptionsEnum.ADD_BOOK.getDescription() + "] Enter year of the new book: ");
                int bookPages = askInt(reader, "[" + bookOptionsEnum.ADD_BOOK.getDescription() + "] Enter number of pages of the book: ");
                String bookISBN = askString(reader, "[" + bookOptionsEnum.ADD_BOOK.getDescription() + "] Enter book ISBN: ");

                // The user selects one of the available authors
                Author bookAuthor = AuthorManager.getAuthor(reader);
                // Add the book into the system
                books.put(bookTitle, new Book(bookTitle, bookPages, bookYear, bookISBN, bookAuthor, true));

                System.out.println("[" + bookOptionsEnum.ADD_BOOK.getDescription() + "] Book " + bookTitle + " added!");
            }
        },
        DELETE_BOOK("Delete book") {
            @Override
            void action(Scanner reader) {
                deleteBook(reader);
            }

            void deleteBook(Scanner reader) {
                String bookTitle = askString(reader, "[" + bookOptionsEnum.DELETE_BOOK.getDescription() + "] Enter name of the book to delete ('Quit' to exit): ");
                while (!books.containsKey(bookTitle)) {
                    if (bookTitle.equals("Quit")) {
                        System.out.println("[" + bookOptionsEnum.DELETE_BOOK.getDescription() + "] Delete book cancelled");
                        break;
                    }
                    System.out.println("[" + bookOptionsEnum.DELETE_BOOK.getDescription() + "] Book " + bookTitle + "doesn't exist in the system!");
                    bookTitle = askString(reader, "[" + bookOptionsEnum.DELETE_BOOK.getDescription() + "] Enter name of the book to delete ('Quit' to exit): ");
                }
                books.remove(bookTitle);
                System.out.println("[" + bookOptionsEnum.DELETE_BOOK.getDescription() + "] Book " + bookTitle + " deleted!");
            }
        },
        CHECK_BOOK("Check book") {
            @Override
            void action(Scanner reader) {
                checkBook(reader);
            }

            void checkBook(Scanner reader) {
                String bookTitle = askString(reader, "[" + bookOptionsEnum.CHECK_BOOK.getDescription() + "] Enter name of the book you want ('Quit' to exit): ");
                while (!books.containsKey(bookTitle)) {
                    if (bookTitle.equals("Quit")) {
                        System.out.println("[" + bookOptionsEnum.CHECK_BOOK.getDescription() + "] Check book cancelled");
                        break;
                    }
                    System.out.println("[" + bookOptionsEnum.CHECK_BOOK.getDescription() + "] Book " + bookTitle + "doesn't exist in the system!");
                    bookTitle = askString(reader, "[" + bookOptionsEnum.CHECK_BOOK.getDescription() + "] Enter name of the book you want ('Quit' to exit): ");
                }
                // Print the book information
                System.out.println(books.get(bookTitle));
            }
        },
        LIST_BOOKS("List books") {
            @Override
            void action(Scanner reader) {
                listBooks();
            }

            void listBooks() {
                System.out.println("[" + bookOptionsEnum.LIST_BOOKS.getDescription() + "] Available books:");
                books.keySet()
                        .forEach(System.out::println);
            }
        },
        UPDATE_BOOK("Update book") {
            @Override
            void action(Scanner reader) {
                updateBook(reader);
            }

            void updateBook(Scanner reader) {
                String bookTitle = askString(reader, "[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Enter name of the book to update ('Quit' to exit): ");
                while (!books.containsKey(bookTitle)) {
                    if (bookTitle.equals("Quit")) {
                        System.out.println("[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Update book cancelled");
                        break;
                    }
                    System.out.println("[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Book " + bookTitle + "doesn't exist in the system!");
                    bookTitle = askString(reader, "[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Enter name of the book to update ('Quit' to exit): ");
                }
                Book bookToUpdate = books.get(bookTitle);
                System.out.println(bookToUpdate);

                // Once book is found, ask for the parameter to change
                String parameter = askString(reader, "[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Enter the parameter to modify ('Quit' to exit): ");
                Object value;
                while (!parameter.equals("Quit")) {
                    switch (parameter) {
                        case "title" -> {
                            value = askString(reader, "[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Enter the new title of the book: ");
                            bookToUpdate.setTitle(value.toString());
                            System.out.println("[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Title set to " + value);
                        }
                        case "pages" -> {
                            value = askInt(reader, "[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Enter the new pages of the book: ");
                            bookToUpdate.setPages((int) value);
                            System.out.println("[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Pages set to " + value);
                        }
                        case "year" -> {
                            value = askInt(reader, "[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Enter the new year of the book: ");
                            bookToUpdate.setYear((int) value);
                            System.out.println("[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Year set to " + value);
                        }
                        case "author" -> {
                            // Ask the user for a new author
                            Author newAuthor = AuthorManager.getAuthor(reader);
                            // Set the new author
                            bookToUpdate.setAuthor(newAuthor);
                            System.out.println("[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Author set to " + bookToUpdate.getAuthor().getFirstName() + " " + bookToUpdate.getAuthor().getLastName());
                        }
                        case "available" -> {
                            String newAvailable = askString(reader, "[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Enter new availability of the book (true or false)");
                            // Check that the input is a boolean
                            while (!Boolean.getBoolean(newAvailable)) {
                                newAvailable = askString(reader, "[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Invalid value. Enter new availability of the book (true or false)");
                            }
                            // Set the new boolean value
                            bookToUpdate.setAvailable(Boolean.getBoolean(newAvailable));
                        }
                        default -> System.out.println("[Manage books] " + parameter + " is a read-only parameter, choose another one");
                    }
                    parameter = askString(reader, "[" + bookOptionsEnum.UPDATE_BOOK.getDescription() + "] Insert the parameter to modify ('Quit' to exit): ");
                }
            }
        };

        abstract void action(Scanner reader);
        private final String description;

        bookOptionsEnum(String description) {
            this.description = description;
        }
        private String getDescription() {
            return this.description;
        }

        static void printOptions() {
            // Print all the available Borrow options
            System.out.println("[Manage books] Available options:");
            bookOptionsEnum.stream()
                    .map(bookOptionsEnum::getDescription)
                    .forEach(System.out::println);
        }

        static bookOptionsEnum getOption(String action) {
            for (bookOptionsEnum option : bookOptionsEnum.values()) {
                if (option.getDescription().equals(action)) {
                    return option;
                }
            }
            return null;
        }

        private static void executeOption(Scanner reader, String action) {
            // compare the action with the available enum and see if it is a valid option
            bookOptionsEnum option = getOption(action);
            // execute the desired option
            if (option != null) {
                option.action(reader);
            }
            else {
                System.out.println("[Manage books] Invalid option, try again");
            }
        }
        public static Stream<bookOptionsEnum> stream() {
            return Stream.of(bookOptionsEnum.values());
        }
    }

    public static void initializeRandomBooks() {
        if (books.isEmpty()) {
            Random randomNum = new Random();
            createFakeBooks(randomNum.nextInt(1, 10));
        }
    }

    public static void start(Scanner reader) {
        // Print the available options
        bookOptionsEnum.printOptions();
        String description = askString(reader, "[Manage books] - Select option: ");
        while (!description.equals("Quit")) {
            bookOptionsEnum.executeOption(reader, description);
            System.out.println();
            bookOptionsEnum.printOptions();
            description = askString(reader, "[Manage books] - Select option: ");
        }
    }

    // Private methods

    private static void createFakeBooks(int number) {
        Faker faker = new Faker();
        // Initialize Random authors if the list is empty
        AuthorManager.initializeRandomActors();
        Book newBook;
        // Create as many new Books as requested
        for (int i = 0; i < number; i++) {
            com.github.javafaker.Book fakeBook = faker.book();
            newBook = new Book();
            newBook.setTitle(fakeBook.title());
            newBook.setPages(faker.number().numberBetween(10, 2000));
            newBook.setYear(faker.number().numberBetween(0, 2023));
            newBook.setISBN(faker.code().isbn13());
            // Get a fake author
            // Mandatory initialization of AuthorManager prior to this
            newBook.setAuthor(AuthorManager.getRandomAuthor());
            // Set a random availability status
            newBook.setAvailable(faker.bool().bool());
            // Put the fake book to the storage
            books.put(newBook.getTitle(), newBook);
        }
    }

    // Public methods
    public static String getBookTitle(Scanner reader) {
        String bookTitle = askString(reader, "[Manage books] Enter book title:");
        while (!books.containsKey(bookTitle)) {
            bookTitle = askString(reader, "[Manage books] Invalid book title. Enter book title:");
        }
        return bookTitle;
    }
}
