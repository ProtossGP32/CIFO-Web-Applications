package com.springbootlab0.approach_1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "DVD")
@DiscriminatorValue(value = "DVD")
public class DVD extends Publication{
    @Column(name = "DURATION")
    private int duration;

    public DVD() {
        super();
    }

    public DVD(String title, Author author, LocalDate publicationDate, String format, Status status, int duration) {
        super(title, author, publicationDate, format, status);
        this.duration = duration;
    }
}
