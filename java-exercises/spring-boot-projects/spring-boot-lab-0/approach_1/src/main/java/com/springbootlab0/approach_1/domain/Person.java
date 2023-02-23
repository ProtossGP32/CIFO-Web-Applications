package com.springbootlab0.approach_1.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
abstract class Person {
    @Column(name="PERSON_FIRSTNAME")
    private String firstName;
    @Column(name="PERSON_LASTNAME")
    private String lastName;
    @Column(name="PERSON_NATIONALITY")
    private String nationality;
    @Column(name="PERSON_BIRTHDATE")
    private LocalDate birthDate;

}
