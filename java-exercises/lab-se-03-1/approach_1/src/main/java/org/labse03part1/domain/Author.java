package org.labse03part1.domain;

import lombok.*;

import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true)
public class Author extends Person{
    private String genre;

    @Builder
    public Author(String firstName, String lastName, int age, String genre) {
        super(firstName, lastName, age);
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Author author = (Author) o;
        return genre.equals(author.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), genre);
    }

    public boolean canEqual(Object other) {
        return other instanceof Author;
    }
}
