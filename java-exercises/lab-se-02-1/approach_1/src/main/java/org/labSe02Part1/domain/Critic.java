package org.labSe02Part1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains the information related to a Critic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Critic {
    private String name;
    private Review review;
}
