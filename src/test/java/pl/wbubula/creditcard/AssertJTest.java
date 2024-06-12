package pl.wbubula.creditcard;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

public class AssertJTest {
    @Test
    void helloAssert(){
        String hello = "Hello World";
        assertThat(hello).contains("Hello World");
    }

    @Test
    void testSomeListExpression(){
        var names = Collections.singleton("Wojtek");
        assertThat(names)
                .isUnmodifiable()
                .hasSize(1)
                .containsAll(Arrays.asList("Wojtek"));

    }
}
