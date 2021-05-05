package spw4.tsp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class CycleCrossoverTests {

    @Test
    @DisplayName("CyclicCrossover.cross when p1 and p2 are valid and returns valid result")
    void crossWhenBothParentsAreValidReturnsValidResult() {
        TSPSolver.random = new RandomStub<>(List.of(0, 1, 7, 2, 0));
        var parent1 = new Permutation(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        var parent2 = new Permutation(new int[] {2, 5, 6, 0, 7, 1, 3, 8, 4, 9});

        int[] expected = new int[] {0, 5, 2, 3, 7, 1, 6, 8, 4, 9};

        var sut = new CyclicCrossover();
        int[] results = sut.cross(parent1, parent2).getValues();

        assertArrayEquals(expected, results);
    }
}
