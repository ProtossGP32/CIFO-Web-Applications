package com.springbootlab0.approach_1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CD cd)) return false;
        if (!super.equals(o)) return false;
        if (Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        return getDuration() == cd.getDuration() && getNumberOfTracks() == cd.getNumberOfTracks();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDuration(), getNumberOfTracks());
    }
}
