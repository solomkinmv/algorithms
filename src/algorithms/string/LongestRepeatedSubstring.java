package algorithms.string;

import java.util.Arrays;

import static algorithms.string.LongestCommonPrefix.longestCommonPrefix;

public class LongestRepeatedSubstring {

    public static String longestRepeatedSubstring(String str) {
        int length = str.length();

        String[] suffixes = new String[length];
        for (int i = 0; i < length; i++) {
            suffixes[i] = str.substring(i, length);
        }

        Arrays.sort(suffixes);

        String lrs = "";
        for (int i = 0; i < length - 1; i++) {
            int len = longestCommonPrefix(suffixes[i], suffixes[i + 1]);
            if (len > lrs.length()) {
                lrs = suffixes[i].substring(0, len);
            }
        }

        return lrs;
    }
}
