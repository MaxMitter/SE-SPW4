package spw4.tsp;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InversionMutatorTests {

    @DisplayName("Passing null to the mutate Function throws an exception")
    @Test
    void mutateWhenPermutationNullThrowsException() {
        InversionMutator mutator = new InversionMutator();

        assertThrows(IllegalArgumentException.class, () -> {
            mutator.mutate(null);
        });
    }

    @DisplayName("Passing a valid Permutation to the mutate function returns a mutated Permutation")
    @Test
    void mutateWhenValidPermutationReturnsMutatedPermutation() {
        Permutation perm = new Permutation(10);
        InversionMutator mutator = new InversionMutator();
        TSPSolver.random = new RandomStub<>(List.of(3, 3));
        int[] expected = {0, 1, 2, 7, 6, 5, 4, 3, 8, 9};

        Permutation result = mutator.mutate(perm);

        assertArrayEquals(expected, result.getValues());
    }
}
