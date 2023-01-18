package domain;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class DirectorTest {
    private String className = "Director";
    Faker faker;

    @BeforeEach
    public void Initialize() {
        faker = new Faker();
    }

    @Test
    public void DirectorClassExists() {
        fail("Test pending to implement");
    }

    @Test
    public void DirectorHasConstructorWithoutParameters() {
        fail("Test pending to implement");
    }

    @Test
    public void DirectorHasRequiredAttributes() {
        fail("Test pending to implement");
    }
}
