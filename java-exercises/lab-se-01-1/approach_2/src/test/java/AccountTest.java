import org.labSe01Part1.Account;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AccountTest {
    private Account accountTest;

    // Account initialization values
    private String accountNumber = "ABCD0123456789";
    private String pin = "1234";
    private double balance = 1000.0;
    private double deltaCompare = 0.01;

    @Before
    public void initialize() {
        this.accountTest = new Account(this.accountNumber, this.pin, this.balance);
    }

    @Test
    public void AccountHasAllItsParameters() {
        assertEquals(this.accountNumber, this.accountTest.getAccountNumber());
        assertEquals(this.pin, this.accountTest.getPin());
        assertEquals(this.balance, this.accountTest.getBalance(), this.deltaCompare);
    }

    @Test
    public void AccountsAreEqual() {
        Account secondAccount = new Account(this.accountNumber, this.pin, this.balance);
        assertEquals(this.accountTest, secondAccount);
    }

    @Test
    public void AccountsAreNotEqual() {
        Account secondAccount = new Account(this.accountNumber, this.pin, this.balance + 100);
        assertNotEquals(this.accountTest, secondAccount);

        secondAccount = new Account(this.accountNumber, "4321", this.balance);
        assertNotEquals(this.accountTest, secondAccount);

        secondAccount = new Account("XYZ9876543210", this.pin, this.balance);
        assertNotEquals(this.accountTest, secondAccount);
    }
}
