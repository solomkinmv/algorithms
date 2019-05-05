package coding_problems.grammarly.coding_golf;

import java.time.LocalDate;

interface T {
    static void main(String... a) {
        int[] s = {0, 13, 22, 17, 21, 31, 27, 55, 32, 42};
        var d = LocalDate.of(1996, 1, 1);
        for (int i = 0; i < 43; i++) {
            System.out.printf("JDK %s %s released in %s\n",
                              i < 5 ? "1." + i : "" + i,
                              LocalDate.now().isAfter(d = d.plusMonths(i < s.length ? s[i] : 6)) ? "was" : "will be",
                              d.format(java.time.format.DateTimeFormatter.ofPattern("MMMM yyyy")));
        }
    }
}