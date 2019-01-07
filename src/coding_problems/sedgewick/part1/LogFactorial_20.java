package coding_problems.sedgewick.part1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogFactorial_20 {

    @Test
    void tests() {
        assertEquals(120, factorial(5));
    }

    static long factorial(int n) {
        if (n == 1 || n == 0) return 1;

        return n * factorial(n - 1);
    }
}
