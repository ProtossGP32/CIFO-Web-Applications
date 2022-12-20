package org.labSe01Part1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Person {
    private String name;
    private String address;
    private int age;
    private Account account;

    public Person(String name, String address, int age, Account account) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.account = account;
    }

    public double getBalance() {
        return this.account.getBalance();
    }

    public void setBalance(double amount) {
        this.account.setBalance(amount);
    }

    public String getPin() {
        return this.account.getPin();
    }

    public void setPin(String newPin) {
        this.account.setPin(newPin);
    }
}
