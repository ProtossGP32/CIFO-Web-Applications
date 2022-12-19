import org.example.Account;
import org.example.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {
    private Person personTest;
    private Account accountTest;

    // Person initialization values
    private String personName = "Luke";
    private String personAddress = "Fake Street, 99";
    private int personAge = 54;

    // Account initialization values
    private String accountNumber = "ABCD0123456789";
    private String pin = "1234";
    private double balance = 1000.0;

    @Before
    public void initialize() {
        // Initialize an account
        this.accountTest = new Account(this.accountNumber, this.pin, this.balance);
        // Initialize a person
        this.personTest = new Person(this.personName, this.personAddress, this.personAge, this.accountTest);
    }

    @Test
    public void PersonHasAllItsParameters() {
        assertEquals(this.personName, this.personTest.getName());
        assertEquals(this.personAddress, this.personTest.getAddress());
        assertEquals(this.personAge, this.personTest.getAge());
        assertEquals(this.accountTest, this.personTest.getAccount());
    }
}
