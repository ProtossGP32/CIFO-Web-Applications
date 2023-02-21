package org.labse03part2.domain;

import lombok.Data;
import lombok.Getter;
import org.labse03part2.utils.Helper;

import java.time.LocalDate;

@Data
@Getter
abstract class Publication implements PublicationOperations{
    // Required fields
    private String id;
    private String title;
    private Author author;
    private LocalDate publicationDate;
    private String format;


    public Publication() {
        this.id = Helper.createUUID();
    }

    public Publication(String title, Author author, LocalDate publicationDate, String format) {
        this();
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.format = format;
    }

    // Interface methods
    @Override
    public void publish(){
        System.out.println("Publishing item: " + this);
    }
}
