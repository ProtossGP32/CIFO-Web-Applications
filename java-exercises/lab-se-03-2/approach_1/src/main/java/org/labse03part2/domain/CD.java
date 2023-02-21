package org.labse03part2.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString(callSuper = true)
public class CD extends Publication{
    private int duration;
    private int numberOfTracks;

    public CD() {
        super();
    }
    public CD(String title, Author author, LocalDate publicationDate, String format, int duration, int numberOfTracks) {
        super(title, author, publicationDate, format);
        this.duration = duration;
        this.numberOfTracks = numberOfTracks;
    }
}
