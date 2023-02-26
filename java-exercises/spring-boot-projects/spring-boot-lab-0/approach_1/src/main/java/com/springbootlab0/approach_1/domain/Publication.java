package com.springbootlab0.approach_1.domain;

import com.springbootlab0.approach_1.utils.Helper;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

// Lombok annotations
@Setter
@ToString
@Getter
// JPA annotations
// - Even though we don't create instances of abstract class Publication, this is required for its subclasses
@Entity(name="Publication")
@Table(name="PUBLICATION_TABLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PUBLICATION_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Publication implements PublicationOperations{
    // Required fields
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="PUBLICATION_ID", updatable = false, nullable = false)
    private String id;
    @Column(name="PUBLICATION_TITLE", nullable = false)
    private String title;

    // TODO: Author entity table isn't being created, thus the app fails if the author field is enabled here
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @Column(name="PUBLICATION_DATE")
    private LocalDate publicationDate;
    @Column(name="PUBLICATION_FORMAT")
    private String format;
    @Column(name="PUBLICATION_STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Publication() {
        this.id = Helper.createUUID();
    }

    public Publication(String title, Author author, LocalDate publicationDate, String format, Status status) {
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
