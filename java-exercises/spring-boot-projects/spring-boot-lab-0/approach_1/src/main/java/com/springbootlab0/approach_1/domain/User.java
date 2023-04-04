package com.springbootlab0.approach_1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "User")
@DiscriminatorValue(value = "USER")
public class User extends LibraryMember implements BorrowOperations{

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "PASSWORD")
    private String password;

    public User() {
        super();
    }
    public User(String firstName, String lastName, String nationality, LocalDate birthDate, String address, String phoneNumber, String mail, String password) {
        super(firstName, lastName, nationality, birthDate, address, phoneNumber);
        this.mail = mail;
        this.password = password;
    }
    @Override
    public void createBorrow() {
        // Create a user's borrow just asking for the book information
    }

    @Override
    public void findBorrow() {
        // Find a user's borrow just asking for the book information
    }

    @Override
    public void removeBorrow() {
        // Remove a user's borrow just asking for the borrow information
    }

    @Override
    public void updateBorrow() {
        // Create a user's borrow just asking for the book information
    }

    @Override
    public void listBorrows() {
        // List all user's borrow
    }

    /*
    public static User createFromJson(String userJSON) {
        // First, convert the JSON into a Map
        JsonParser bodyParser = JsonParserFactory.getJsonParser();
        Map<String, Object> bodyMap = bodyParser.parseMap(userJSON);
        // Then, create a new User with all the fields of the JSON
        // TODO: assign the fields of the JSON
        User newUser = new User();
        Class<?> clazz = newUser.getClass();
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
                        return false;
                    }
                })
                .forEach(field -> {
                    try {
                        field.set(newUser, field.get(bodyMap));
                    } catch (IllegalAccessException e) {
                        // Handle the exception
                    }
                });
        // Return the newly created User
        return newUser;
    }

     */
}
