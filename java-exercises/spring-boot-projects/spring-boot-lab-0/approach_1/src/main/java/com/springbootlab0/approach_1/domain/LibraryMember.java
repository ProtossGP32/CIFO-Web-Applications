package com.springbootlab0.approach_1.domain;

import jakarta.persistence.*;
import lombok.*;
import com.springbootlab0.approach_1.utils.Helper;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// Lombok annotations
@Getter
@Setter
@ToString(callSuper = true)
// JPA Annotations
@Entity
@Table(name="LIBRARYMEMBER_TABLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MEMBER_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class LibraryMember extends Person implements GeneralOperations {
    @Id
    //@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="LIBRARYMEMBER_ID", updatable = false, nullable = false)
    private String id;
    @Column(name="LIBRARYMEMBER_ADDRESS")
    private String address;
    @Column(name="LIBRARYMEMBER_PHONENUMBER")
    private String phoneNumber;

    protected LibraryMember() {
        super();
        this.id = Helper.createUUID();
    }

    protected LibraryMember(String firstName, String lastName, String nationality, LocalDate birthDate, String address, String phoneNumber) {
        super(firstName, lastName, nationality, birthDate);
        this.id = Helper.createUUID();
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void createAccount() {
        System.out.println("[" + this.getClass().getSimpleName() + "] Creating account...");
    }

    @Override
    public void searchPublications(String query) {
        System.out.println("[" + this.getClass().getSimpleName() + "] Searching publications...");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryMember that)) return false;
        if (!super.equals(o)) return false;
        return getId().equals(that.getId()) && getAddress().equals(that.getAddress()) && getPhoneNumber().equals(that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getAddress(), getPhoneNumber());
    }
}
