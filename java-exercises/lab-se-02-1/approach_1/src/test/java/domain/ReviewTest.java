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
    public void Initialize() {
        faker = new Faker();
    }

    @Test
    public void ReviewClassExists() {
        fail("Test pending to implement");
    }

    @Test
    public void ReviewHasConstructorWithoutParameters() {
        fail("Test pending to implement");
    }

    @Test
    public void ReviewHasRequiredAttributes() {
        fail("Test pending to implement");
    }
}
