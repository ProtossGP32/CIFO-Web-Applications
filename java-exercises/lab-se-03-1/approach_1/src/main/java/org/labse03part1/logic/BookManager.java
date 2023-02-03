package org.labse03part1.logic;

import org.labse03part1.domain.Book;
import org.labse03part1.utils.InterfaceUtils;

import java.util.HashMap;
import java.util.Scanner;

public class BookManager {
    private HashMap<String, Book> books = new HashMap<>();
    public void createAndAddToStorage(Scanner reader) {
        // Ask for the book title
        String bookTitle = InterfaceUtils.askString(reader, "Enter name of the new book: ");
        while (true) {
            if (!this.books.containsKey(bookTitle)) {
                // The book title is candidate to be inserted
                break;
            }
            System.out.println("Book " + bookTitle + " already exists! Enter a new one");
            bookTitle = InterfaceUtils.askString(reader, "Enter name of the new book: ");
        }

        // Ask for the book year
        // TODO: Secure case where bookYear is not an integer
        int bookYear = InterfaceUtils.askInt(reader, "Enter year of the new book: ");

    }
}
