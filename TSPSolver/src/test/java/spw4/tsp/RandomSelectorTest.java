package spw4.tsp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomSelectorTest {
    @DisplayName("Checks select with solution list null")
    @Test
    void selectWhenListNullThrowException() {
        List<Solution<Integer>> solList = null;
        RandomSelector<Integer> selector = new RandomSelector<>();

        assertThrows(IllegalArgumentException.class, () -> {
            selector.select(solList, 1);
        });
    }

    @DisplayName("Checks select with empty solution list")
    @Test
    void selectWhenListEmptyThrowException() {
        List<Solution<Integer>> solList = new LinkedList<>();
        RandomSelector<Integer> selector = new RandomSelector<>();

        assertThrows(IllegalArgumentException.class, () -> {
            selector.select(solList, 1);
        });
    }

    @DisplayName("Checks select with negativ parents")
    @Test
    void selectWhenParentsNegativThrowException() {
        List<Solution<Integer>> solList = new LinkedList<>();
        solList.add(new Solution<>(1, 0.5));
        RandomSelector<Integer> selector = new RandomSelector<>();

        assertThrows(IllegalArgumentException.class, () -> {
            selector.select(solList, -1);
        });
    }

    @DisplayName("Checks select with valid params single Solution")
    @Test
    void selectWhenParamsValidSingleSolutionReturnsSelection() {
        List<Solution<Integer>> solList = new LinkedList<>();
        Solution<Integer> sol = new Solution<>(1, 0.5);
        solList.add(sol);
        RandomSelector<Integer> selector = new RandomSelector<>();
        TSPSolver.random = new RandomStub<>(List.of(0, 1, 2, 3));

        var result = selector.select(solList, 1);

        assertEquals(0, sol.compareTo(result.get(0)));
    }

    @DisplayName("Checks select with valid params multiple Solutions")
    @ParameterizedTest(name = "expected solution: 0.{0}")
    @ValueSource(ints = {0, 1, 2, 3})
    void selectWhenParamsValidMultipleSolutionsReturnsSelection(int expectedValue) {
        List<Solution<Integer>> solList = new LinkedList<>();
        Solution<Integer> expected = new Solution<>(expectedValue, (double)expectedValue / 10);
        int MAX_SOLUTIONS = 5;

        //add solutions before expectedValue
        for (int i = 0; i < expectedValue; i++) {
            solList.add(new Solution<>(i, (double)i / 10));
        }

        solList.add(expected);

        //add solutions after expectedValue
        for (int i = expectedValue + 1; i < MAX_SOLUTIONS; i++) {
            solList.add(new Solution<>(i, (double)i / 10));
        }

        RandomSelector<Integer> selector = new RandomSelector<>();
        TSPSolver.random = new RandomStub<>(List.of(expectedValue));

        var result = selector.select(solList, 1);

        assertEquals(0, expected.compareTo(result.get(0)));
    }
}
