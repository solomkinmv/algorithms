package coding_problems.sedgewick.part1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Histogram_15 {

    static int[] histogram(int[] array, int m) {
        int[] result = new int[m];

        for (int el : array) {
            if (el > m) {
                continue;
            }

            result[el]++;
        }

        return result;
    }

    @Test
    void tests() {
        assertArrayEquals(new int[]{1, 2, 3}, histogram(new int[]{0, 1, 2, 2, 1, 2}, 3));
    }
}
