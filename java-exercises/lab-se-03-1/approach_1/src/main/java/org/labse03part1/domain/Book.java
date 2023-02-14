package org.labse03part1.domain;

import lombok.*;
import org.labse03part1.utils.InterfaceUtils;

import java.util.Objects;

@Data
@Getter
@Setter
public class Book {
    private String bookID;
    private String title;
    private int pages;
    private int year;
    private String ISBN;
    private Author author;
    private boolean available;

    public Book() {
        this.bookID = InterfaceUtils.createUUID();
    }

    public Book(String title, int pages, int year, String ISBN, Author author, boolean available) {
        this();
        this.title = title;
        this.pages = pages;
        this.year = year;
        this.ISBN = ISBN;
        this.author = author;
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        // When comparing books, don't check its availability nor its ID
        return pages == book.pages && year == book.year && title.equals(book.title) && ISBN.equals(book.ISBN) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, pages, year, ISBN, author);
    }

}

