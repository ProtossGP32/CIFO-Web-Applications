package org.labse03part1.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true)
public class Student extends Person{
    private String university;
    private List<Book> books;

    private Car car;

    @Builder
    public Student(String firstName, String lastName, int age, String university) {
        super(firstName, lastName, age);
        this.university = university;
        this.books = new ArrayList<>();
        this.car = new Car();
    }

    @Builder
    public Student(String firstName, String lastName, int age, String university, List<Book> books) {
        super(firstName, lastName, age);
        this.university = university;
        this.books = books;
        this.car = new Car();
    }

    @Builder
    public Student(String firstName, String lastName, int age, String university, List<Book> books, Car car) {
        super(firstName, lastName, age);
        this.university = university;
        this.books = books;
        this.car = car;
    }

    public void addBook(Book newBook) {
        this.books.add(newBook);
    }
}
