package logic;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class MovieManagerTest {
    private String className = "MovieManager";
    Faker faker;

    @BeforeEach
    public void Initialize() {
        faker = new Faker();
    }

    @Test
    public void MovieManagerClassExists() {
        fail("Test pending to implement");
    }
}
