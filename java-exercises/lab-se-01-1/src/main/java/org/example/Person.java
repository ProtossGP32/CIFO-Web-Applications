package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Person {
    private String name;
    private String address;
    private int age;
    private Account account;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
