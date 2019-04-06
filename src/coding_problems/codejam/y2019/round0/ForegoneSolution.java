package coding_problems.codejam.y2019.round0;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Problem
 * Someone just won the Code Jam lottery, and we owe them N jamcoins! However, when we tried to print out an oversized check, we encountered a problem. The value of N, which is an integer, includes at least one digit that is a 4... and the 4 key on the keyboard of our oversized check printer is broken.
 * Fortunately, we have a workaround: we will send our winner two checks for positive integer amounts A and B, such that neither A nor B contains any digit that is a 4, and A + B = N. Please help us find any pair of values A and B that satisfy these conditions.
 * Input
 * The first line of the input gives the number of test cases, T. T test cases follow; each consists of one line with an integer N.
 * Output
 * For each test case, output one line containing Case #x: A B, where x is the test case number (starting from 1), and A and B are positive integers as described above.
 * It is guaranteed that at least one solution exists. If there are multiple solutions, you may output any one of them. (See "What if a test case has multiple correct solutions?" in the Competing section of the FAQ. This information about multiple solutions will not be explicitly stated in the remainder of the 2019 contest.)
 * Limits
 * 1 ≤ T ≤ 100.
 * Time limit: 10 seconds per test set.
 * Memory limit: 1GB.
 * At least one of the digits of N is a 4.
 * Test set 1 (Visible)
 * 1 < N < 105.
 * Test set 2 (Visible)
 * 1 < N < 109.
 * Solving the first two test sets for this problem should get you a long way toward advancing. The third test set is worth only 1 extra point, for extra fun and bragging rights!
 * Test set 3 (Hidden)
 * 1 < N < 10100.
 * Sample
 * <p>
 * Input:
 * 3
 * 4
 * 940
 * 4444
 * <p>
 * Output:
 * Case #1: 2 2
 * Case #2: 852 88
 * Case #3: 667 3777
 * <p>
 * <p>
 * In Sample Case #1, notice that A and B can be the same. The only other possible answers are 1 3 and 3 1.
 */
public class ForegoneSolution {

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        InputStream input = System.in;
        testCaseProcessor(inputReader(input));
    }

    public static List<String> inputReader(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);
        int n = sc.nextInt();
        sc.nextLine();

        List<String> result = new ArrayList<>();
        while (n-- > 0) {
            result.add(sc.nextLine());
        }

        System.out.println(result);
        return result;
    }

    public static void testCaseProcessor(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String[] res = splitOnTwoNumbers(input.get(i));
            System.out.printf("Case #%d: %s %s\n", i + 1, res[0], res[1]);
        }
    }

    public static String[] splitOnTwoNumbers(String numberWith4Digit) {
        StringBuilder sb1 = new StringBuilder(numberWith4Digit);
        StringBuilder sb2 = new StringBuilder(numberWith4Digit);

        int start = -1;
        for (int i = 0; i < sb1.length(); i++) {
            char ch = sb1.charAt(i);
            if (ch == '4') {
                if (start < 0) start = i;
                sb1.setCharAt(i, '1');
                sb2.setCharAt(i, '3');
            } else {
                sb1.setCharAt(i, '0');
            }
        }

        return new String[]{sb1.substring(start), sb2.toString()};
    }
}
