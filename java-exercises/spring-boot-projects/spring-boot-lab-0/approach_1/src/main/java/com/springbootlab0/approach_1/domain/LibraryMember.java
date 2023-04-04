package com.springbootlab0.approach_1.domain;

import jakarta.persistence.*;
import lombok.*;
import com.springbootlab0.approach_1.utils.Helper;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

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

    // Generics
    public static <T extends LibraryMember> T createFromJson(String userJSON, Class<T> clazz) {
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
                        //Object userValue = field.get(bodyMap);
                        String fieldName = field.getName();
                        Object userValue = bodyMap.getOrDefault(fieldName, null);
                        return userValue != null &&
                                (!(userValue instanceof Number) || ((Number) userValue).intValue() != 0) &&
                                (!(userValue instanceof Boolean) || (Boolean) userValue);
                    })
                    .forEach(field -> {
                        try {
                            Object value = bodyMap.get(field.getName());
                            field.set(newMember, value);
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
