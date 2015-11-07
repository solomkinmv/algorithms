#|
 | A palindromic number reads the same both ways.
 | The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 х 99.
 | Find the largest palindrome made from the product of two 3-digit numbers.
 | Glossary:
 | a-number - first 3-digit number used for making palindromic number
 | b-number - second 3-digit number used for making palindromic number
 |#

; function to check n number if it is palindromic
(defun check_number (n base)
  (cond
    ((equal n 0) t) ; if n is checked
    ((not (equal (nth-value 0 (floor n base)) (mod n 10))) nil) ; if left and right digits not equal
    ((equal (nth-value 0 (floor n base)) (mod n 10)) (check_number (nth-value 0 (floor (mod n base) 10)) (/ base 100))) ; check inner digits
  )
)

; function to find palindromic number made from the product of a-number
(defun find_palindrome (a b)
  (cond
    ((or (< a 100) (< b 100)) nil)
    ((check_number (* a b) 100000) (list (* a b) a b))
    (t (find_palindrome a (- b 1)))
  )
)

; get list with maximum palindromic number
(defun get_max (res1 res2)
  (cond
    ((null res1) res2)
    ((null res2) res1)
    (t (if (> (car res1) (car res2)) res1 res2))
  )
)

; check all a-numbers for palindromic number and return largest palindromic number
(defun start (a largest)
  (cond
    ((< a (min (nth 1 largest) (nth 2 largest))) largest) ; stop if a-number is lower than one of a/b-numbers of the largest palindromic number
    ((< a 10) largest)
    (t (start (- a 1) (get_max largest (find_palindrome a a))))
  )
)

(setq palindromic (start 999 '(0 0 0))) ; start with the highest a-number and pass lowest palindromic number
(format t "The largest palindromic number made from the product of two 3-digit numbers ~D and ~D is ~D." (nth 1 palindromic) (nth 2 palindromic) (nth 0 palindromic))
