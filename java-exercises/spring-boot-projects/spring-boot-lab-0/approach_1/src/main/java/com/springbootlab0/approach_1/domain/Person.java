package com.springbootlab0.approach_1.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// Required for subclasses to inherit its JPA fields
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
