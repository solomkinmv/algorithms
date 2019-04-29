package algorithms.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class MsdRadixSortTest {

    @Test
    void shouldSortInputArray() {
        String[] original = {"hello", "abbdi", "bsdfi", "zcxvd", "qwedf", "nvdfg", "abcde", "edcba"};
        String[] arrayToSort = Arrays.copyOf(original, original.length);

        Arrays.sort(original);
        MsdRadixSort.sort(arrayToSort);

        assertThat(arrayToSort)
                .isEqualTo(original);
    }
}