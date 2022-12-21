import org.labSe01Part1.Account;
import org.labSe01Part1.AccountManager;
import org.labSe01Part1.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountManagerTest {
    private Person person1Test;
    private Account account1Test;
    private Person person2Test;
    private Account account2Test;

    private final double deltaCompare = 0.01;

    @Before
    public void initialize() {
        // Person 1 initialization values
        String person1Name = "Luke";
        String person1Address = "Fake Street, 99";
        int person1Age = 54;

        // Account 1 initialization values
        String account1Number = "ABCD0123456789";
        String pin1 = "1234";
        double balance1 = 1000.0;

        this.account1Test = new Account(account1Number, pin1, balance1);
        this.person1Test = new Person(person1Name, person1Address, person1Age, this.account1Test);

        // Person 2 initialization values
        String person2Name = "Mariah";
        String person2Address = "Ultra Fake Boulevard, 22";
        int person2Age = 37;

        // Account 2 initialization values
        String account2Number = "XYZ0123456789";
        String pin2 = "4321";
        double balance2 = 20.0;

        this.account2Test = new Account(account2Number, pin2, balance2);
        this.person2Test = new Person(person2Name, person2Address, person2Age, this.account2Test);
    }

    @Test
    public void AccountManagerCanWithdraw() {
        double amount = 50.0;
        double initialBalance = this.person1Test.getBalance();
        assertTrue(AccountManager.withdrawal(this.person1Test, amount));
        // Check that the person balance is correct after withdrawing the given amount
        assertEquals(initialBalance - amount, this.person1Test.getBalance(), this.deltaCompare);
    }

    @Test
    public void AccountManagerCanNotWithdraw() {
        double amount = 1001.0;
        double initialBalance = this.person1Test.getBalance();
        assertFalse(AccountManager.withdrawal(this.person1Test, amount));
        // Check that the person balance is still the same as before the withdrawal attempt
        assertEquals(initialBalance, this.person1Test.getBalance(), this.deltaCompare);
    }

    @Test
    public void AccountManagerCanNotWithdrawNegative() {
        double amount = -10.0;
        double initialBalance = this.person1Test.getBalance();
        assertFalse(AccountManager.withdrawal(this.person1Test, amount));
        // Check that the person balance is still the same as before the withdrawal attempt
        assertEquals(initialBalance, this.person1Test.getBalance(), this.deltaCompare);
    }

    @Test
    public void AccountManagerCanTransfer() {
        double amount = 50.0;
        double person1InitialBalance = this.person1Test.getBalance();
        double person2InitialBalance = this.person2Test.getBalance();
        assertTrue(AccountManager.transfer(this.person1Test, this.person2Test, amount));
        // Check that both balances are correct after the transferring
        assertEquals(person1InitialBalance - amount, this.person1Test.getBalance(), this.deltaCompare);
        assertEquals(person2InitialBalance + amount, this.person2Test.getBalance(), this.deltaCompare);
    }

    @Test
    public void AccountManagerCanNotTransfer() {
        double amount = 50.0;
        double person1InitialBalance = this.person1Test.getBalance();
        double person2InitialBalance = this.person2Test.getBalance();
        assertFalse(AccountManager.transfer(this.person2Test, this.person1Test, amount));
        // Check that both balances are correct after the transferring
        assertEquals(person1InitialBalance, this.person1Test.getBalance(), this.deltaCompare);
        assertEquals(person2InitialBalance, this.person2Test.getBalance(), this.deltaCompare);
    }

    @Test
    public void AccountManagerCanNotTransferNegative() {
        double amount = -50.0;
        double person1InitialBalance = this.person1Test.getBalance();
        double person2InitialBalance = this.person2Test.getBalance();
        assertFalse(AccountManager.transfer(this.person1Test, this.person2Test, amount));
        // Check that both balances are correct after the transferring
        assertEquals(person1InitialBalance, this.person1Test.getBalance(), this.deltaCompare);
        assertEquals(person2InitialBalance, this.person2Test.getBalance(), this.deltaCompare);
    }

    @Test
    public void AccountManagerCanChangePin() {
        String oldPin = this.person1Test.getPin();
        String newPin = "abracadabra";
        assertTrue(AccountManager.changePin(this.person1Test, oldPin, newPin));
        // Check that the pin has been changed
        assertEquals(newPin, this.person1Test.getPin());
    }

    @Test
    public void AccountManagerCanNotChangePin() {
        String initialPin = this.person1Test.getPin();
        String oldPin = "ultrafakepin";
        String newPin = "abracadabra";
        assertFalse(AccountManager.changePin(this.person1Test, oldPin, newPin));
        // Check that the pin has been changed
        assertEquals(initialPin, this.person1Test.getPin());
    }
}
