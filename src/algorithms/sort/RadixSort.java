package algorithms.sort;

public class RadixSort {

    /**
     * @param R number of unique numbers from {@code arr}
     */
    public static void sort(int[] arr, int R) {
        int N = arr.length;
        int[] count = new int[R + 1];

        // count frequencies
        for (int val : arr) {
            count[val + 1]++; // each value is stored in "val + 1" bucket
        }

        // compute cumulates
        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        // move items
        int[] aux = new int[N];
        for (int val : arr) {
            aux[count[val]++] = val;
        }

        // copy back
        System.arraycopy(aux, 0, arr, 0, N);
    }
}
