package org.labse03part1.logic;

import com.github.javafaker.Faker;
import org.labse03part1.domain.Author;
import org.labse03part1.domain.Book;
import org.labse03part1.utils.InterfaceUtils;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class BookManager {
    private static final HashMap<String, Book> books = new HashMap<>();

    private enum bookOptionsEnum {
        ADD_BOOK("Add book"),
        DELETE_BOOK("Delete book"),
        GET_BOOK("Get book"),
        LIST_BOOKS("List books"),
        UPDATE_BOOK("Update book");

        private final String action;

        bookOptionsEnum(String action) {
            this.action = action;
        }
        private String getAction() {
            return this.action;
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
        printOptions();
        String option = InterfaceUtils.askString(reader, "- Select option:");
        Object result = executeOption(reader, option);
        // TODO: Process the result of the method
    }

    private static void printOptions() {
        // Print all the available Borrow options
        System.out.println("Available options:");
        bookOptionsEnum.stream()
                .map(bookOptionsEnum::getAction)
                .forEach(System.out::println);
    }



    private static bookOptionsEnum getOption(String action) {
        for (bookOptionsEnum option : bookOptionsEnum.values()) {
            if (option.getAction().equals(action)) {
                return option;
            }
        }
        return null;
    }

    private static Object executeOption(Scanner reader, String action) {
        // compare the action with the available enum and see if it is a valid option
        bookOptionsEnum option = getOption(action);
        // execute the desired option
        if (option != null) {
            switch (option) {

                case ADD_BOOK -> {
                    return addBook(reader);
                }
                case DELETE_BOOK -> {
                    return deleteBook(reader);
                }
                case GET_BOOK -> {
                    return getBook(reader);
                }
                case LIST_BOOKS -> {
                    return listBooks(reader);
                }
                case UPDATE_BOOK -> {
                }
            }
        }
        else {
            System.out.println("Unknown option, try again");
            return false;
        }
        return null;
    }

    public static void initializeRandom() {
        Random randomNum = new Random();
        createFakeBooks(randomNum.nextInt(0, 10));
    }

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
            // Get a fake author
            // Mandatory initialization of AuthorManager prior to this
            newBook.setAuthor(AuthorManager.getRandomAuthor());
            // Put the fake book to the storage
            books.put(newBook.getTitle(), newBook);
        }
    }

    private static boolean addBook(Scanner reader) {
        // Create a book
        String bookTitle = InterfaceUtils.askString(reader, "Enter name of the new book: ");
        // The book title is candidate to be inserted
        while (books.containsKey(bookTitle)) {
            System.out.println("Book " + bookTitle + " already exists!");
            bookTitle = InterfaceUtils.askString(reader, "- Enter name of the new book: ");
        }

        // TODO: Secure the rest of the parameters
        int bookYear = InterfaceUtils.askInt(reader, "- Enter year of the new book: ");
        int bookPages = InterfaceUtils.askInt(reader, "- Enter number of pages of the book:");
        String bookISBN = InterfaceUtils.askString(reader, "- Enter book ISBN:");
        System.out.println("Available authors:");
        AuthorManager.listAuthors();
        String authorName = InterfaceUtils.askString(reader, "- Enter author's name:");
        Author bookAuthor = AuthorManager.getAuthor(reader);
        books.put(bookTitle, new Book(bookTitle, bookPages, bookYear, bookISBN, bookAuthor));

        System.out.println("Book " + bookTitle + " added!");
        return true;
    }

    private static boolean deleteBook(Scanner reader) {
        String bookTitle;
        bookTitle = InterfaceUtils.askString(reader, "Enter name of the book to delete: ");
        while (!books.containsKey(bookTitle)) {
            System.out.println("Book " + bookTitle + "doesn't exist in the system!");
            bookTitle = InterfaceUtils.askString(reader, "Enter name of the book to delete: ");
        }
        books.remove(bookTitle);
        System.out.println("Book " + bookTitle + " deleted!");
        return true;
    }

    private static Book getBook(Scanner reader) {
        String bookTitle;
        bookTitle = InterfaceUtils.askString(reader, "Enter name of the book you want: ");
        while (!books.containsKey(bookTitle)) {
            System.out.println("Book " + bookTitle + "doesn't exist in the system!");
            bookTitle = InterfaceUtils.askString(reader, "Enter name of the book you want: ");
        }

        return books.get(bookTitle);
    }

    private static boolean listBooks(Scanner reader) {
        books.keySet().stream()
                .forEach(book -> System.out.println(book));
        return true;
    }

    private static boolean updateBook(Scanner reader) {
        return false;
    }
}
