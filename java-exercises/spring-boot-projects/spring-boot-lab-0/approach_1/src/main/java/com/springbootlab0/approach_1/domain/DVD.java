package com.springbootlab0.approach_1.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString(callSuper = true)
public class DVD extends Publication{
    private int duration;

    public DVD() {
        super();
    }

    public DVD(String title, Author author, LocalDate publicationDate, String format, String status, int duration) {
        super(title, author, publicationDate, format, status);
        this.duration = duration;
    }
}
