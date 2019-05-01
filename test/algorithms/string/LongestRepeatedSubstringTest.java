package algorithms.string;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LongestRepeatedSubstringTest {

    @Test
    void findsLongestRepeatedSubstring() {
        String actualResult = LongestRepeatedSubstring.longestRepeatedSubstring("abcdefg");

        assertThat(actualResult)
                .isEmpty();
    }

    @Test
    void returnsEmptyStringIfNoRepeatedSubstring() {
        String actualResult = LongestRepeatedSubstring.longestRepeatedSubstring("aacaagtttacaagc");

        assertThat(actualResult)
                .isEqualTo("acaag");
    }
}