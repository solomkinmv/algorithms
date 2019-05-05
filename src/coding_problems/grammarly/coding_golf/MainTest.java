package coding_problems.grammarly.coding_golf;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    private static String EXPECTED_STRING = "JDK 1.0 was released in January 1996\n" +
            "JDK 1.1 was released in February 1997\n" +
            "JDK 1.2 was released in December 1998\n" +
            "JDK 1.3 was released in May 2000\n" +
            "JDK 1.4 was released in February 2002\n" +
            "JDK 5 was released in September 2004\n" +
            "JDK 6 was released in December 2006\n" +
            "JDK 7 was released in July 2011\n" +
            "JDK 8 was released in March 2014\n" +
            "JDK 9 was released in September 2017\n" +
            "JDK 10 was released in March 2018\n" +
            "JDK 11 was released in September 2018\n" +
            "JDK 12 was released in March 2019\n" +
            "JDK 13 will be released in September 2019\n" +
            "JDK 14 will be released in March 2020\n" +
            "JDK 15 will be released in September 2020\n" +
            "JDK 16 will be released in March 2021\n" +
            "JDK 17 will be released in September 2021\n" +
            "JDK 18 will be released in March 2022\n" +
            "JDK 19 will be released in September 2022\n" +
            "JDK 20 will be released in March 2023\n" +
            "JDK 21 will be released in September 2023\n" +
            "JDK 22 will be released in March 2024\n" +
            "JDK 23 will be released in September 2024\n" +
            "JDK 24 will be released in March 2025\n" +
            "JDK 25 will be released in September 2025\n" +
            "JDK 26 will be released in March 2026\n" +
            "JDK 27 will be released in September 2026\n" +
            "JDK 28 will be released in March 2027\n" +
            "JDK 29 will be released in September 2027\n" +
            "JDK 30 will be released in March 2028\n" +
            "JDK 31 will be released in September 2028\n" +
            "JDK 32 will be released in March 2029\n" +
            "JDK 33 will be released in September 2029\n" +
            "JDK 34 will be released in March 2030\n" +
            "JDK 35 will be released in September 2030\n" +
            "JDK 36 will be released in March 2031\n" +
            "JDK 37 will be released in September 2031\n" +
            "JDK 38 will be released in March 2032\n" +
            "JDK 39 will be released in September 2032\n" +
            "JDK 40 will be released in March 2033\n" +
            "JDK 41 will be released in September 2033\n" +
            "JDK 42 will be released in March 2034\n";

    @Test
    void doTask() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        T.main(null);

        assertEquals(EXPECTED_STRING, baos.toString());
    }
}