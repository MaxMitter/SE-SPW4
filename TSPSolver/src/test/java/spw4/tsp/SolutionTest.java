package spw4.tsp;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {

    @DisplayName("Checks Constructor with solutionData null throws Exception")
    @Test
    void ctorWhenSolDataNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Solution<Integer>(null, 0.0);
        });
    }

    @DisplayName("Checks Constructor with quality infinite throws Exception")
    @Test
    void ctorWhenQualityInfiniteThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Solution<Integer>(1, Double.POSITIVE_INFINITY);
        });
    }

    @DisplayName("Checks Constructor with valid params")
    @Test
    void ctorWhenParamsValidReturnsValidSolution() {
        Solution<Integer> sol = new Solution<>(1, 0.5);

        assertAll( () -> assertEquals(1, sol.getSolutionData()),
                   () -> assertEquals(0.5, sol.getQuality()));
    }

    @DisplayName("Checks compareTo with equal params")
    @Test
    void compareToParamsEqualReturnsZero() {
        Solution<Integer> sol1 = new Solution<>(1, 0.5);
        Solution<Integer> sol2 = new Solution<>(2, 0.5);

        assertEquals(0, sol1.compareTo(sol2));
    }

    @DisplayName("Checks compareTo with other larger")
    @Test
    void compareToOtherLargerEqualReturnsNegativZero() {
        Solution<Integer> sol1 = new Solution<>(1, 0.5);
        Solution<Integer> sol2 = new Solution<>(2, 0.7);

        assertEquals(-1, sol1.compareTo(sol2));
    }

    @DisplayName("Checks compareTo with other smaller")
    @Test
    void compareToOtherSmallerEqualReturnsZero() {
        Solution<Integer> sol1 = new Solution<>(1, 0.5);
        Solution<Integer> sol2 = new Solution<>(2, 0.3);

        assertEquals(1, sol1.compareTo(sol2));
    }

    @DisplayName("Checks toString returns valid string")
    @Test
    void toStringReturnsValidString() {
        Integer solution = 1;
        Double quality = 0.5;
        Solution<Integer> sol1 = new Solution<>(solution, quality);

        String expected = String.format("%.3f", quality) + "   " + solution.toString();

        assertEquals(expected, sol1.toString());
    }
}
