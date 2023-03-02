package com.springbootlab0.approach_1.domain;

import com.springbootlab0.approach_1.utils.Helper;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
// JPA Annotations
@Entity(name="Author")
@Table(name="AUTHOR_TABLE")
// Inheritance strategy is not required as no other classes extend Author
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
}
