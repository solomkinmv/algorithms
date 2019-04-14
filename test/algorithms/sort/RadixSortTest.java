package algorithms.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class RadixSortTest {

    @Test
    void shouldSortInputArray() {
        int[] original = {1, 4, 9, 3, 4, 1, 5, 6, 3, 9, 4, 5, 3, 2, 9, 7, 0, 1, 6};
        int[] arrayToSort = Arrays.copyOf(original, original.length);

        Arrays.sort(original);
        RadixSort.sort(arrayToSort, 10);

        assertThat(arrayToSort)
                .isEqualTo(original);
    }
}