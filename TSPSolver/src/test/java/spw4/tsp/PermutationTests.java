package spw4.tsp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Random;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class PermutationTests {

    @ParameterizedTest(name = "length = {0}")
    @DisplayName("Checks Constructor with different lengths")
    @ValueSource(ints = {2, 5, 10})
    void ctorWhenLenthIsValidReturnPermutation(int length) {
        int[] expected = IntStream.range(0, length).toArray();

        int[] result = new Permutation(length).getValues();

        assertArrayEquals(expected, result);
    }

    @DisplayName("Checks Constructor with invalid lengths")
    @ParameterizedTest(name = "invalid length = {0}")
    @ValueSource(ints = {-1, 0, 1})
    void ctorWhenLenthIsInValidThrowException(int length) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Permutation(length);
        });
    }

    @DisplayName("Checks Constructor with int array")
    @Test
    void ctorWhenParamIsIntArrayReturnPermutation() {
        int[] expected = IntStream.range(0, 10).toArray();

        int[] result = new Permutation(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}).getValues();

        assertArrayEquals(expected, result);
    }

    @DisplayName("Checks Constructor with empty int array")
    @Test
    void ctorWhenIntArrayIsEmptyThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Permutation(new int[]{});
        });
    }

    @DisplayName("Checks Constructor with int array with negative numbers")
    @Test
    void ctorWhenParamIsIntArrayNegativeReturnPermutation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Permutation(new int[]{-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        });
    }

    @DisplayName("Checks Constructor with int array with double values")
    @Test
    void ctorWhenParamIsIntArrayDoubleValuesReturnPermutation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Permutation(new int[]{0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        });
    }

    @DisplayName("Checks Constructor with uninitialized int array")
    @Test
    void ctorWhenIntArrayIsInValidThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            int[] arr = null;
            new Permutation(arr);
        });
    }

    @DisplayName("Checks Constructor with invalid permutation")
    @Test
    void ctorWhenIntArrayIsInValidPermutationThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            int[] arr = {0, 1, 3, 4, 5, 7, 8, 10};
            new Permutation(arr);
        });
    }

    @DisplayName("Checks .toString()")
    @Test
    void toStringWhenPermutationIsValidReturnsString() {
        Permutation perm = new Permutation(10);
        String expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]";

        String result = perm.toString();

        assertEquals(expected, result);
    }

    @DisplayName("Checks createRandom with invalid lengths")
    @ParameterizedTest(name = "invalid length = {0}")
    @ValueSource(ints = {-1, 0, 1})
    void createRandomWhenLengthInvalidThrowsException(int length) {
        assertThrows(IllegalArgumentException.class, () -> {
            Permutation.createRandom(length);
        });
    }

    @DisplayName("Checks createRandom with valid lengths")
    @Test
    void createRandomWhenLengthValidReturnsPermutation() {
        int[] expected = {4, 0, 1, 2, 3};
        TSPSolver.random = new RandomStub<>(List.of(4, 3, 2, 1, 0));

        var perm = Permutation.createRandom(5);

        assertArrayEquals(expected, perm.getValues());
    }
}
