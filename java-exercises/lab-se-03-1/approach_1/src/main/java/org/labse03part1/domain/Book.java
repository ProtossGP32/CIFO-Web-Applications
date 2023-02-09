package org.labse03part1.domain;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String title;
    private int pages;
    private int year;
    private String ISBN;
    private Author author;
    // TODO: Add an availability attribute
    private boolean available;
}
