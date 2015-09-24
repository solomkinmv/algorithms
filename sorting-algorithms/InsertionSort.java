import java.util.Arrays;

class InsertionSort {
    static int[] insertionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j - 1] > array[j]) {
                    int c = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = c;
                }
            }
        }
        return array;
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(insertionSort(new int[]{4, 7, 1, 0, 2, 7, 4, 6, 9})));
    }
}
