package root;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;


class SortSomePrefixLengthOneTest {

    @Test
    void test() {
        var sortSomePrefixLengthOne = new SortSomePrefixLengthOne('A');

        var expected = Collections.emptyList();

        var actual = sortSomePrefixLengthOne.exec(Collections.emptyList());
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void test2() {

        var sortSomePrefixLengthOne = new SortSomePrefixLengthOne('A');

        var expected = expectedData();

        var actual = sortSomePrefixLengthOne.exec(testData());
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
                "特定営業日A0営業日",
                "特定営業日A3営業日",
                "特定営業日A5営業日",
                "特定営業日AA0営業日",
                "特定営業日AA4営業日",
                "特定営業日AA5営業日",
                "特定営業日AAA2営業日",
                "特定営業日AAA5営業日",
                "特定営業日a営業日",
                "特定営業日営業日"
        );
    }

    private List<String> testData() {

        return List.of(
                "特定営業日5営業日",
                "特定営業日0営業日",
                "特定営業日A5営業日",
                "特定営業日AA5営業日",
                "特定営業日AAA5営業日",
                "特定営業日営業日",
                "特定営業日11営業日",
                "特定営業日1営業日",
                "特定営業日2営業日",
                "特定営業日A3営業日",
                "特定営業日A0営業日",
                "特定営業日AA0営業日",
                "特定営業日AA4営業日",
                "特定営業日AAA2営業日",
                "特定営業日a営業日",
                "特定営業日10営業日",
                "特定営業日9営業日"
        );
    }
}