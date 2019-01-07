import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSort {

    static void quicksort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    static void quicksort(int[] arr, int l, int r) {
        if (l < r) {
            int m = partition(arr, l, r);
            quicksort(arr, l, m - 1);
            quicksort(arr, m + 1, r);
        }
    }

    static int partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int start = l;
        for (int i = start; i < r; i++) {
            if (arr[i] <= pivot) {
                Utils.swap(arr, i, l++);
            }
        }
        Utils.swap(arr, r, l);

        return l;
    }

    @Test
    void test1() {
        verifySort(new int[]{1,4, 5,3, 0, 3, 4, 6, 3});
        verifySort(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
    }

    void verifySort(int[] arr) {
        int[] arrClone = arr.clone();
        Arrays.sort(arrClone);
        quicksort(arr);

        assertArrayEquals(arrClone, arr);
    }
}
