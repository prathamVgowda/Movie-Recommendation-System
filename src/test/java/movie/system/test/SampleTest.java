package movie.system.test;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SampleTest {

    @BeforeAll
    void initAll() {
        System.out.println("Runs once before all tests");
        // e.g., connect to in-memory DB
    }

    @BeforeEach
    void init() {
        System.out.println("Runs before each test");
        // e.g., initialize test data or mocks
    }

    @Test
    void testOne() {
        System.out.println("Running testOne");
    }

    @Test
    void testTwo() {
        System.out.println("Running testTwo");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Runs after each test");
        // e.g., clean up test data
    }

    @AfterAll
    void tearDownAll() {
        System.out.println("Runs once after all tests");
        // e.g., close DB connection
    }
}
