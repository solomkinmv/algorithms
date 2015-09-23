import java.util.Arrays;

class Filter {
    static int[] filter(int[] array) {
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 3 != 0) {
                int c = array[i];
                array[i] = array[j];
                array[j++] = c;
            }
        }

        return Arrays.copyOfRange(array, 0, j);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(filter(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9})));
    }
}
