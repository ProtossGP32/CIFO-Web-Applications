package org.labse03part1.domain;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private int age;
}
