package coding_problems.sedgewick.coursera.course1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FastCollinearPoints {

    private final Point[] points;
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        validate(points);

        this.points = copy(points);
        calculateSegments();
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    private Point[] copy(Point[] a) {
        Point[] copy = new Point[a.length];
        System.arraycopy(a, 0, copy, 0, a.length);
        return copy;
    }

    private void validate(Point[] points) {
        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
            }
        }
    }

    private void calculateSegments() {
        ArrayList<LineSegment> segments = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            Point p0 = points[i];
            Comparator<Point> pointComparator = p0.slopeOrder();
            Arrays.sort(points, i + 1, points.length, pointComparator);


            for (int j = i + 1; j < points.length - 1; j++) {
                double slope = p0.slopeTo(points[j]);
                int k = j + 1;
                while (k < points.length && p0.slopeTo(points[k]) == slope) {
                    k++;
                }
                ArrayList<Point> linePoints = new ArrayList<>();
                while (j < k) {
                    linePoints.add(points[j++]);
                }
                j = k - 1;
                if (linePoints.size() > 2) {
                    linePoints.add(p0);
                    Collections.sort(linePoints);
                    segments.add(new LineSegment(linePoints.get(0), linePoints.get(linePoints.size() - 1)));
                }
            }
        }
        this.segments = segments.toArray(new LineSegment[segments.size()]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }
}
