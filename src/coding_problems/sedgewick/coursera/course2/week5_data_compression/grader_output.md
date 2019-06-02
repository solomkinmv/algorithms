# Aggregate score: 100

See the Assessment Guide for information on how to interpret this report.

ASSESSMENT SUMMARY

Compilation:  PASSED
API:          PASSED

Spotbugs:     PASSED
PMD:          PASSED
Checkstyle:   FAILED (0 errors, 2 warnings)

Correctness:  64/64 tests passed
Memory:       10/10 tests passed
Timing:       159/159 tests passed

Aggregate score: 100.00%
[Compilation: 5%, API: 5%, Spotbugs: 0%, PMD: 0%, Checkstyle: 0%, Correctness: 60%, Memory: 10%, Timing: 20%]

ASSESSMENT DETAILS

The following files were submitted:
----------------------------------
2.6K Jun  2 22:19 BurrowsWheeler.java
2.3K Jun  2 22:19 CircularSuffixArray.java
2.2K Jun  2 22:19 MoveToFront.java


********************************************************************************
*  COMPILING                                                                    
********************************************************************************


% javac CircularSuffixArray.java
*-----------------------------------------------------------

% javac BurrowsWheeler.java
*-----------------------------------------------------------

% javac MoveToFront.java
*-----------------------------------------------------------


================================================================


Checking the APIs of your programs.
*-----------------------------------------------------------
CircularSuffixArray:

BurrowsWheeler:

MoveToFront:

================================================================


********************************************************************************
*  CHECKING STYLE AND COMMON BUG PATTERNS                                       
********************************************************************************


% spotbugs *.class
*-----------------------------------------------------------


================================================================


% pmd .
*-----------------------------------------------------------


================================================================


% checkstyle *.java
*-----------------------------------------------------------
[WARN] BurrowsWheeler.java:4:8: Unused import statement for 'java.util.Arrays'. [UnusedImports]
[WARN] CircularSuffixArray.java:32:15: The local (or parameter) variable 'suffixes' has the same name as an instance variable. Use a different name. [HiddenField]
Checkstyle ends with 0 errors and 2 warnings.

% custom checkstyle checks for CircularSuffixArray.java
*-----------------------------------------------------------

% custom checkstyle checks for BurrowsWheeler.java
*-----------------------------------------------------------

% custom checkstyle checks for MoveToFront.java
*-----------------------------------------------------------


================================================================


********************************************************************************
*  TESTING CORRECTNESS
********************************************************************************

Testing correctness of CircularSuffixArray
*-----------------------------------------------------------
Running 15 total tests.

Test 1: check index() and length() with random binary strings
  * length = 10
  * length = 100
  * length = 1000
==> passed

Test 2: check index() and length() with random uppercase strings
  * length = 10
  * length = 100
  * length = 1000
==> passed

Test 3: check index() and length() with random ASCII strings
  * length = 10
  * length = 100
  * length = 1000
==> passed

Test 4: check index() and length() with random extended ASCII strings
  * length = 10
  * length = 100
  * length = 1000
==> passed

Test 5: check index() and length() with strings from text files
  * cadabra.txt
  * amendments.txt
  * moby1.txt
  * dickens1000.txt
==> passed

Test 6: check index() and length() with strings from binary files
  * us.gif
  * CS_bricks.jpg
  * rand1K.bin
==> passed

Test 7: check index() and length() with random strings of length 0, 1, and 2
  * length = 0
  * length = 1
  * length = 2
==> passed

Test 8: check that index() throws an exception when argument is out of bounds
  * string of length 10
  * string of length 100
  * string of length 2
  * string of length 1
  * string of length 0
==> passed

Test 9: check that constructor throws an exception when argument is null
==> passed

Test 10: check that two CircularSuffixArray objects can be created at the same time
  * cadabra.txt and amendments.txt
  * amendments.txt and cadabra.txt
  * dickens1000.txt and cadabra.txt
==> passed

Test 11: check that CircularSuffixArray is immutable
  * string = "MLYKIHYCBSGIFWUPHJFDMSSMKBUOAK"
  * string = "ABBAABBAAAAABBABBABBAAAAABABAB"
  * string = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
  * string = hex representation: 10 63 60 76 13 9c e4 e9 df cc 1f 14 26 c9 1f 
==> passed

Test 12: check index() and length() with corner-case strings
  * a.txt
  * nomatch.txt
  * zebra.txt
  * alphanum.txt
==> passed

Test 13: check index() and length() with strings that are nontrivial circular
         that are nontrivial circular suffixes of themselves
  * stars.txt
  * couscous.txt
==> passed

Test 14: check index() and length() with unary strings
  * length 10 string over unary alphabet
  * length 100 string over unary alphabet
  * length 1000 string over unary alphabet
==> passed

Test 15: check index() and length() with random strings
         that are nontrivial circular suffixes of themselves
  * length 2 string over binary alphabet, repeated 2 times
  * length 2 string over binary alphabet, repeated 10 times
  * length 5 string over binary alphabet, repeated 2 times
  * length 5 string over binary alphabet, repeated 3 times
  * length 5 string over binary alphabet, repeated 5 times
  * length 7 string over uppercase alphabet, repeated 2 times
  * length 7 string over uppercase alphabet, repeated 3 times
  * length 7 string over uppercase alphabet, repeated 5 times
==> passed

Total: 15/15 tests passed!


================================================================
Testing correctness of MoveToFront
*-----------------------------------------------------------
Running 23 total tests.

Test 1a: check main() on text files
  * java MoveToFront - < abra.txt
  * java MoveToFront - < zebra.txt
  * java MoveToFront - < amendments.txt
  * java MoveToFront - < aesop.txt
==> passed

Test 1b: check main() on text files
  * java MoveToFront + < abra.txt.mtf
  * java MoveToFront + < zebra.txt.mtf
  * java MoveToFront + < amendments.txt.mtf
  * java MoveToFront + < aesop.txt.mtf
==> passed

Test 2a: check that main() is consistent with encode() on text files
  * abra.txt
  * zebra.txt
  * amendments.txt
  * aesop.txt
==> passed

Test 2b: check that main() is consistent with decode() on text files
  * abra.txt.mtf
  * zebra.txt.mtf
  * amendments.txt.mtf
  * aesop.txt.mtf
==> passed

Test 3a: check encode() on text files
  * abra.txt
  * zebra.txt
  * amendments.txt
  * aesop.txt
  * stars.txt
  * alphanum.txt
  * a.txt
==> passed

Test 3b: check encode() on binary files
  * us.gif
  * CS_bricks.jpg
  * rand10K.bin
==> passed

Test 3c: check encode() on random inputs
  * 10 random characters from { A } alphabet
  * 10 random characters from { A, B } alphabet
  * 10 random characters from { A, T, C, G } alphabet
  * 10 random characters from uppercase letter alphabet
  * 1000 random characters from { A } alphabet
  * 1000 random characters from { A, B } alphabet
  * 1000 random characters from { A, T, C, G } alphabet
  * 1000 random characters from uppercase letter alphabet
==> passed

Test 3d: check encode() on more random inputs
  * 1000 random characters from ASCII alphabet 
  * 1000 random characters from extended ASCII alphabet
  * 1000 random characters from extended ASCII alphabet (excluding 0x00)
  * 1000 random characters from extended ASCII alphabet (excluding 0xFF)
==> passed

Test 4a: check decode() on move-to-front-encoded text files
  * abra.txt.mtf
  * zebra.txt.mtf
  * amendments.txt.mtf
  * aesop.txt.mtf
  * stars.txt.mtf
  * alphanum.txt.mtf
  * a.txt.mtf
==> passed

Test 4b: check decode() on move-to-front encoded binary files
  * us.gif.mtf
  * CS_bricks.jpg.mtf
  * rand10K.bin.mtf
==> passed

Test 4c: check decode() on random inputs
  * 10 random characters from { A } alphabet
  * 10 random characters from { A, B } alphabet
  * 10 random characters from { A, T, C, G } alphabet
  * 10 random characters from uppercase letter alphabet
  * 1000 random characters from { A } alphabet
  * 1000 random characters from { A, B } alphabet
  * 1000 random characters from { A, T, C, G } alphabet
  * 1000 random characters from uppercase letter alphabet
==> passed

Test 4d: check decode() on more random inputs
  * 1000 random characters from ASCII alphabet 
  * 1000 random characters from extended ASCII alphabet
  * 1000 random characters from extended ASCII alphabet (excluding 0x00)
  * 1000 random characters from extended ASCII alphabet (excluding 0xFF)
==> passed

Test 4e: check decode() on random inputs
         that were encoded with move-to-front
  * 10 random characters from { A } alphabet
  * 10 random characters from { A, B } alphabet
  * 10 random characters from { A, T, C, G } alphabet
  * 10 random characters from uppercase letter alphabet
  * 1000 random characters from { A } alphabet
  * 1000 random characters from { A, B } alphabet
  * 1000 random characters from { A, T, C, G } alphabet
  * 1000 random characters from uppercase letter alphabet
==> passed

Test 4f: check decode() on more random inputs
         that were encoded with move-to-front
  * 1000 random characters from ASCII alphabet 
  * 1000 random characters from extended ASCII alphabet
  * 1000 random characters from extended ASCII alphabet (excluding 0x00)
  * 1000 random characters from extended ASCII alphabet (excluding 0xFF)
==> passed

Test 5a: check whether decode(encode()) = original on text files
  * abra.txt
  * zebra.txt
  * amendments.txt
  * aesop.txt
  * stars.txt
  * alphanum.txt
  * a.txt
==> passed

Test 5b: check whether decode(encode()) = original on binary files
  * us.gif
  * CS_bricks.jpg
  * rand10K.bin
==> passed

Test 5c: check that decode(encode()) = original on random inputs
  * 10 random characters from { A } alphabet
  * 10 random characters from { A, B } alphabet
  * 10 random characters from { A, T, C, G } alphabet
  * 10 random characters from uppercase letter alphabet
  * 100 random characters from { A } alphabet
  * 1000 random characters from { A, B } alphabet
  * 1000 random characters from { A, T, C, G } alphabet
  * 1000 random characters from uppercase letter alphabet
==> passed

Test 5d: check that decode(encode()) = original on random inputs
  * 1000 random characters from ASCII alphabet 
  * 1000 random characters from extended ASCII alphabet
  * 1000 random characters from extended ASCII alphabet (excluding 0x00)
  * 1000 random characters from extended ASCII alphabet (excluding 0xFF)
==> passed

Test 6a: check that encode() calls either close() or flush()
  * amendments.txt
  * aesop.txt
==> passed

Test 6b: check that decode() calls either close() or flush()
  * amendments.txt.mtf
  * aesop.txt.mtf
==> passed

Test 7a: check encode() on large files
  * rand100K.bin
  * world192.txt
==> passed

Test 7b: check decode() on large files
  * rand100K.bin.mtf
  * world192.txt.mtf
==> passed

Test 7c: check whether decode(encode()) = original on large files
  * rand100K.bin
  * world192.txt
==> passed


Total: 23/23 tests passed!


================================================================
********************************************************************************
*  TESTING CORRECTNESS (substituting reference CircularSuffixArray)
********************************************************************************

Testing correctness of BurrowsWheeler
*-----------------------------------------------------------
Running 26 total tests.

Test 1a: check main() on text files
  * java BurrowsWheeler - < abra.txt
  * java BurrowsWheeler - < zebra.txt
  * java BurrowsWheeler - < cadabra.txt
  * java BurrowsWheeler - < amendments.txt
==> passed

Test 1b: check main() on text files
  * java BurrowsWheeler + < abra.txt.bwt
  * java BurrowsWheeler + < zebra.txt.bwt
  * java BurrowsWheeler + < cadabra.txt.bwt
  * java BurrowsWheeler + < amendments.txt.bwt
==> passed

Test 2a: check that main() is consistent with transform() on text files
  * abra.txt
  * zebra.txt
  * cadabra.txt
  * amendments.txt
==> passed

Test 2b: check that main() is consistent with inverseTransform() on text files
  * abra.txt.bwt
  * zebra.txt.bwt
  * cadabra.txt.bwt
  * amendments.txt.bwt
==> passed

Test 3a: check transform() on text files
  * abra.txt
  * zebra.txt
  * cadabra.txt
  * amendments.txt
==> passed

Test 3b: check transform() on corner-case text files
  * alphanum.txt
  * a.txt
==> passed

Test 3c: check transform() on binary files
  * us.gif
  * CS_bricks.jpg
  * rand10K.bin
==> passed

Test 3d: check transform() on random inputs
  * 10 random characters from binary alphabet
  * 10 random characters from DNA alphabet
  * 10 random characters from uppercase alphabet
  * 1000 random characters from binary alphabet
  * 1000 random characters from DNA alphabet
  * 1000 random characters from uppercase alphabet
==> passed

Test 3e: check transform() on more random inputs
  * 1000 random characters from ASCII alphabet 
  * 1000 random characters from extended ASCII alphabet
  * 1000 random characters from extended ASCII alphabet (excluding 0x00)
  * 1000 random characters from extended ASCII alphabet (excluding 0xFF)
==> passed

Test 3f: check tranform() on random inputs that are circular
         shifts of themselves
  * 5 random strings from unary alphabet
  * 5 random strings from binary alphabet
  * 5 random strings from DNA alphabet
  * 5 random strings from uppercase alphabet
==> passed

Test 4a: check inverseTransform() on text files
  * abra.txt.bwt
  * zebra.txt.bwt
  * cadabra.txt.bwt
  * amendments.txt.bwt
==> passed

Test 4b: check inverseTransform() on corner-case text files
  * alphanum.txt.bwt
  * a.txt.bwt
  * stars.txt.bwt
  * couscous.txt.bwt
==> passed

Test 4c: check inverseTransform() on binary files
  * us.gif.bwt
  * CS_bricks.jpg.bwt
  * rand10K.bin.bwt
==> passed

Test 4d: check inverseTransform() of transform() on random inputs
  * 10 random characters from unary alphabet
  * 10 random characters from binary alphabet
  * 10 random characters from DNA alphabet
  * 10 random characters from uppercase alphabet
  * 100 random characters from unary alphabet
  * 1000 random characters from binary alphabet
  * 1000 random characters from DNA alphabet
  * 1000 random characters from uppercase alphabet
==> passed

Test 4e: check inverseTransform() of transform() on more random inputs
  * 1000 random characters from ASCII alphabet 
  * 1000 random characters from extended ASCII alphabet
  * 1000 random characters from extended ASCII alphabet (excluding 0x00)
  * 1000 random characters from extended ASCII alphabet (excluding 0xFF)
==> passed

Test 5a: check that inverseTransform(transform()) = original on text files
  * abra.txt
  * zebra.txt
  * cadabra.txt
  * amendments.txt
==> passed

Test 5b: check that inverseTransform(transform()) = original on corner-case text files
  * alphanum.txt
  * a.txt
  * stars.txt
  * couscous.txt
==> passed

Test 5c: check that inverseTransform(transform()) = original on binary files
  * us.gif
  * CS_bricks.jpg
  * rand10K.bin
==> passed

Test 5d: check that inverseTransform(tranform()) = original on random inputs
  * 10 random characters from binary alphabet
  * 10 random characters from DNA alphabet
  * 10 random characters from uppercase alphabet
  * 1000 random characters from binary alphabet
  * 1000 random characters from DNA alphabet
  * 1000 random characters from uppercase alphabet
==> passed

Test 5e: check that inverseTransform(tranform()) = original on random inputs
  * 1000 random characters from ASCII alphabet 
  * 1000 random characters from extended ASCII alphabet
  * 1000 random characters from extended ASCII alphabet (excluding 0x00)
  * 1000 random characters from extended ASCII alphabet (excluding 0xFF)
==> passed

Test 5f: check that inverseTransform(tranform()) = original
         on random inputs that are circular shifts of themselves
  * random strings from unary alphabet
  * random strings from binary alphabet
  * random strings from DNA alphabet
  * random strings from uppercase alphabet
==> passed

Test 6a: check that transform() calls either close() or flush()
  * amendments.txt
  * aesop.txt
==> passed

Test 6b: check that inverseTransform() calls either close() or flush()
  * amendments.txt.bwt
  * aesop.txt.bwt
==> passed

Test 7a: check transform() on large files
  * rand100K.bin
  * world192.txt
==> passed

Test 7b: check inverseTransform() on large files
  * rand100K.bin.bwt
  * world192.txt.bwt
==> passed

Test 7c: check that inverseTransform(transform()) = original on large files
  * rand100K.bin
  * world192.txt
==> passed


Total: 26/26 tests passed!


================================================================
********************************************************************************
*  MEMORY
********************************************************************************

Analyzing memory of CircularSuffixArray
*-----------------------------------------------------------
Running 10 total tests.

Memory usage of a CircularSuffixArray for a random string of length n.
Maximum allowed memory is 64n + 128.

                 n        bytes
-------------------------------
=> passed       16          208
=> passed       32          304
=> passed       64          496
=> passed      128          880
=> passed      256         1648
=> passed      512         3184
=> passed     1024         6256
=> passed     2048        12400
=> passed     4096        24688
=> passed     8192        49264
==> 10/10 tests passed

Total: 10/10 tests passed!

Estimated student memory (bytes) = 6.00 n + 112.00   (R^2 = 1.000)

================================================================



Could not test BurrowsWheeler: MemoryOfBurrowsWheeler.class does not exist.


Could not test MoveToFront: MemoryOfMoveToFront.class does not exist.


********************************************************************************
*  TIMING
********************************************************************************

Timing CircularSuffixArray
*-----------------------------------------------------------
Running 26 total tests.

Tests  1-13: time to create a circular suffix array for the first
             n character of dickens.txt and call index(i) for each i

            [ max allowed time = 10 seconds and <= 12x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       9.56
=> passed     2000       0.00       0.00       1.56
=> passed     4000       0.00       0.00       1.73
=> passed     8000       0.00       0.00       1.81
=> passed    16000       0.00       0.00       1.10
=> passed    32000       0.01       0.00       1.39
=> passed    64000       0.01       0.01       1.65
=> passed   128000       0.02       0.01       1.60
=> passed   256000       0.03       0.02       1.62
=> passed   512000       0.07       0.05       1.61
=> passed  1024000       0.16       0.11       1.53
=> passed  2048000       0.33       0.22       1.53
=> passed  4096000       0.71       0.48       1.49

Estimated running time (using last 6 measurements)
    = 5.75e-08 * n^1.07  (R^2 = 1.00)


Tests 14-26: time to create circular suffix array for n random ASCII characters
            and call index(i) for each i

            [ max allowed time = 10 seconds and <= 20x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       2.78
=> passed     2000       0.00       0.00       1.56
=> passed     4000       0.00       0.00       1.39
=> passed     8000       0.00       0.00       2.14
=> passed    16000       0.00       0.00       3.01
=> passed    32000       0.00       0.00       3.73
=> passed    64000       0.01       0.00       4.00
=> passed   128000       0.01       0.00       3.66
=> passed   256000       0.03       0.01       2.52
=> passed   512000       0.07       0.03       2.25
=> passed  1024000       0.15       0.05       2.98
=> passed  2048000       0.31       0.09       3.49
=> passed  4096000       0.69       0.17       4.05

Estimated running time (using last 6 measurements)
    = 3.31e-08 * n^1.11  (R^2 = 1.00)


Total: 26/26 tests passed!


================================================================



********************************************************************************
*  TIMING
********************************************************************************

Timing MoveToFront
*-----------------------------------------------------------
Running 38 total tests.

Test 1: count calls to methods in BinaryStdOut from encode()
  * abra.txt
  * amendments.txt
==> passed

Test 2: count calls to methods in BinaryStdOut from decode()
  * abra.txt.mtf
  * amendments.txt.mtf
==> passed

Tests  3-12: Timing encode() with first n character of dickens.txt
             [ max allowed time = 2 seconds and <= 4x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       0.75
=> passed     2000       0.00       0.00       0.96
=> passed     4000       0.00       0.00       0.97
=> passed     8000       0.01       0.01       0.97
=> passed    16000       0.01       0.01       0.97
=> passed    32000       0.03       0.03       0.99
=> passed    64000       0.05       0.06       0.99
=> passed   128000       0.11       0.11       1.01
=> passed   256000       0.22       0.23       0.94

Estimated running time (using last 6 measurements)
     = 9.59e-07 * n^0.99  (R^2 = 1.00)


Tests  13-20: Timing encode() with first n character of abab.txt
             [ max allowed time = 2 seconds and <= 4x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       1.01
=> passed     2000       0.00       0.00       1.00
=> passed     4000       0.00       0.00       1.01
=> passed     8000       0.01       0.01       1.01
=> passed    16000       0.01       0.01       1.01
=> passed    32000       0.03       0.03       1.01
=> passed    64000       0.06       0.05       1.19
=> passed   128000       0.17       0.16       1.03
=> passed   256000       0.21       0.21       1.00

Estimated running time (using last 6 measurements)
     = 4.59e-07 * n^1.06  (R^2 = 0.99)


Tests 21-29: Timing decode() with first n character of dickens.txt
             [ max allowed time = 2 seconds and <= 4x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       0.99
=> passed     2000       0.00       0.00       1.00
=> passed     4000       0.00       0.00       1.01
=> passed     8000       0.01       0.01       1.01
=> passed    16000       0.01       0.01       1.01
=> passed    32000       0.02       0.02       1.00
=> passed    64000       0.05       0.05       1.01
=> passed   128000       0.10       0.10       1.01
=> passed   256000       0.19       0.19       1.01

Estimated running time (using last 6 measurements)
     = 8.63e-07 * n^0.99  (R^2 = 1.00)


Tests 30-38: Timing decode() with first n character of abab.txt
             [ max allowed time = 2 seconds and <= 4x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       1.00
=> passed     2000       0.00       0.00       0.99
=> passed     4000       0.00       0.00       1.00
=> passed     8000       0.01       0.01       0.99
=> passed    16000       0.01       0.01       1.00
=> passed    32000       0.02       0.02       0.99
=> passed    64000       0.05       0.05       1.00
=> passed   128000       0.09       0.09       0.99
=> passed   256000       0.18       0.19       0.99

Estimated running time (using last 6 measurements)
     = 7.00e-07 * n^1.00  (R^2 = 1.00)


Total: 38/38 tests passed!


================================================================



********************************************************************************
*  TIMING (substituting reference CircularSuffixArray)
********************************************************************************

Timing BurrowsWheeler
*-----------------------------------------------------------
Running 95 total tests.

Test 1: count calls to methods in CircularSuffixArray from transform()
  * abra.txt
  * amendments.txt
==> passed

Test 2: count calls to methods in CircularSuffixArray from inverseTransform()
  * abra.txt.bwt
  * amendments.txt.bwt
==> passed

Test 3: count calls to methods in BinaryStdOut from transform()
  * abra.txt
  * amendments.txt
==> passed

Test 4: count calls to methods in BinaryStdOut from inverseTransform()
  * abra.txt.bwt
  * amendments.txt.bwt
==> passed

Tests  5-17: timing transform() with first n character of dickens.txt
             [ max allowed time = 2 seconds and <= 8x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       0.12
=> passed     2000       0.00       0.00       0.61
=> passed     4000       0.00       0.00       0.62
=> passed     8000       0.00       0.00       0.71
=> passed    16000       0.00       0.00       0.60
=> passed    32000       0.00       0.01       0.65
=> passed    64000       0.01       0.01       0.84
=> passed   128000       0.02       0.02       0.97
=> passed   256000       0.03       0.03       0.99
=> passed   512000       0.07       0.07       0.99
=> passed  1024000       0.15       0.15       1.01
=> passed  2048000       0.31       0.34       0.91
=> passed  4096000       0.71       0.66       1.07

Estimated running time as a function of n (using last 6 measurements)
    = 3.68e-08 * n^1.10  (R^2 = 1.00)


Tests 18-30: timing transform() with first n character of random.bin
             [ max allowed time = 2 seconds and <= 8x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       0.72
=> passed     2000       0.00       0.00       0.94
=> passed     4000       0.00       0.00       0.97
=> passed     8000       0.00       0.00       0.98
=> passed    16000       0.00       0.00       0.97
=> passed    32000       0.00       0.00       0.99
=> passed    64000       0.01       0.01       1.00
=> passed   128000       0.02       0.02       1.00
=> passed   256000       0.03       0.03       1.00
=> passed   512000       0.06       0.07       0.83
=> passed  1024000       0.14       0.15       0.98
=> passed  2048000       0.30       0.30       0.99
=> passed  4096000       0.65       0.71       0.91

Estimated running time as a function of n (using last 6 measurements)
    = 6.51e-08 * n^1.06  (R^2 = 1.00)


Tests 31-43: timing transform() with first n character of abab.txt
             [ max allowed time = 2 seconds and <= 8x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       0.76
=> passed     2000       0.00       0.00       0.92
=> passed     4000       0.00       0.00       0.93
=> passed     8000       0.00       0.00       1.04
=> passed    16000       0.00       0.00       0.93
=> passed    32000       0.00       0.00       0.52
=> passed    64000       0.00       0.00       0.85
=> passed   128000       0.00       0.00       0.85
=> passed   256000       0.00       0.01       0.79
=> passed   512000       0.01       0.01       0.83
=> passed  1024000       0.02       0.02       0.84
=> passed  2048000       0.03       0.04       0.84
=> passed  4096000       0.08       0.08       0.92

Estimated running time as a function of n (using last 6 measurements)
    = 2.60e-08 * n^0.97  (R^2 = 0.99)


Tests 44-56: timing inverseTransform() with first n character of dickens.txt
             [ max allowed time = 2 seconds and <= 8x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       0.51
=> passed     2000       0.00       0.00       0.34
=> passed     4000       0.00       0.00       0.24
=> passed     8000       0.00       0.00       0.23
=> passed    16000       0.00       0.00       0.24
=> passed    32000       0.00       0.00       0.74
=> passed    64000       0.00       0.00       0.80
=> passed   128000       0.00       0.00       0.78
=> passed   256000       0.01       0.01       1.23
=> passed   512000       0.02       0.01       1.06
=> passed  1024000       0.04       0.03       1.17
=> passed  2048000       0.12       0.11       1.09
=> passed  4096000       0.30       0.33       0.91

Estimated running time as a function of n (using last 6 measurements)
    = 4.34e-10 * n^1.33  (R^2 = 1.00)


Tests 57-69: timing inverseTransform() with first n character of random.bin
             [ max allowed time = 2 seconds and <= 8x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1024       0.00       0.00       0.99
=> passed     2048       0.00       0.00       1.27
=> passed     4096       0.00       0.00       1.31
=> passed     8192       0.00       0.00       1.34
=> passed    16384       0.00       0.00       1.36
=> passed    32768       0.00       0.00       1.37
=> passed    65536       0.00       0.00       1.41
=> passed   131072       0.00       0.00       1.40
=> passed   262144       0.01       0.01       1.23
=> passed   524288       0.02       0.02       1.14
=> passed  1048576       0.04       0.04       1.04
=> passed  2097152       0.10       0.08       1.31
=> passed  4194304       0.24       0.23       1.03

Estimated running time as a function of n (using last 6 measurements)
    = 2.40e-09 * n^1.21  (R^2 = 1.00)


Tests 70-82: timing inverseTransform() with first n character of abab.txt
             [ max allowed time = 2 seconds and <= 8x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       0.96
=> passed     2000       0.00       0.00       1.34
=> passed     4000       0.00       0.00       1.46
=> passed     8000       0.00       0.00       1.38
=> passed    16000       0.00       0.00       1.35
=> passed    32000       0.00       0.00       1.46
=> passed    64000       0.00       0.00       1.50
=> passed   128000       0.00       0.00       1.48
=> passed   256000       0.01       0.00       1.48
=> passed   512000       0.01       0.01       1.47
=> passed  1024000       0.02       0.02       1.48
=> passed  2048000       0.05       0.03       1.48
=> passed  4096000       0.09       0.06       1.48

Estimated running time as a function of n (using last 6 measurements)
    = 2.30e-08 * n^1.00  (R^2 = 1.00)


Tests 83-95: timing inverseTransform() with first n character of cyclic.bin
             [ max allowed time = 2 seconds and <= 8x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1024       0.00       0.00       1.05
=> passed     2048       0.00       0.00       1.32
=> passed     4096       0.00       0.00       1.35
=> passed     8192       0.00       0.00       1.55
=> passed    16384       0.00       0.00       1.38
=> passed    32768       0.00       0.00       1.39
=> passed    65536       0.00       0.00       1.43
=> passed   131072       0.00       0.00       1.55
=> passed   262144       0.01       0.01       1.54
=> passed   524288       0.02       0.01       1.59
=> passed  1048576       0.04       0.03       1.22
=> passed  2097152       0.09       0.07       1.26
=> passed  4194304       0.16       0.16       1.02

Estimated running time as a function of n (using last 6 measurements)
    = 1.08e-08 * n^1.09  (R^2 = 0.99)


Total: 95/95 tests passed!


================================================================

