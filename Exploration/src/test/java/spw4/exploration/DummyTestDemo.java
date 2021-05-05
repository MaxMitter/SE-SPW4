package spw4.exploration;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledForJreRange;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import static org.junit.jupiter.api.condition.JRE.*;
import static org.junit.jupiter.api.condition.OS.*;

class DummyTestDemo {
    //@DisplayName("this is a dummy test")
    @Test
    void dummyTest() {
        assertEquals(42, 21 + 21);

    }

    @Test
    void testOnCIServer() {
        assumeTrue(System.getenv("ENVIRONMENT").equals("CI"));
        assertEquals(42, 21 + 21);
    }

    @Test
    void conditionalTest() {
        assumingThat(System.getenv("ENVIRONMENT").equals("DEV"), () -> {
            assertEquals(42, 40 + 2);
        });
        assertEquals(42, 21 + 21);
    }

    @DisabledOnOs(LINUX)
    @Test
    void testOnlyOnWindows() {

        assertEquals(42, 21 * 2);
    }

    @Test
    @EnabledForJreRange(min = JAVA_10, max = JAVA_11)
    void testOnlyOnJRE10AndJRE11() {

    }
}
