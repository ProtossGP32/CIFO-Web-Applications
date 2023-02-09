package org.labse00part1.domain;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.labse03part1.domain.Author;
import org.labse03part1.domain.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class BookTest {
    private String fakeTitle;
    private int fakePages;
    private String fakeISBN;
    private int fakeYear;
    private Author fakeAuthor;
    private boolean fakeAvailable;
    private Book book1Test;
    private Book book2Test;
    Faker bookFaker = new Faker();

    @BeforeEach
    void initialize() {
        this.fakeTitle = bookFaker.book().title();
        this.fakePages = bookFaker.number().numberBetween(1, 1000);
        this.fakeISBN = bookFaker.code().isbn13();
        this.fakeYear = bookFaker.number().numberBetween(0, 2023);
        String[] fakeAuthorName = bookFaker.book().author().split(" ");
        int fakeAuthorAge = bookFaker.number().numberBetween(0, 99);
        String fakeGenre = bookFaker.book().genre();
        this.fakeAuthor = new Author(fakeAuthorName[0], fakeAuthorName[1], fakeAuthorAge, fakeGenre);
        this.fakeAvailable = bookFaker.bool().bool();

        this.book1Test = new Book(this.fakeTitle, this.fakePages, this.fakeYear, this.fakeISBN, this.fakeAuthor, this.fakeAvailable);
        this.book2Test = new Book();
    }

    @Test
    void testEquals() {
        this.book2Test.setTitle(this.fakeTitle);
        this.book2Test.setPages(this.fakePages);
        this.book2Test.setYear(this.fakeYear);
        this.book2Test.setISBN(this.fakeISBN);
        this.book2Test.setAuthor(this.fakeAuthor);

        assertEquals(this.book1Test, this.book2Test);
    }

    @Test
    void canEqual() {
        fail("Test still not implemented");
    }

    @Test
    void testHashCode() {
        this.book2Test.setTitle(this.fakeTitle);
        this.book2Test.setPages(this.fakePages);
        this.book2Test.setYear(this.fakeYear);
        this.book2Test.setISBN(this.fakeISBN);
        this.book2Test.setAuthor(this.fakeAuthor);

        assertEquals(this.book1Test.hashCode(), this.book2Test.hashCode());
    }

    @Test
    void testToString() {
        String expectedToString = "Book(title=" + this.fakeTitle + ", pages=" + this.fakePages + ", year=" + this.fakeYear + ", ISBN="
                + this.fakeISBN + ", author=" + this.fakeAuthor + ")";
        assertEquals(expectedToString, this.book1Test.toString());
    }

    @Test
    void getTitle() {
        assertEquals(this.fakeTitle, this.book1Test.getTitle());
    }

    @Test
    void getPages() {
        assertEquals(this.fakePages, this.book1Test.getPages());
    }

    @Test
    void getYear() {
        assertEquals(this.fakeYear, this.book1Test.getYear());
    }

    @Test
    void getISBN() {
        assertEquals(this.fakeISBN, this.book1Test.getISBN());
    }

    @Test
    void getAuthor() {
        assertEquals(this.fakeAuthor, this.book1Test.getAuthor());
    }

    @Test
    void setTitle() {
        String newTitle = bookFaker.book().title();
        this.book1Test.setTitle(newTitle);

        assertEquals(newTitle, this.book1Test.getTitle());
    }

    @Test
    void setPages() {
        int newPages = bookFaker.number().numberBetween(1, 2000);
        this.book1Test.setPages(newPages);

        assertEquals(newPages, this.book1Test.getPages());
    }

    @Test
    void setISBN() {
        String newISBN = bookFaker.code().isbn13();
        this.book1Test.setISBN(newISBN);

        assertEquals(newISBN, this.book1Test.getISBN());
    }

    @Test
    void setYear() {
        int newYear = bookFaker.number().numberBetween(0, 2023);
        this.book1Test.setYear(newYear);

        assertEquals(newYear, this.book1Test.getYear());
    }

    @Test
    void setAuthor() {
        Author newAuthor = new Author(bookFaker.name().firstName(), bookFaker.name().lastName(), bookFaker.number().numberBetween(0, 99), bookFaker.book().genre());
        this.book1Test.setAuthor(newAuthor);

        assertEquals(newAuthor, this.book1Test.getAuthor());
    }

}