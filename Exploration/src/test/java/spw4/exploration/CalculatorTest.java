package spw4.exploration;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @DisplayName("Calculator.square with param 0 returns 0")
    @Test
    void squareWithNullReturnsNull() {
        //arrange
        var calc = new Calculator();
        //act
        var result = calc.square(0f);
        //assert
        assertEquals(0f, result);
    }

    @Test
    void absWithNegativeReturnsPositive() {
        var calc = new Calculator();
        var result = calc.abs(-3f);
        assertEquals(3f, result);
    }
}
