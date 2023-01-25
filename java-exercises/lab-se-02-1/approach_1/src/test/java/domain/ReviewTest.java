package domain;

import com.github.javafaker.Buffy;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ReviewTest {
    private String className = "Review";
    Faker faker;

    @BeforeEach
    public void initialize() {
        faker = new Faker();
    }

    @Test
    public void reviewClassExists() {
        fail("Test pending to implement");
    }

    @Test
    public void reviewHasConstructorWithoutParameters() {
        fail("Test pending to implement");
    }

    @Test
    public void reviewHasRequiredAttributes() {
        fail("Test pending to implement");
    }
}
