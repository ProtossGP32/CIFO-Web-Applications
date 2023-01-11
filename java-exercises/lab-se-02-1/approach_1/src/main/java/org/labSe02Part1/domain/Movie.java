package org.labSe02Part1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Contains the information related to a movie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private String title;
    private int year;
    private Set<Review> reviews;
    private Critic critic;
}
