package com.springbootlab0.approach_1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "Book")
@DiscriminatorValue(value = "BOOK")
public class Book extends Publication {
    private String isbn;
    private int pages;
    private String genre;

    public Book() {
        super();
    }
}
