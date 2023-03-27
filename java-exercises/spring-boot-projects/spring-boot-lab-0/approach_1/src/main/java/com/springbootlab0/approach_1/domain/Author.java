package com.springbootlab0.approach_1.domain;

import com.springbootlab0.approach_1.utils.Helper;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
// JPA Annotations
@Entity(name="Author")
@Table(name="AUTHOR_TABLE")
public class Author extends Person{
    @Id
    //@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="AUTHOR_ID", updatable = false, nullable = false)
    private String id;
    @Column(name="AUTHOR_PENNAME")
    private String penName;


    public Author() {
        super();
        this.id = Helper.createUUID();
    }

    public Author(String firstName, String lastName, String nationality, LocalDate birthDate, String penName) {
        super(firstName, lastName, nationality, birthDate);
        this.id = Helper.createUUID();
        this.penName = penName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return id.equals(author.id) && penName.equals(author.penName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, penName);
    }
}
