package spw4.exploration;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class SetupTeardownDemo {

    private Calculator calc;

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("    starting test");
        calc = new Calculator();
    }

    @AfterEach
    void afterEach() {
        System.out.println("    ending test");
        calc = null;
    }

    @AfterAll
    static void afterAll(){
        System.out.println("done testing");
    }

    @DisplayName("Calculator.square with param 0 returns 0")
    @Test
    void squareWithNullReturnsNull() {
        //act
        var result = calc.square(0f);
        //assert
        assertEquals(0f, result);
    }

    @Test
    void absWithNegativeReturnsPositive() {
        var result = calc.abs(-3f);
        assertEquals(3f, result);
    }
}
