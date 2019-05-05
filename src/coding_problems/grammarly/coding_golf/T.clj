(import java.time.LocalDate)

(defn l [i, d]
      (if (< i 43)
        (do
          (println (str "JDK "
                        (if (< i 5)
                          (str "1." i)
                          i)
                        (if (.isAfter (LocalDate/now) d)
                          " was"
                          " will be"
                          )
                        " released in "
                        (.format d
                                 (java.time.format.DateTimeFormatter/ofPattern "MMMM yyyy"))
                        ))
          (l
            (+ i 1)
            (.plusMonths d
                         (nth [0, 13, 22, 17, 21, 31, 27, 55, 32, 42] (+ i 1) 6))
            )
          )
        )
      )

(l 0 (LocalDate/of 1996, 1, 1))