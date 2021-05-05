package spw4.exploration;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

public class SourceDemo {

    @ParameterizedTest(name = "String: {0}")
    @NullAndEmptySource
    @ValueSource(strings = {"abcd", "efgh"})
    void testSources(String s) {
        System.out.println(s);
        assertEquals("abcd", s);
    }
}
