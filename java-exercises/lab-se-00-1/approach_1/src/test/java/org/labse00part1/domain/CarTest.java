package org.labse00part1.domain;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    Faker carFaker = new Faker(new Random());

    int fakeDoors;
    int fakeSeats;
    String fakeColor;
    Car car1Test;
    Car car2Test;
    @BeforeEach
    void initialize() {
        this.fakeDoors = carFaker.number().numberBetween(2,5);
        this.fakeSeats = carFaker.number().numberBetween(2,10);
        this.fakeColor = carFaker.color().name();

        this.car1Test = new Car(this.fakeDoors, this.fakeSeats, this.fakeColor);
        this.car2Test = new Car();
    }

    @Test
    void testEquals() {
        this.car2Test.setDoors(this.fakeDoors);
        this.car2Test.setSeats(this.fakeSeats);
        this.car2Test.setColor(this.fakeColor);

        assertEquals(this.car1Test, this.car2Test);
    }

    @Test
    void canEqual() {
        fail("Test still not implemented");
    }

    @Test
    void testHashCode() {
        this.car2Test.setDoors(this.fakeDoors);
        this.car2Test.setSeats(this.fakeSeats);
        this.car2Test.setColor(this.fakeColor);

        assertEquals(this.car1Test.hashCode(), this.car2Test.hashCode());
    }

    @Test
    void testToString() {
        String expectedToString = "Car(doors=" + this.fakeDoors + ", seats=" + this.fakeSeats + ", color=" + this.fakeColor + ")";
        assertEquals(expectedToString, this.car1Test.toString());
    }

    @Test
    void getDoors() {
        assertEquals(this.fakeDoors, this.car1Test.getDoors());
    }

    @Test
    void getSeats() {
        assertEquals(this.fakeSeats, this.car1Test.getSeats());
    }

    @Test
    void getColor() {
        assertEquals(this.fakeColor, this.car1Test.getColor());
    }

    @Test
    void setDoors() {
        int newDoors = carFaker.number().numberBetween(2,5);
        this.car1Test.setDoors(newDoors);

        assertEquals(newDoors, this.car1Test.getDoors());
    }

    @Test
    void setSeats() {
        int newSeats = carFaker.number().numberBetween(2,10);
        this.car1Test.setSeats(newSeats);

        assertEquals(newSeats, this.car1Test.getSeats());
    }

    @Test
    void setColor() {
        String newColor = carFaker.color().name();
        this.car1Test.setColor(newColor);

        assertEquals(newColor, this.car1Test.getColor());
    }
}