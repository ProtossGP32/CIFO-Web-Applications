package org.labse02part1.domain;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private Movie movie;
    private Critic critic;
    private int rating;
    private String comment;
}
