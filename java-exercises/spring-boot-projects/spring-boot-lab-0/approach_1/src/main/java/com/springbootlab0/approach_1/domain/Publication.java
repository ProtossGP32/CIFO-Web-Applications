package com.springbootlab0.approach_1.domain;

import com.springbootlab0.approach_1.utils.Helper;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Setter
@ToString
@Getter
@Entity(name="Publication")
@Table(name="PUBLICATION_TABLE")
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Publication implements PublicationOperations{
    // Required fields
    @Id
    @Column(name="PUBLICATION_ID")
    private String id;
    @Column(name="PUBLICATION_TITLE")
    private String title;
    @Column(name="PUBLICATION_AUTHOR")
    private Author author;
    @Column(name="PUBLICATION_DATE")
    private LocalDate publicationDate;
    @Column(name="PUBLICATION_FORMAT")
    private String format;
    @Column(name="PUBLICATION_STATUS")
    private String status;


    public Publication() {
        this.id = Helper.createUUID();
    }

    public Publication(String title, Author author, LocalDate publicationDate, String format, String status) {
        this();
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.format = format;
        this.status = status;
    }

    // Interface methods
    @Override
    public void publish(){
        System.out.println("Publishing item: " + this);
    }
}
