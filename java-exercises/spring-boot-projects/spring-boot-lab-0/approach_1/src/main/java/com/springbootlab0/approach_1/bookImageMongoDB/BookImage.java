package com.springbootlab0.approach_1.bookImageMongoDB;

import jakarta.persistence.Id;
import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

// Lombok annotations
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// MongoDB annotations
@Document(collection = "book_images")
public class BookImage {
    @Id
    private String id;
    private String name;
    // Image is stored as a binary file
    private Binary image;

    private String publication_id;
}
