package coding_problems.sedgewick.part1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Logarithm_14 {

    @Test
    void tests() {
        assertEquals(3, lg(8));
        assertEquals(3, lg(9));
    }
    static int lg(int n) {
        int log = 0;
        int power = 1;
        while (power <= n) {
            power *= 2;
            log++;
        }

        return log - 1;
    }
}
