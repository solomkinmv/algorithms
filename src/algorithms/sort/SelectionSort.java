package algorithms.sort;

import java.util.Arrays;

class SelectionSort {
    static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minInd = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minInd]) {
                    minInd = j;
                }
            }
            int c = array[minInd];
            array[minInd] = array[i];
            array[i] = c;
        }

        return array;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(selectionSort(new int[]{4, 7, 1, 0, 2, 7, 4, 6, 9, 0})));
    }
}
