package coding_problems.sedgewick.part1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchRecursive_22 {

    @Test
    void tests() {
        assertEquals(2, search(new int[] {1, 2, 3, 4, 5}, 3));
        assertEquals(0, search(new int[] {1, 2, 3, 4, 5}, 1));
        assertEquals(4, search(new int[] {1, 2, 3, 4, 5}, 5));
        assertEquals(3, search(new int[] {1, 2, 3, 4, 5}, 4));
    }

    static int search(int[] array, int target) {
        return search(array, 0, array.length - 1, target, 0);
    }

    static int search(int[] array, int lo, int hi, int target, int level) {
        if (lo > hi) {
            return -1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) sb.append("\t");
        sb.append(lo).append(" ").append(hi);
        System.out.println(sb);

        int mi = lo + (hi - lo) / 2;

        if (array[mi] < target) {
            return search(array, mi + 1, hi, target, level + 1);
        } else if (array[mi] > target) {
            return search(array, lo, mi - 1, target, level + 1);
        }

        return mi;
    }
}
