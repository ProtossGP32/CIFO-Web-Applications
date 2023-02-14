package org.labse03part1.domain;

import lombok.*;

import java.util.Objects;

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
    private boolean available;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        // When comparing books, don't check its availability
        return pages == book.pages && year == book.year && title.equals(book.title) && ISBN.equals(book.ISBN) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, pages, year, ISBN, author);
    }

    public boolean canEqual(Object other) {
        return other instanceof Book;
    }
}

