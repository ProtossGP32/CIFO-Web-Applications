package org.labse00part1.domain;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.labse03part1.domain.Author;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    private Author author1Test;
    private Author author2Test;

    private String fakeFirstName;
    private String fakeLastName;
    private int fakeAge;
    private String fakeGenre;

    private Faker authorFaker = new Faker();

    @BeforeEach
    void initialize() {
        this.fakeFirstName = this.authorFaker.name().firstName();
        this.fakeLastName = this.authorFaker.name().lastName();
        this.fakeAge = this.authorFaker.number().numberBetween(0, 99);
        this.fakeGenre = this.authorFaker.book().genre();

        this.author1Test = new Author(this.fakeFirstName, this.fakeLastName, this.fakeAge, this.fakeGenre);
        // Create an empty Actor object for actor2Test and assign its values depending on the current test
        this.author2Test = new Author();
    }
    @Test
    void testEquals() {
        // Same values for person2Test
        this.author2Test.setFirstName(this.fakeFirstName);
        this.author2Test.setLastName(this.fakeLastName);
        this.author2Test.setAge(this.fakeAge);
        this.author2Test.setGenre(this.fakeGenre);
        assertEquals(this.author1Test, this.author2Test);
    }

    @Test
    void canEqual() {
        // Same values for person2Test
        this.author2Test.setFirstName(this.fakeFirstName);
        this.author2Test.setLastName(this.fakeLastName);
        this.author2Test.setAge(this.fakeAge);
        this.author2Test.setGenre(this.fakeGenre);

        Object objectAuthor = (Object) this.author2Test;
        assertTrue(this.author1Test.canEqual(objectAuthor));
    }

    @Test
    void testHashCode() {
        // Same values for person2Test
        this.author2Test.setFirstName(this.fakeFirstName);
        this.author2Test.setLastName(this.fakeLastName);
        this.author2Test.setAge(this.fakeAge);
        this.author2Test.setGenre(this.fakeGenre);

        assertEquals(this.author1Test.hashCode(), this.author2Test.hashCode());
    }

    @Test
    void getGenre() {
        assertEquals(this.fakeGenre, this.author1Test.getGenre());
    }

    @Test
    void setGenre() {
        String newGenre = this.authorFaker.book().genre();
        this.author1Test.setGenre(newGenre);

        assertEquals(newGenre, this.author1Test.getGenre());
    }

    @Test
    void testToString() {
        // Compose the expected lombok toString output
        String expectedToString = "Author(super=Person(firstName=" + this.fakeFirstName + ", lastName=" + this.fakeLastName + ", age=" + this.fakeAge + "), genre=" + this.fakeGenre + ")";

        assertEquals(expectedToString, this.author1Test.toString());
    }
}