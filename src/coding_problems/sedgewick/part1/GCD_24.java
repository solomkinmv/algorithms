package coding_problems.sedgewick.part1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GCD_24 {

    @Test
    void tests() {
        assertEquals(3, gcd(6, 3));
        assertEquals(2, gcd(6, 4));
        assertEquals(1, gcd(91, 10));
        assertEquals(1, gcd(3, 2));
        assertEquals(3, gcd(105, 24));
        assertEquals(1, gcd(1111111, 1234567));
    }

    static int gcd(int p, int q) {
        System.out.println(p + " " + q);
        if (q == 0) {
            return p;
        }

        return gcd(q, p % q);
    }
}
