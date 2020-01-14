package coding_problems.cracking_the_coding_interview;

public class Task_16_1_NumberSwapper {

    public static void main(String[] args) {
        swapNumbers(1, 2);
    }

    static void swapNumbers(int a, int b) {
        a = a + b;
        b = a - b; // (a + b) - b = a
        a = a - b; // (a + b) - a = b

        System.out.println(a);
        System.out.println(b);
    }
}
