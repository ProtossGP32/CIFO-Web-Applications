import org.labSe01Part1.AccountManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountManagerTest {
    private AccountManager accountManagerTest;

    private final double deltaCompare = 0.01;

    // Person 1 initialization values
    String person1Name = "Luke";
    String person1Address = "Fake Street, 99";
    int person1Age = 54;

    // Account 1 initialization values
    String account1Number = "ABCD0123456789";
    String pin1 = "1234";
    double balance1 = 1000.0;

    // Person 2 initialization values
    String person2Name = "Mariah";
    String person2Address = "Ultra Fake Boulevard, 22";
    int person2Age = 37;

    // Account 2 initialization values
    String account2Number = "XYZ0123456789";
    String pin2 = "4321";
    double balance2 = 20.0;

    @Before
    public void initialize() {

        // AccountManager initialization
        this.accountManagerTest = AccountManager.getInstance();
        this.accountManagerTest.clearAccounts();
        this.accountManagerTest.createAccount(this.person1Name, this.person1Address, this.person1Age, this.account1Number, this.pin1, this.balance1);
        this.accountManagerTest.createAccount(this.person2Name, this.person2Address, this.person2Age, this.account2Number, this.pin2, this.balance2);
    }

    @Test
    public void AccountManagerCanWithdraw() {
        double amount = 50.0;
        double initialBalance = accountManagerTest.getAccountBalance(this.person1Name, this.account1Number);
        assertTrue(accountManagerTest.withdrawal(this.person1Name, this.account1Number, amount));
        // Check that the account balance is correct after withdrawing the given amount
        double accountBalance = accountManagerTest.getAccountBalance(this.person1Name, this.account1Number);
        assertEquals(initialBalance - amount, accountBalance, this.deltaCompare);
    }

    @Test
    public void AccountManagerCanNotWithdraw() {
        double amount = 1001.0;
        double initialBalance = accountManagerTest.getAccountBalance(this.person1Name, this.account1Number);
        assertFalse(accountManagerTest.withdrawal(this.person1Name, this.account1Number, amount));
        // Check that the account balance is still the same as before the withdrawal attempt
        double accountBalance = accountManagerTest.getAccountBalance(this.person1Name, this.account1Number);
        assertEquals(initialBalance, accountBalance, this.deltaCompare);
    }

    @Test
    public void AccountManagerCanNotWithdrawNegative() {
        double amount = -10.0;
        double initialBalance = accountManagerTest.getAccountBalance(this.person1Name, this.account1Number);
        assertFalse(accountManagerTest.withdrawal(this.person1Name, this.account1Number, amount));
        // Check that the account balance is still the same as before the withdrawal attempt
        double accountBalance = accountManagerTest.getAccountBalance(this.person1Name, this.account1Number);
        assertEquals(initialBalance, accountBalance, this.deltaCompare);
    }

    @Test
    public void AccountManagerCanTransfer() {
        double amount = 50.0;
        double senderInitialBalance = accountManagerTest.getAccountBalance(this.person1Name, this.account1Number);
        double receiverInitialBalance = accountManagerTest.getAccountBalance(this.person2Name, this.account2Number);
        assertTrue(accountManagerTest.transfer(this.person1Name, this.account1Number, this.person2Name, this.account2Number, amount));
        // Check that both balances are correct after the transferring
        double senderFinalBalance = accountManagerTest.getAccountBalance(this.person1Name, this.account1Number);
        double receiverFinalBalance = accountManagerTest.getAccountBalance(this.person2Name, this.account2Number);
        assertEquals(senderInitialBalance - amount, senderFinalBalance, this.deltaCompare);
        assertEquals(receiverInitialBalance + amount, receiverFinalBalance, this.deltaCompare);
    }

    @Test
    public void AccountManagerCanNotTransfer() {
        double amount = 50.0;
        double senderInitialBalance = accountManagerTest.getAccountBalance(this.person1Name, this.account1Number);
        double receiverInitialBalance = accountManagerTest.getAccountBalance(this.person2Name, this.account2Number);
        assertFalse(accountManagerTest.transfer(this.person2Name, this.account2Number, this.person1Name, this.account1Number, amount));
        // Check that both balances are correct after the transferring
        double senderFinalBalance = accountManagerTest.getAccountBalance(this.person1Name, this.account1Number);
        double receiverFinalBalance = accountManagerTest.getAccountBalance(this.person2Name, this.account2Number);
        assertEquals(senderInitialBalance, senderFinalBalance, this.deltaCompare);
        assertEquals(receiverInitialBalance, receiverFinalBalance, this.deltaCompare);
    }

    @Test
    public void AccountManagerCanNotTransferNegative() {
        double amount = -50.0;
        double senderInitialBalance = accountManagerTest.getAccountBalance(this.person1Name, this.account1Number);
        double receiverInitialBalance = accountManagerTest.getAccountBalance(this.person2Name, this.account2Number);
        assertFalse(accountManagerTest.transfer(this.person1Name, this.account1Number, this.person2Name, this.account2Number, amount));
        // Check that both balances are correct after the transferring
        double senderFinalBalance = accountManagerTest.getAccountBalance(this.person1Name, this.account1Number);
        double receiverFinalBalance = accountManagerTest.getAccountBalance(this.person2Name, this.account2Number);
        assertEquals(senderInitialBalance, senderFinalBalance, this.deltaCompare);
        assertEquals(receiverInitialBalance, receiverFinalBalance, this.deltaCompare);
    }

    @Test
    public void AccountManagerCanDeposit() {
        double amount = 50.0;
        double initialBalance = accountManagerTest.getAccountBalance(this.person1Name, this.account1Number);
        assertTrue(accountManagerTest.deposit(this.person1Name, this.account1Number, amount));
        // Check that the account balance is correct after withdrawing the given amount
        double finalBalance = accountManagerTest.getAccountBalance(this.person1Name, this.account1Number);
        assertEquals(initialBalance + amount, finalBalance, this.deltaCompare);
    }

    @Test
    public void AccountManagerCanChangePin() {
        String oldPin = this.pin1;
        String newPin = "abracadabra";
        assertTrue(accountManagerTest.changePin(this.person1Name, this.account1Number, oldPin, newPin));
    }

    @Test
    public void AccountManagerCanNotChangePin() {
        String oldPin = "ultrafakepin";
        String newPin = "abracadabra";
        assertFalse(accountManagerTest.changePin(this.person1Name, this.account1Number, oldPin, newPin));
    }
}
