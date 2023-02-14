package org.labse00part1.domain;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.labse03part1.domain.Author;
import org.labse03part1.domain.Book;
import org.labse03part1.domain.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student student1Test;
    private Student student2Test;

    private String fakeFirstName;
    private String fakeLastName;
    private int fakeAge;
    private String fakeStudentID;
    private String fakeUniversity;
    private List<Book> fakeBooks;

    private final Faker studentFaker = new Faker();

    @BeforeEach
    void initialize() {
        this.fakeFirstName = studentFaker.name().firstName();
        this.fakeLastName = studentFaker.name().lastName();
        this.fakeAge = studentFaker.number().numberBetween(0, 99);
        this.fakeUniversity = studentFaker.university().name();
        // Initialize the list of books
        this.fakeBooks = new ArrayList<>();
        for (int i = 1; i < studentFaker.number().randomNumber(2, false); i++) {
            this.fakeBooks.add(createBook());
        }

        this.student1Test = new Student(this.fakeFirstName, this.fakeLastName, this.fakeAge, this.fakeUniversity, this.fakeBooks);
        // Retrieve the studentID from the newly created object
        this.fakeStudentID = this.student1Test.getStudentID();
        // Create an empty Student object for student2Test and assign its values depending on the current test
        this.student2Test = new Student();
    }

    @Test
    void testEquals() {
        // Same values for person2Test
        this.student2Test.setFirstName(this.fakeFirstName);
        this.student2Test.setLastName(this.fakeLastName);
        this.student2Test.setStudentID(this.fakeStudentID);
        this.student2Test.setAge(this.fakeAge);
        this.student2Test.setUniversity(this.fakeUniversity);
        this.student2Test.setBooks(this.fakeBooks);

        assertEquals(this.student1Test, this.student2Test);
    }

    @Test
    void testHashCode() {
        // Same values for person2Test
        this.student2Test.setFirstName(this.fakeFirstName);
        this.student2Test.setLastName(this.fakeLastName);
        this.student2Test.setStudentID(this.fakeStudentID);
        this.student2Test.setAge(this.fakeAge);
        this.student2Test.setUniversity(this.fakeUniversity);
        this.student2Test.setBooks(this.fakeBooks);

        assertEquals(this.student1Test.hashCode(), this.student2Test.hashCode());
    }

    @Test
    void testToString() {
        // Compose the expected lombok toString output
        String expectedToString = "Student(super=Person(firstName=" + this.fakeFirstName + ", lastName=" + this.fakeLastName + ", age=" + this.fakeAge + "), studentID=" + this.fakeStudentID + ", university=" + this.fakeUniversity + ", books=" + this.fakeBooks + ")";

        assertEquals(expectedToString, this.student1Test.toString());
    }

    @Test
    void getUniversity() {
        assertEquals(this.fakeUniversity, this.student1Test.getUniversity());
    }

    @Test
    void setUniversity() {
        String newUniversity = studentFaker.university().name();
        this.student1Test.setUniversity(newUniversity);

        assertEquals(newUniversity, this.student1Test.getUniversity());
    }

    @Test
    void getBooks() {
        assertEquals(this.fakeBooks, this.student1Test.getBooks());
    }

    @Test
    void setBooks() {
        // Initialize the list of books
        List<Book> newBooks = new ArrayList<>();
        for (int i = 1; i < studentFaker.number().randomNumber(2, false); i++) {
            newBooks.add(createBook());
        }

        this.student1Test.setBooks(newBooks);

        assertEquals(newBooks, this.student1Test.getBooks());
    }

    @Test
    void addBook() {
        Book newBook = createBook();
        this.student1Test.addBook(newBook);

        assertTrue(this.student1Test.getBooks().contains(newBook));
    }

    // Test utilities
    Book createBook() {
        com.github.javafaker.Book fakeBook = studentFaker.book();
        Author fakeAuthor = new Author(
                fakeBook.author().split(" ")[0],
                fakeBook.author().split(" ")[1],
                studentFaker.number().numberBetween(0, 99),
                fakeBook.genre()
        );
        return new Book(fakeBook.title(), studentFaker.number().numberBetween(0, 2000), studentFaker.number().numberBetween(0, 2023), studentFaker.code().isbn13(), fakeAuthor, studentFaker.bool().bool());
    }
}