package com.springbootlab0.approach_1.domain;

import com.springbootlab0.approach_1.utils.Helper;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@Entity(name="Author")
@Table(name="AUTHOR_TABLE")
public class Author extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="AUTHOR_ID")
    private String id;
    @Column(name="AUTHOR_PENNAME")
    private String penName;

    public Author() {
        super();
        this.id = Helper.createUUID();
    }

    public Author(String firstName, String lastName, String nationality, LocalDate birthDate, String penName) {
        super(firstName, lastName, nationality, birthDate);
        this.id = Helper.createUUID();
        this.penName = penName;
    }
}
