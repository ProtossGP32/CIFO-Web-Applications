package domain;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class DirectorTest {
    private String className = "Director";
    Faker faker;

    @BeforeEach
    public void initialize() {
        faker = new Faker();
    }

    @Test
    public void directorClassExists() {
        fail("Test pending to implement");
    }

    @Test
    public void directorHasConstructorWithoutParameters() {
        fail("Test pending to implement");
    }

    @Test
    public void directorHasRequiredAttributes() {
        fail("Test pending to implement");
    }
}
