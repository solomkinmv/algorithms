package coding_problems.sedgewick.part1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToBinaryString_9 {

    @Test
    void toBinaryStringTest() {
        assertEquals("1", toBinaryString(1));
        assertEquals("111", toBinaryString(7));
        assertEquals("1000", toBinaryString(8));
        assertEquals("101000", toBinaryString(40));

    }

    static String toBinaryString(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.insert(0, n % 2);
            n /= 2;
        }
        return sb.toString();
    }
}
