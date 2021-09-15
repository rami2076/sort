package root;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortSomePrefixIsZeroTest {

    @Test
    void test() {
        var sortSomePrefixIsZero = new SortSomePrefixIsZero();

        var expected = Collections.emptyList();

        var actual = sortSomePrefixIsZero.exec(Collections.emptyList());
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void test2() {

        var sortSomePrefixIsZero = new SortSomePrefixIsZero();

        var expected = expectedData();

        var actual = sortSomePrefixIsZero.exec(testData());
        actual.forEach(System.out::println);

        Assertions.assertEquals(expected, actual);

    }

    private List<String> expectedData() {

        return List.of(
                "特定営業日0営業日",
                "特定営業日1営業日",
                "特定営業日2営業日",
                "特定営業日5営業日",
                "特定営業日9営業日",
                "特定営業日10営業日",
                "特定営業日11営業日",
                "特定営業日00営業日",
                "特定営業日03営業日",
                "特定営業日05営業日",
                "特定営業日000営業日",
                "特定営業日004営業日",
                "特定営業日005営業日",
                "特定営業日0000営業日",
                "特定営業日0002営業日",
                "特定営業日0005営業日",
                "特定営業日a営業日",
                "特定営業日営業日"
        );
    }

    private List<String> testData() {

        return List.of(
                "特定営業日5営業日",
                "特定営業日0営業日",
                "特定営業日05営業日",
                "特定営業日005営業日",
                "特定営業日0005営業日",
                "特定営業日営業日",
                "特定営業日11営業日",
                "特定営業日1営業日",
                "特定営業日2営業日",
                "特定営業日03営業日",
                "特定営業日00営業日",
                "特定営業日000営業日",
                "特定営業日004営業日",
                "特定営業日0002営業日",
                "特定営業日a営業日",
                "特定営業日10営業日",
                "特定営業日9営業日",
                "特定営業日0000営業日"
        );
    }
}