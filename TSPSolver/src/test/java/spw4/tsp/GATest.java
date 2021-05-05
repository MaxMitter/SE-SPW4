package spw4.tsp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Random;

@ExtendWith(MockitoExtension.class)
public class GATest {
    @Mock
    Problem<Permutation> problem;
    @Mock
    Selector<Permutation> selector;
    @Mock
    Crossover<Permutation> crossover;
    @Mock
    Mutator<Permutation> mutator;
    @Mock
    Random random;

    @Test
    void executeReturnsBestSolution() {
        int popSize = 5;
        int iterations = 5;
        double mutationRate = 0.05;

        Permutation permutation = new Permutation(2);
        Solution<Permutation> solution = new Solution<>(permutation, 1.0);
        Solution<Permutation> best = new Solution<>(permutation, 0.0);

        when(random.nextDouble())
                .thenReturn(1.0)
                .thenReturn(0.0)
                .thenReturn(1.0)
                .thenReturn(0.0)
                .thenReturn(1.0);
        when(problem.createRandomSolution()).thenReturn(solution);
        when(selector.select(any(), eq(2))).thenReturn(List.of(solution, solution));
        when(crossover.cross(any(), any())).thenReturn(permutation);
        when(mutator.mutate(any())).thenReturn(permutation);
        when(problem.evaluate(any())).thenReturn(best).thenReturn(solution);
        TSPSolver.random = random;

        var sut = new GA<Permutation>(problem, popSize, iterations, selector, crossover, mutator, mutationRate);

        var result = sut.execute();

        int expectedNumberOfChildren = (iterations - 1) * (popSize - 1);
        int expectedNumberOfMutations = 2;

        assertAll(
                () -> assertSame(best, result),
                () -> verify(problem, times(popSize)).createRandomSolution(),
                () -> verify(problem, times(expectedNumberOfChildren)).evaluate(any()),
                () -> verify(selector, times(expectedNumberOfChildren)).select(any(), eq(2)),
                () -> verify(crossover, times(expectedNumberOfChildren)).cross(any(), any()),
                () -> verify(mutator, times(expectedNumberOfMutations)).mutate(any())
        );
    }
}
