package algorithms.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ThreeWayRadixQuicksortTest {

    @Test
    void shouldSortInputArray() {
        String[] original = {
                "hello",
                "abbdi",
                "wehfsdlkafjav",
                "bsdfi",
                "zcxvd",
                "askdlfhasdfvadsw",
                "qwedf",
                "nvdfg",
                "abcde",
                "asdfhoewfsdfsf",
                "sdf",
                "ab",
                "bca",
                "edcba"};
        String[] arrayToSort = Arrays.copyOf(original, original.length);

        Arrays.sort(original);
        ThreeWayRadixQuicksort.sort(arrayToSort);

        assertThat(arrayToSort)
                .isEqualTo(original);
    }
}