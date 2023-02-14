package org.labse00part1.domain;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.labse03part1.domain.Person;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person person1Test;
    private Person person2Test;

    private String fakeFirstName;
    private String fakeLastName;
    private int fakeAge;

    private Faker faker = new Faker();

    @BeforeEach
    void initialize() {
        this.fakeFirstName = faker.name().firstName();
        this.fakeLastName = faker.name().lastName();
        this.fakeAge = faker.number().numberBetween(0, 99);
        this.person1Test = new Person(this.fakeFirstName, this.fakeLastName, this.fakeAge);
        // Create an empty Person object for person2Test and assign its values depending on the current test
        this.person2Test = new Person();
    }
    @Test
    void testEquals() {
        // Same values for person2Test
        this.person2Test.setFirstName(this.fakeFirstName);
        this.person2Test.setLastName(this.fakeLastName);
        this.person2Test.setAge(this.fakeAge);
        assertTrue(this.person1Test.equals(this.person2Test));
    }

    @Test
    void testHashCode() {
        // Same values for person2Test
        this.person2Test.setFirstName(this.fakeFirstName);
        this.person2Test.setLastName(this.fakeLastName);
        this.person2Test.setAge(this.fakeAge);
        assertEquals(this.person1Test.hashCode(), this.person2Test.hashCode());
    }

    @Test
    void testToString() {
        // Compose the expected lombok toString output
        String expectedToString = "Person(firstName=" + this.fakeFirstName + ", lastName=" + this.fakeLastName + ", age=" + this.fakeAge + ")";
        assertEquals(expectedToString, this.person1Test.toString());
    }

    @Test
    void getFirstName() {
        assertEquals(this.fakeFirstName, this.person1Test.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals(this.fakeLastName, this.person1Test.getLastName());
    }

    @Test
    void getAge() {
        assertEquals(this.fakeAge, this.person1Test.getAge());
    }

    @Test
    void setFirstName() {
        String newFirstName = faker.name().firstName();
        this.person1Test.setFirstName(newFirstName);
        assertEquals(newFirstName, this.person1Test.getFirstName());
    }

    @Test
    void setLastName() {
        String newLastName = faker.name().lastName();
        this.person1Test.setLastName(newLastName);
        assertEquals(newLastName, this.person1Test.getLastName());
    }

    @Test
    void setAge() {
        int newAge = faker.number().numberBetween(0, 99);
        this.person1Test.setAge(newAge);
        assertEquals(newAge, this.person1Test.getAge());
    }
}