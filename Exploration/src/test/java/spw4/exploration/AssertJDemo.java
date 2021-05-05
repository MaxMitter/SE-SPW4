package spw4.exploration;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class AssertJDemo {

    @Test
    void hamcrestTest() {
        assertThat("hello assertJ").isNotEmpty();
        assertThat("hello assertJ").contains("hello").contains("assertJ");
    }
}
