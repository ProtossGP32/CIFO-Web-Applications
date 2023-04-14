package com.springbootlab0.approach_1.domain;

import com.springbootlab0.approach_1.bookImageMongoDB.BookImage;
import com.springbootlab0.approach_1.utils.Helper;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;

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
    //@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="PUBLICATION_ID", updatable = false, nullable = false)
    private String id;
    @Column(name="PUBLICATION_TITLE", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @Column(name="PUBLICATION_DATE")
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate publicationDate;
    @Column(name="PUBLICATION_FORMAT")
    private String format;
    @Column(name="PUBLICATION_STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name="IMAGE_IDS")
    private List<String> imageIds;

    protected Publication() {
        this.id = Helper.createUUID();
        this.imageIds = new ArrayList<>();
    }

    protected Publication(String title, Author author, LocalDate publicationDate, String format, Status status) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publication that)) return false;
        return id.equals(that.id) && title.equals(that.title) && author.equals(that.author) && publicationDate.equals(that.publicationDate) && format.equals(that.format) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publicationDate, format, status);
    }

}
