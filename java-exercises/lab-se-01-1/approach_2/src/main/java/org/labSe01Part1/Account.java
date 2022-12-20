package org.labSe01Part1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Account {
    private String accountNumber;
    private String pin;
    private double balance;

    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean equals(Object compared) {
        if (this == compared) {
            return true;
        }
        if (!(compared instanceof Account)) {
            return false;
        }

        Account comparedAccount = (Account) compared;

        if (this.accountNumber.equals(comparedAccount.accountNumber) &&
                this.pin.equals(comparedAccount.pin) &&
                this.balance == comparedAccount.balance) {
            return true;
        }

        return false;
    }
}