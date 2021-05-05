package spw4.exploration;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class HamcrestDemo {

    @Test
    void hamcrestTest() {
        assertThat("hello hamcrest", is(not(emptyString())));
    }
}
