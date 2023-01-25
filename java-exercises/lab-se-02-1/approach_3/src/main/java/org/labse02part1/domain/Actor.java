package org.labse02part1.domain;

import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Actor extends Person{
    private Set<Movie> movieSet;
}
