# Aggregate score: 96.89%

See the Assessment Guide for information on how to interpret this report.

ASSESSMENT SUMMARY

Compilation:  PASSED
API:          PASSED

Spotbugs:     PASSED
PMD:          PASSED
Checkstyle:   FAILED (0 errors, 5 warnings)

Correctness:  30/31 tests passed
Memory:       6/6 tests passed
Timing:       16/17 tests passed

Aggregate score: 96.89%
[Compilation: 5%, API: 5%, Spotbugs: 0%, PMD: 0%, Checkstyle: 0%, Correctness: 60%, Memory: 10%, Timing: 20%]

ASSESSMENT DETAILS

The following files were submitted:
----------------------------------
8.2K Mar 23 23:10 SeamCarver.java


********************************************************************************
*  COMPILING                                                                    
********************************************************************************


% javac SeamCarver.java
*-----------------------------------------------------------


================================================================


Checking the APIs of your programs.
*-----------------------------------------------------------
SeamCarver:

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

% custom checkstyle checks for SeamCarver.java
*-----------------------------------------------------------
[WARN] SeamCarver.java:36:9: Your program will likely be faster if you use an implicit graph instead of creating an object of type 'EdgeWeightedDigraph'. [Performance]
[WARN] SeamCarver.java:51:9: Your program will likely be faster if you use an implicit graph instead of creating an object of type 'EdgeWeightedDigraph'. [Performance]
[WARN] SeamCarver.java:144:13: Your program will likely be faster if you use an implicit graph instead of creating an object of type 'EdgeWeightedDigraph'. [Performance]
[WARN] SeamCarver.java:146:9: Your program will likely be faster if you use an implicit graph instead of creating an object of type 'EdgeWeightedDigraph'. [Performance]
[WARN] SeamCarver.java:146:72: The numeric literal '4' appears to be unnecessary. [NumericLiteral]
Checkstyle ends with 0 errors and 5 warnings.


================================================================


********************************************************************************
*  TESTING CORRECTNESS
********************************************************************************

Testing correctness of SeamCarver
*-----------------------------------------------------------
Running 31 total tests.

Test 1a: check energy() with file inputs
  * 6x5.png
  * 4x6.png
  * 10x12.png
  * 3x7.png
  * 5x6.png
  * 7x3.png
  * 7x10.png
  * 12x10.png
  * stripes.png
  * diagonals.png
  * chameleon.png
  * HJoceanSmall.png
  * 1x8.png
  * 8x1.png
  * 1x1.png
==> passed

Test 1b: check energy() with random pictures
  * 4-by-6
  * 5-by-5
  * 6-by-4
  * 7-by-10
  * 250-by-250
==> passed

Test 1c: check energy() with random pictures in which the RGB components
         of each pixel are in a small range
  * 4-by-6
  * 5-by-5
  * 6-by-4
  * 7-by-10
  * 250-by-250
==> passed

Test 2a: check width() with file inputs
  * 6x5.png
  * 4x6.png
==> passed

Test 2b: check width() with random pictures
  * 4-by-6
  * 5-by-5
  * 6-by-4
  * 7-by-10
==> passed

Test 3a: check height() with file inputs
  * 6x5.png
  * 4x6.png
==> passed

Test 3b: check height() with random pictures
  * 4-by-6
  * 5-by-5
  * 6-by-4
  * 7-by-10
==> passed

Test 4a: check findVerticalSeam() with file inputs
  * 6x5.png
  * 4x6.png
  * 10x12.png
  * 3x7.png
  * 5x6.png
  * 7x3.png
  * 7x10.png
  * 12x10.png
  * stripes.png
  * diagonals.png
  * chameleon.png
  * HJoceanSmall.png
  * 1x8.png
  * 8x1.png
  * 1x1.png
==> passed

Test 4b: check findVerticalSeam() with random pictures
  * 4-by-6
  * 5-by-5
  * 6-by-4
  * 7-by-10
  * 250-by-250
==> passed

Test 4c: check findVerticalSeam() with random pictures in which
         the RGB values of each pixel are in a small range
  * 4-by-6
  * 5-by-5
  * 6-by-4
  * 7-by-10
  * 250-by-250
==> passed

Test 5a: check findHorizontalSeam() with file inputs
  * 6x5.png
  * 4x6.png
  * 10x12.png
  * 3x7.png
  * 5x6.png
  * 7x3.png
  * 7x10.png
  * 12x10.png
  * stripes.png
  * diagonals.png
  * chameleon.png
  * HJoceanSmall.png
  * 1x8.png
  * 8x1.png
  * 1x1.png
==> passed

Test 5b: check findHorizontalSeam() with random pictures
  * 4-by-6
  * 5-by-5
  * 6-by-4
  * 7-by-10
  * 250-by-250
==> passed

Test 5c: check findHorizontalSeam() with random pictures in which the RGB
         components of each pixel are in a small range
  * 4-by-6
  * 5-by-5
  * 6-by-4
  * 7-by-10
  * 250-by-250
==> passed

Test 6a: check removeVerticalSeam() with file inputs and optimal seams
  * 6x5.png
  * 10x12.png
  * 3x7.png
  * 5x6.png
  * 7x3.png
  * 7x10.png
  * 12x10.png
  * stripes.png
  * diagonals.png
  * chameleon.png
  * HJoceanSmall.png
  * 8x1.png
==> passed

Test 6b: check removeVerticalSeam() with random pictures and optimal seams
  * 4-by-6
  * 5-by-5
  * 6-by-4
  * 7-by-10
  * 250-by-250
==> passed

Test 6c: check removeVerticalSeam() with file inputs and random seams
  * 6x5.png
  * 10x12.png
  * 3x7.png
  * 5x6.png
  * 7x3.png
  * 7x10.png
  * 12x10.png
  * stripes.png
  * diagonals.png
  * chameleon.png
  * HJoceanSmall.png
  * 8x1.png
==> passed

Test 6d: check removeVerticalSeam() with random pictures and random seams
  * 4-by-6
  * 5-by-5
  * 6-by-4
  * 7-by-10
  * 250-by-250
==> passed

Test 7a: check removeHorizontalSeam() with file inputs and optimal seams
  * 6x5.png
  * 10x12.png
  * 3x7.png
  * 5x6.png
  * 7x3.png
  * 7x10.png
  * 12x10.png
  * stripes.png
  * diagonals.png
  * chameleon.png
  * HJoceanSmall.png
  * 1x8.png
==> passed

Test 7b: check removeHorizontalSeam() with random pictures and optimal seams
  * 4-by-6
  * 5-by-5
  * 6-by-4
  * 7-by-10
  * 250-by-250
==> passed

Test 7c: check removeHorizontalSeam() with file inputs and random seams
  * 6x5.png
  * 10x12.png
  * 3x7.png
  * 5x6.png
  * 7x3.png
  * 7x10.png
  * 12x10.png
  * stripes.png
  * diagonals.png
  * chameleon.png
  * HJoceanSmall.png
  * 1x8.png
==> passed

Test 7d: check removeHorizontalSeam() with random pictures and random seams
  * 4-by-6
  * 5-by-5
  * 6-by-4
  * 7-by-10
  * 250-by-250
==> passed

Test 8: check energy() with invalid arguments
  * picture = 6x5.png, call energy(-1, 4)
  * picture = 6x5.png, call energy(6, 4)
  * picture = 6x5.png, call energy(5, 5)
  * picture = 6x5.png, call energy(4, -1)
  * picture = 6x5.png, call energy(4, 5)
==> passed

Test 9a: check removeVerticalSeam() with invalid seam
  * picture = 10x10.png
  * picture = 3x7.png
  * picture = 7x3.png
  * picture = 10x12.png
  * picture = 12x10.png
  * picture = 1x8.png
  * picture = 8x1.png
  * picture = 1x1.png
==> passed

Test 9b: check removeHorizontalSeam() with invalid seam
  * picture = 10x10.png
  * picture = 3x7.png
  * picture = 7x3.png
  * picture = 10x12.png
  * picture = 12x10.png
  * picture = 1x8.png
  * picture = 8x1.png
  * picture = 1x1.png
==> passed

Test 9c: check removeHorizontalSeam() and removeVerticalSeam() with null arguments
  * picture = 6x5.png
  * picture = 3x7.png
==> passed

Test 10a: check that client can mutate the Picture object that is passed to the constructor
==> passed

Test 10b: check that client can mutate the Picture object that is returned by picture()
    - repeated calls to picture() return reference to same Picture object
    - so, if the client mutates their copy, picture() will return wrong value

==> FAILED

Test 11: check constructor with null argument
==> passed

Test 12a: check intermixed calls to findHorizontalSeam(), findVerticalSeam(),
          removeHorizontalSeam(), and removeVerticalSeam(), width(), height(),
          energy(), and picture() made with probabilities p1, p2, p3, p4, p5,
          p6, p7, and p8, respectively with optimal seams
  * random 5-by-6 image with p = (0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.5)
  * random 6-by-5 image with p = (0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.5)
  * random 6-by-6 image with p = (0.1, 0.1, 0.2, 0.2, 0.0, 0.0, 0.0, 0.4)
  * random 6-by-6 image with p = (0.2, 0.2, 0.0, 0.0, 0.2, 0.2, 0.2, 0.0)
  * random 6-by-6 image with p = (0.1, 0.1, 0.2, 0.2, 0.1, 0.1, 0.1, 0.1)
  * random 100-by-110 image with p = (0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.5)
  * random 110-by-100 image with p = (0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.5)
  * random 110-by-110 image with p = (0.1, 0.1, 0.2, 0.2, 0.0, 0.0, 0.0, 0.4)
  * random 100-by-100 image with p = (0.2, 0.2, 0.0, 0.0, 0.1, 0.1, 0.2, 0.2)
  * random 110-by-110 image with p = (0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.2, 0.2)
==> passed

Test 12b: check intermixed calls to findHorizontalSeam(), findVerticalSeam(),
          removeHorizontalSeam(), and removeVerticalSeam(), width(), height(),
          energy(), and picture() made with probabilities p1, p2, p3, p4, p5,
          p6, p7, and p8, respectively with random seams
  * random 5-by-6 image with p = (0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.5)
  * random 6-by-5 image with p = (0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.5)
  * random 6-by-6 image with p = (0.1, 0.1, 0.2, 0.2, 0.0, 0.0, 0.0, 0.4)
  * random 6-by-6 image with p = (0.2, 0.2, 0.0, 0.0, 0.2, 0.2, 0.2, 0.0)
  * random 6-by-6 image with p = (0.1, 0.1, 0.2, 0.2, 0.1, 0.1, 0.1, 0.1)
  * random 100-by-110 image with p = (0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.5)
  * random 110-by-100 image with p = (0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.5)
  * random 110-by-110 image with p = (0.1, 0.1, 0.2, 0.2, 0.0, 0.0, 0.0, 0.4)
  * random 100-by-100 image with p = (0.2, 0.2, 0.0, 0.0, 0.1, 0.1, 0.2, 0.2)
  * random 110-by-110 image with p = (0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.2, 0.2)
==> passed

Test 12c: check intermixed calls to findHorizontalSeam(), findVerticalSeam(),
          removeHorizontalSeam(), and removeVerticalSeam(), width(), height(),
          energy(), and picture() made with probabilities p1, p2, p3, p4, p5,
          p6, p7, and p8, respectively with optimal seams
          (tests corner cases when width = 1 or 2 and/or height = 1 or 2)
  * random 1-by-8 image with p = (0.1, 0.1, 0.2, 0.0, 0.1, 0.1, 0.2, 0.2)
  * random 8-by-1 image with p = (0.1, 0.1, 0.0, 0.2, 0.1, 0.1, 0.2, 0.2)
  * random 1-by-1 image with p = (0.2, 0.2, 0.0, 0.0, 0.1, 0.1, 0.2, 0.2)
  * random 2-by-8 image with p = (0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.2, 0.2)
  * random 8-by-2 image with p = (0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.2, 0.2)
  * random 2-by-2 image with p = (0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.2, 0.2)
==> passed


Total: 30/31 tests passed!


================================================================
********************************************************************************
*  MEMORY
********************************************************************************

Analyzing memory of SeamCarver
*-----------------------------------------------------------
Running 6 total tests.

Memory usage of a SeamCarver after removing 2 horizontal
and 2 vertical seams from an n-by-n image.

Maximum allowed memory is ~ 12 n^2 bytes.

                 n       student (bytes)
-------------------------------------------
=> passed       16         2368
=> passed       32         5184
=> passed       64        16960
=> passed      128        65096
=> passed      256       259656
=> passed      512      1041992
==> 6/6 tests passed

Total: 6/6 tests passed!

Estimated student memory (bytes) = 4.00 n^2 - 15.93 n + 1598.28   (R^2 = 1.000)
================================================================



********************************************************************************
*  TIMING
********************************************************************************

Timing SeamCarver
*-----------------------------------------------------------
Reference solution is unoptimized.

Running 17 total tests.

Test 1: create a SeamCarver object for a given 736-by-584 picture;
        then call findHorizontalSeam(), removeHorizontalSeam(),
        findVerticalSeam(), removeVerticalSeam(), and picture()
        one each; count total number of calls to methods in Picture
  * constructor calls        = 3
  * get()    calls per pixel = 2.0
  * set()    calls per pixel = 2.0
  * getRGB() calls per pixel = 7.9
  * setRGB() calls per pixel = 0.0
==> passed

Test 2: create a SeamCarver object for a given 736-by-584 picture;
        then call findHorizontalSeam(), removeHorizontalSeam(),
        findVerticalSeam(), and removeVerticalSeam(), and picture();
        once each; count total number of calls to methods in Color
  * constructor calls per pixel = 2.0
  * getRed()    calls per pixel = 0.0
  * getGreen()  calls per pixel = 0.0
  * getBlue()   calls per pixel = 0.0
  * getRGB()    calls per pixel = 0.0
  * equal number of calls to getRed(), getGreen(), and getBlue()
==> passed

Tests 3a-3c: time removeVerticalSeam() for a given 736-by-584 picture
  * student   solution calls per second:      56.17
  * reference solution calls per second:      55.55
  * reference / student ratio:                 0.99

=> passed      student <= 150.0x reference
=> passed      student <=  15.0x reference
=> passed      student <=   4.5x reference

Tests 4a-4c: time findVerticalSeam() and removeVerticalSeam()
             for a given 736-by-584 picture
  * student   solution calls per second:       7.21
  * reference solution calls per second:       8.63
  * reference / student ratio:                 1.20

=> passed      student <= 150.0x reference
=> passed      student <=  15.0x reference
=> passed      student <=   2.3x reference

Tests 5a-5c: time removeHorizontalSeam() for a given 736-by-584 picture
  * student   solution calls per second:      40.69
  * reference solution calls per second:      15.44
  * reference / student ratio:                 0.38

=> passed      student <= 150.0x reference
=> passed      student <=  15.0x reference
=> passed      student <=   4.5x reference

Tests 6a-6c: time findHorizontalSeam() and removeHorizontalSeam()
             for a given 736-by-584 picture
  * student   solution calls per second:       2.73
  * reference solution calls per second:       5.59
  * reference / student ratio:                 2.05

=> passed      student <= 150.0x reference
=> passed      student <=  15.0x reference
=> passed      student <=   2.3x reference

Tests 7a-7c: time findHorizontalSeam(), removeHorizontalSeam(), findVerticalSeam(),
             and removeVerticalSeam() for a given 736-by-584 picture
  * student   solution calls per second:       2.00
  * reference solution calls per second:       3.56
  * reference / student ratio:                 1.78

=> passed      student <= 150.0x reference
=> passed      student <=  15.0x reference
=> FAILED      student <=   1.5x reference

Total: 16/17 tests passed!


================================================================



