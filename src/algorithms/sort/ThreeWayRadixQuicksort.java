package algorithms.sort;

public class ThreeWayRadixQuicksort {

    /**
     * @param arr array of string values with ASCII only chars
     */
    public static void sort(String[] arr) {
        sort(arr, 0, arr.length - 1, 0);
    }

    private static void sort(String[] arr, int lo, int hi, int d) {
        if (hi <= lo) return;

        int lt = lo, gt = hi;
        int v = charAt(arr[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(arr[i], d);
            if (t < v) {
                swap(arr, lt++, i++);
            } else if (t > v) {
                swap(arr, i, gt--);
            } else {
                i++;
            }
        }

        sort(arr, lo, lt - 1, d);
        if (v >= 0) {
            sort(arr, lt, gt, d + 1);
        }
        sort(arr, gt + 1, hi, d);
    }

    private static int charAt(String string, int index) {
        if (string.length() <= index) {
            return -1;
        }
        return string.charAt(index);
    }

    private static void swap(String[] arr, int a, int b) {
        String tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
