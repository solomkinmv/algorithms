package coding_problems.sedgewick.part1;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci_19 {
    static Map<Integer, Long> cache = new HashMap<>();

    static long F(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;

        if (cache.containsKey(N)) {
            return cache.get(N);
        }
        long value = F(N - 1) + F(N - 2);
        cache.put(N, value);
        return value;
    }

    public static void main(String[] args) {
        for (int N = 0; N < 100; N++)
            StdOut.println(N + " " + F(N));
    }
}
