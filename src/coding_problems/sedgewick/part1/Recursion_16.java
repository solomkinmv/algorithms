package coding_problems.sedgewick.part1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by max on 5/30/17.
 */
public class Recursion_16 {
    @Test
    void tests() {
        assertEquals("311361142246", exR(6));
    }

    static String exR(int n) {
        if (n <= 0) return "";

        return exR(n - 3) + n + exR(n - 2) + n;
    }
}
