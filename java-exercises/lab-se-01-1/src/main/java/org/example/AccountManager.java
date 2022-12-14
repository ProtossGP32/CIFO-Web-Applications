package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
/**
 * Public class with static methods to manage accounts between persons
 */
public class AccountManager {
    public static boolean withdrawal(Person person, double amount) {
        if (amount > 0 && amount <= person.getBalance()) {
            person.setBalance(person.getBalance() - amount);
            return true;
        }
        return false;
    }

    public static boolean transfer(Person sender, Person receiver, double amount) {
        if (amount > 0 && amount <= sender.getBalance()) {
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            return true;
        }
        return false;
    }

    public static boolean changePin(Person person, String oldPin, String newPin) {
        if (person.getPin().equals(oldPin)) {
            person.setPin(newPin);
            return true;
        }
        return false;
    }
}
