package org.labse02part1.domain;

import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private String title;
    private FilmMaker filmMaker;
    private Set<Actor> actors;
    private Set<Review> reviews;
}
