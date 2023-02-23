package com.springbootlab0.approach_1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "CD")
@DiscriminatorValue(value = "CD")
public class CD extends Publication{
    @Column(name = "DURATION")
    private int duration;
    @Column(name = "NUMBER_OF_TRACKS")
    private int numberOfTracks;

    public CD() {
        super();
    }
    public CD(String title, Author author, LocalDate publicationDate, String format, Status status, int duration, int numberOfTracks) {
        super(title, author, publicationDate, format, status);
        this.duration = duration;
        this.numberOfTracks = numberOfTracks;
    }
}
