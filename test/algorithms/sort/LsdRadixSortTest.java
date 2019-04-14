package algorithms.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LsdRadixSortTest {

    @Test
    void shouldSortInputArray() {
        String[] original = {"hello", "abbdi", "bsdfi", "zcxvd", "qwedf", "nvdfg", "abcde", "edcba"};
        String[] arrayToSort = Arrays.copyOf(original, original.length);

        Arrays.sort(original);
        LsdRadixSort.sort(arrayToSort, 5);

        assertThat(arrayToSort)
                .isEqualTo(original);
    }
}