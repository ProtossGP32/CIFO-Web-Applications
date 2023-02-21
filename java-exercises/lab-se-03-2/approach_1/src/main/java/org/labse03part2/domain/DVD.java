package org.labse03part2.domain;

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

    public DVD(String title, Author author, LocalDate publicationDate, String format, int duration) {
        super(title, author, publicationDate, format);
        this.duration = duration;
    }
}
