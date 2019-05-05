import java.lang {
    String {
        format
    }
}
import java.time {
    D=LocalDate
}
import java.time.format {
    ...
}

shared void run() {
    variable D d = D.\iof(1996, 1, 1);
    for (i in 0:43) {
        print(format("JDK %s %s released in %s",
            if (i < 5) then "1.``i``" else "``i``",
            if (D.now().isAfter(d = d.plusMonths([0, 13, 22, 17, 21, 31, 27, 55, 32, 42][i] else 6))) then "was" else "will be",
            d.format(DateTimeFormatter.ofPattern("MMMM yyyy"))
        ));
    }
}