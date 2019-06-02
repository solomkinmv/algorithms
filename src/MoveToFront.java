import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    private static final int ASCII_SIZE = 256;

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        char[] alphabet = initAlphabet(); // pos -> char
        int[] charMapping = initCharMapping(); // char -> pos

        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            int pos = charMapping[ch];

            BinaryStdOut.write((char) pos);
            placeCharToBeginning(alphabet, charMapping, ch, pos);
        }
        BinaryStdOut.close();
    }

    private static void placeCharToBeginning(char[] alphabet, int[] charMapping, char c, int index) {
        for (int i = index; i > 0; i--) {
            alphabet[i] = alphabet[i - 1];
            charMapping[alphabet[i]] = i;
        }
        alphabet[0] = c;
        charMapping[c] = 0;
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char[] alphabet = initAlphabet(); // pos -> char
        int[] charMapping = initCharMapping(); // char -> pos

        while (!BinaryStdIn.isEmpty()) {
            int pos = BinaryStdIn.readChar();
            char ch = alphabet[pos];

            BinaryStdOut.write(ch);
            placeCharToBeginning(alphabet, charMapping, ch, pos);
        }

        BinaryStdOut.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if ("-".equals(args[0])) {
            encode();
        } else {
            decode();
        }
    }

    private static char[] initAlphabet() {
        char[] alphabet = new char[ASCII_SIZE];
        for (int i = 0; i < ASCII_SIZE; i++) {
            alphabet[i] = (char) i;
        }
        return alphabet;
    }

    private static int[] initCharMapping() {
        int[] charMapping = new int[ASCII_SIZE];
        for (int i = 0; i < ASCII_SIZE; i++) {
            charMapping[i] = i;
        }
        return charMapping;
    }
}
