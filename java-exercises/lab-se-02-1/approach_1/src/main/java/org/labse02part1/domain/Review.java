package org.labse02part1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains the information related to a movie review
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private Movie movie;
    private Critic critic;
    private int rating;
    private String comment;
}
