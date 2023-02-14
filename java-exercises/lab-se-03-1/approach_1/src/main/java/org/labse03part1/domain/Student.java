package org.labse03part1.domain;

import lombok.*;
import org.labse03part1.utils.InterfaceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true)
public class Student extends Person{
    private String studentID;
    private String university;
    private List<Book> books;

    @Builder
    public Student(String firstName, String lastName, int age, String university) {
        super(firstName, lastName, age);
        this.studentID = InterfaceUtils.createUUID();
        this.university = university;
        this.books = new ArrayList<>();
    }

    @Builder
    public Student(String firstName, String lastName, int age, String university, List<Book> books) {
        super(firstName, lastName, age);
        this.studentID = InterfaceUtils.createUUID();
        this.university = university;
        this.books = books;
    }

    public void addBook(Book newBook) {
        this.books.add(newBook);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        if (!super.equals(o)) return false;
        return getUniversity().equals(student.getUniversity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUniversity());
    }
}
