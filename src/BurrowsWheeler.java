import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.Arrays;

public class BurrowsWheeler {

    private static final int ASCII_SIZE = 256;

    // apply Burrows-Wheeler transform, reading from standard input and writing to standard output
    public static void transform() {
        String string = BinaryStdIn.readString();
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(string);

        // find position of original suffix
        for (int i = 0; i < string.length(); i++) {
            int originalSuffixIndex = circularSuffixArray.index(i);
            if (originalSuffixIndex == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }

        // find last chars in sorted suffixes
        for (int i = 0; i < string.length(); i++) {
            int originalSuffixIndex = circularSuffixArray.index(i);
            int lastCharIndex = originalSuffixIndex - 1;
            BinaryStdOut.write(charAt(string, lastCharIndex));
        }

        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();

        char[] t = BinaryStdIn.readString().toCharArray();
        int[] next = new int[t.length];
        char[] sorted = new char[t.length];

        int[] count = new int[ASCII_SIZE + 1];
        for (int i = 0; i < t.length; i++) {
            count[t[i] + 1]++;
        }

        for (int r = 0; r < ASCII_SIZE; r++) {
            count[r + 1] += count[r];
        }

        for (int i = 0; i < t.length; i++) {
            next[count[t[i]]] = i; // like radix sort, but instead value set index
            sorted[count[t[i]]] = t[i];
            count[t[i]]++;
        }

        System.out.println(Arrays.toString(next));

        int p = first;
        for (int i = 0; i < t.length; i++) {
            BinaryStdOut.write(sorted[p]);
            p = next[p];
        }
        BinaryStdOut.close();
    }

    private static char charAt(String str, int index) {
        if (index < 0) {
            index += str.length();
        }

        if (index >= str.length()) {
            index -= str.length();
        }

        return str.charAt(index);
    }

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if ("-".equals(args[0])) {
            transform();
        } else {
            inverseTransform();
        }
    }
}
