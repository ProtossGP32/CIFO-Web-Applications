package org.labse00part1.domain;

import lombok.*;

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
}
