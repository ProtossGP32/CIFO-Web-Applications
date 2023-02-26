package com.springbootlab0.approach_1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "Book")
@DiscriminatorValue(value = "BOOK")
public class Book extends Publication {
    private String ISBN;
    private int pages;
    private String genre;

    public Book() {
        super();
    }

    public Book(String title, Author author, LocalDate publicationDate, String format, Status status, String ISBN, int pages, String genre) {
        super(title, author, publicationDate, format, status);
        this.ISBN = ISBN;
        this.pages = pages;
        this.genre = genre;
    }
}
