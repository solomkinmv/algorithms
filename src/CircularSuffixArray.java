public class CircularSuffixArray {

    private final String str;
    private final int[] suffixes;

    public CircularSuffixArray(String s) {    // circular suffix array of s
        if (s == null) throw new IllegalArgumentException();
        str = s;

        suffixes = buildSuffixes();
    }

    public static void main(String[] args) {  // unit testing (required)
        String str = "AAAAAAAAAA";
//        String str = "ABRACADABRA!";
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(str);
        for (int i = 0; i < str.length(); i++) {
            System.out.println("i = " + i + ", index = " + circularSuffixArray.index(i));
        }
    }

    public int length() {                     // length of s
        return str.length();
    }

    public int index(int i) {                 // returns index of ith sorted suffix
        if (i < 0 || i >= str.length()) throw new IllegalArgumentException();
        return suffixes[i];
    }

    private int[] buildSuffixes() {
        int[] suffixes = new int[length()];
        for (int i = 1; i < length(); i++) {
            suffixes[i] = i;
        }

        threeWayRadixQuicksort(suffixes);
        return suffixes;
    }

    private void threeWayRadixQuicksort(int[] arr) {
        threeWayRadixQuicksort(arr, 0, arr.length - 1, 0);
    }

    private void threeWayRadixQuicksort(int[] arr, int lo, int hi, int d) {
        if (hi <= lo) return;
        if (d >= arr.length) return;

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

        threeWayRadixQuicksort(arr, lo, lt - 1, d);
        if (v >= 0) {
            threeWayRadixQuicksort(arr, lt, gt, d + 1);
        }
        threeWayRadixQuicksort(arr, gt + 1, hi, d);
    }

    private int charAt(int offset, int index) {
        int pos = offset + index;
        if (pos >= length()) {
            pos -= length();
        }

        return str.charAt(pos);
    }

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
