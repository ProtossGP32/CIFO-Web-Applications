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

    // Generics
    public static <T extends Publication> T createFromJson(String userJSON, Class<T> clazz) {
        // First, convert the JSON into a Map
        JsonParser bodyParser = JsonParserFactory.getJsonParser();
        Map<String, Object> bodyMap = bodyParser.parseMap(userJSON);
        // Then, create a new User with all the fields of the JSON
        // TODO: assign the fields of the JSON
        try {
            T newMember = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            Arrays.stream(fields)
                    .filter(field -> {
                        // Only get fields that exist in the User class
                        try {
                            Object userValue = field.get(bodyMap);
                            return userValue != null &&
                                    (!(userValue instanceof Number) || ((Number) userValue).intValue() != 0) &&
                                    (!(userValue instanceof Boolean) || (Boolean) userValue);
                        } catch (IllegalAccessException e) {
                            // Not a class field
                            return false;
                        }
                    })
                    .forEach(field -> {
                        try {
                            field.set(newMember, field.get(bodyMap));
                        } catch (IllegalAccessException e) {
                            // Handle the stream exception
                        }
                    });
            // Return the newly created User
            return newMember;
        } catch (InstantiationException e) {
            // Generics exception
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            // Generics exception
            throw new RuntimeException(e);
        }
    }
}
