package algorithms.search;

class BinarySearch {
    static int binarySearch(int[] array, int value, int p, int q) {
        int mid = (p + q) / 2;

        if (array[mid] == value) {
            return mid;
        } else if (p == q) {
            return -1;
        } else if (array[mid] > value) {
            return binarySearch(array, value, p, mid);
        } else {
            return binarySearch(array, value, mid + 1, q);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < 11; i++) {
            System.out.println(binarySearch(array, i, 0, array.length - 1));
        }
    }
}
