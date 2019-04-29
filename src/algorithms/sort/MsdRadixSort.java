package algorithms.sort;

/**
 * Most Significant Digit Radix Sort
 */
public class MsdRadixSort {

    private static final int R = 256; // support only ASCII chars

    /**
     * @param arr array of string values with ASCII only chars
     */
    public static void sort(String[] arr) {
        int size = arr.length;
        sort(arr, new String[size], 0, size - 1, 0);
    }

    private static void sort(String[] arr, String[] aux, int lo, int hi, int d) {
        if (hi <= lo) return;

        // key indexed counting
        int[] count = new int[R + 2];

        // count frequencies
        for (int i = lo; i <= hi; i++) {
            String str = arr[i];
            count[str.charAt(d) + 1]++;
        }

        // compute cumulates
        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        // move items
        for (int i = lo; i <= hi; i++) {
            String str = arr[i];
            aux[count[str.charAt(d)]++] = str;
        }

        // copy back
        for (int i = lo; i <= hi; i++) {
            arr[i] = aux[i - lo];
        }

        for (int r = 0; r < R; r++) {
            sort(arr, aux, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }
}
