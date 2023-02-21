package org.labse03part2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
abstract class Person {
    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate birthDate;

}
