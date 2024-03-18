package pl.wbubula.creditcard;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

public class AssertJTest {
    @Test
    void helloAssert(){
        var hello = "Hello World";

        assertThat(hello).containsOnlyDigits();

    }

    @Test
    void testSomeListExpression(){
        var names = Collections.singleton("Jakub");

        assertThat(names)
                .isUnmodifiable()
                .hasSize(1)
                .containsAll(Arrays.asList("Jakub","Michal"));

    }
}
