package org.labse02part1.domain;

import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Critic extends Person{
    private Set<Review> reviewSet;
}
