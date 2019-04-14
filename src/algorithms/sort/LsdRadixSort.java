package algorithms.sort;

/**
 * Least Significant Digit Radix Sort
 */
public class LsdRadixSort {

    /**
     * @param arr array of string values with ASCII only chars
     * @param W   fixed length strings
     */
    public static void sort(String[] arr, int W) {
        int R = 256; // support only ASCII chars
        int N = arr.length;
        String[] aux = new String[N];

        // do key-indexed counting for each digit from right to left
        for (int d = W - 1; d >= 0; d--) {
            // key indexed counting
            int[] count = new int[R + 1];

            // count frequencies
            for (String str : arr) {
                count[str.charAt(d) + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            // move items
            for (String str : arr) {
                aux[count[str.charAt(d)]++] = str;
            }

            // copy back
            for (int i = 0; i < arr.length; i++) {
                arr[i] = aux[i];
            }
        }
    }
}
