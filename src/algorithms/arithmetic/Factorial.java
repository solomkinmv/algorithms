package algorithms.arithmetic;

public class Factorial {

    public static void main(String[] args) {
        System.out.println(factorialRecursive(5));
    }

    static long factorialRecursive(int n) {
        if (n == 0) return 1;
        return n * factorialRecursive(n - 1);
    }
}
