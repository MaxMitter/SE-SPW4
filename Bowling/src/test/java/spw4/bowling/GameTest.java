package spw4.bowling;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game g;

    @BeforeEach
    void beforeEach() {
        g = new Game();
    }

    @Test
    void testGutterGame() {
        int count = 20;
        int pins = 0;

        rollMany(count, pins);

        assertEquals(0, g.score());
    }

    @Test
    void testAllOnes() {
        rollMany(20, 1);

        assertEquals(20, g.score());
    }

    private void rollMany(int count, int pins) {
        for (int i = 0; i < count; i++) {
            g.roll(pins);
        }
    }
}
