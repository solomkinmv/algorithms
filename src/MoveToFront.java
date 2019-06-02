import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.ArrayList;
import java.util.List;

public class MoveToFront {

    private static final int ASCII_SIZE = 256;

    // todo: optimize this shit

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        List<Character> alphabet = initAlphabet();
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            int index = alphabet.indexOf(c);
            alphabet.remove(index);
            alphabet.add(0, c);
            BinaryStdOut.write(index);
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        List<Character> alphabet = initAlphabet();
        while (!BinaryStdIn.isEmpty()) {
            int pos = BinaryStdIn.readInt();
            char ch = alphabet.get(pos);
            BinaryStdOut.write(ch);
            alphabet.remove(pos);
            alphabet.add(0, ch);
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

    private static List<Character> initAlphabet() {
        List<Character> chars = new ArrayList<>(ASCII_SIZE);
        for (int i = 0; i < ASCII_SIZE; i++) {
            chars.add((char) i);
        }
        return chars;
    }
}
