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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DVD dvd)) return false;
        if (!super.equals(o)) return false;
        if (Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        return getDuration() == dvd.getDuration();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDuration());
    }
}
