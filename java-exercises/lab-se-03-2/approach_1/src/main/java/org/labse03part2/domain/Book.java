package org.labse03part2.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString(callSuper = true)
public class Book extends Publication {
    private String ISBN;
    private int pages;
    private String genre;

    public Book() {
        super();
    }

    public Book(String title, Author author, LocalDate publicationDate, String format, String ISBN, int pages, String genre) {
        super(title, author, publicationDate, format);
        this.ISBN = ISBN;
        this.pages = pages;
        this.genre = genre;
    }
}
