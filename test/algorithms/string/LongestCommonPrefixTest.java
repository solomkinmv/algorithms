package algorithms.string;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LongestCommonPrefixTest {

    @Test
    void returnsZeroIfNoCommonPrefix() {
        int actualResult = LongestCommonPrefix.longestCommonPrefix("abcd", "bcd");

        assertThat(actualResult)
                .isZero();
    }

    @Test
    void returnsCorrectResultIfOneStringIsPrefixOfAnotherOne() {
        int actualResult = LongestCommonPrefix.longestCommonPrefix("abcd", "ab");

        assertThat(actualResult)
                .isEqualTo(2);
    }

    @Test
    void returnsCorrectResultForStringsWithCommonPrefix() {
        int actualResult = LongestCommonPrefix.longestCommonPrefix("abcd", "abdbe");

        assertThat(actualResult)
                .isEqualTo(2);
    }
}