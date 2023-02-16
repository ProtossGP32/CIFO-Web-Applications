import org.labSe01Part1.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {
    private Person personTest;

    // Person initialization values
    private final String personName = "Luke";
    private final String personAddress = "Fake Street, 99";
    private final int personAge = 54;

    @Before
    public void initialize() {
        // Initialize a person
        this.personTest = new Person(this.personName, this.personAddress, this.personAge);
    }

    @Test
    public void PersonHasAllItsParameters() {
        assertEquals(this.personName, this.personTest.getName());
        assertEquals(this.personAddress, this.personTest.getAddress());
        assertEquals(this.personAge, this.personTest.getAge());
    }
}
