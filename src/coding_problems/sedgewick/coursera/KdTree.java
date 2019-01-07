package coding_problems.sedgewick.coursera;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KdTree {
    private Node root;

    // construct an empty set of points
    public KdTree() {
    }

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        return isEmpty() ? 0 : root.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            root = new Node(p, new RectHV(0, 0, 1, 1));
        } else {
            root.insert(p, true);
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        return !isEmpty() && root.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        if (!isEmpty()) {
            root.draw(true);
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }

        List<Point2D> accumulator = new ArrayList<>();
        if (!isEmpty()) {
            root.range(rect, true, accumulator);
        }
        return accumulator;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) return null;

        return root.nearest(p, root.point, true);
    }

    private static class Node {
        // the point
        private final Point2D point;
        // the axis-aligned rectangle corresponding to this node
        private RectHV rect;
        // the left/bottom subtree
        private Node lb;
        // the right/top subtree
        private Node rt;
        private int size = 1;

        private Node(Point2D point, RectHV rect) {
            this.point = point;
            this.rect = rect;
        }

        public Node(Point2D point) {
            this.point = point;
        }

        private int size() {
            return size;
        }

        private int sizeOrZero(Node n) {
            return n == null ? 0 : n.size();
        }

        private boolean contains(Point2D p) {
            return rect.contains(p) && (point.equals(p) || nodeContainsPoint(lb, p) || nodeContainsPoint(rt, p));
        }

        private boolean nodeContainsPoint(Node n, Point2D p) {
            return n != null && n.contains(p);
        }

        private RectHV splitRect(boolean horizontalOrientation, boolean rt) {
            if (horizontalOrientation) {
                if (rt) {
                    return new RectHV(point.x(), rect.ymin(), rect.xmax(), rect.ymax());
                }
                return new RectHV(rect.xmin(), rect.ymin(), point.x(), rect.ymax());
            }

            if (rt) {
                return new RectHV(rect.xmin(), point.y(), rect.xmax(), rect.ymax());
            }
            return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), point.y());
        }

        private Node insert(Point2D p, boolean horizontalOrientation) {
            if (point.equals(p)) return this;

            if (horizontalOrientation ? p.x() >= point.x() : p.y() >= point.y()) {
                if (rt == null) {
                    rt = new Node(p, splitRect(horizontalOrientation, true));
                } else {
                    rt.insert(p, !horizontalOrientation);
                }
            } else {
                if (lb == null) {
                    lb = new Node(p, splitRect(horizontalOrientation, false));
                } else {
                    lb.insert(p, !horizontalOrientation);
                }
            }

            size = 1 + sizeOrZero(lb) + sizeOrZero(rt);

            return this;
        }

        private void draw(boolean horizontalOrientation) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(point.x(), point.y());

            StdDraw.setPenRadius();
            if (horizontalOrientation) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(point.x(), rect.ymin(), point.x(), rect.ymax());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(rect.xmin(), point.y(), rect.xmax(), point.y());
            }

            if (rt != null) rt.draw(!horizontalOrientation);
            if (lb != null) lb.draw(!horizontalOrientation);
        }

        private void range(RectHV rec, boolean horizontalOrientation, Collection<Point2D> accumulator) {
            if (rec.contains(point)) {
                accumulator.add(point);
            }

            if (horizontalOrientation) {
                if (rec.xmin() < point.x() && lb != null) {
                    lb.range(rec, !horizontalOrientation, accumulator);
                }
                if (rec.xmax() >= point.x() && rt != null) {
                    rt.range(rec, !horizontalOrientation, accumulator);
                }
            } else {
                if (rec.ymin() < point.y() && lb != null) {
                    lb.range(rec, !horizontalOrientation, accumulator);
                }
                if (rec.ymax() >= point.y() && rt != null) {
                    rt.range(rec, !horizontalOrientation, accumulator);
                }
            }
        }

        private Point2D nearest(Point2D p, Point2D nearestPoint, boolean horizontalOrientation) {
            if (rect.distanceSquaredTo(p) > p.distanceSquaredTo(nearestPoint)) return nearestPoint;

            nearestPoint = closest(p, nearestPoint, point);
            if (rtDirection(p, horizontalOrientation)) {
                nearestPoint = closest(p, nearestPoint, rt, horizontalOrientation);

                double d = p.distanceSquaredTo(nearestPoint);
                if (d >= bestDistance(p, horizontalOrientation)) {
                    return closest(p, nearestPoint, lb, horizontalOrientation);
                }
            } else {
                nearestPoint = closest(p, nearestPoint, lb, horizontalOrientation);

                double d = p.distanceSquaredTo(nearestPoint);
                if (d >= bestDistance(p, horizontalOrientation)) {
                    return closest(p, nearestPoint, rt, horizontalOrientation);
                }
            }

            return nearestPoint;
        }

        private boolean rtDirection(Point2D targetPoint, boolean horizontalOrientation) {
            if (horizontalOrientation) {
                return targetPoint.x() >= point.x();
            } else {
                return targetPoint.y() >= point.y();
            }
        }

        private double bestDistance(Point2D targetPoint, boolean horizontalOrientation) {
            if (horizontalOrientation) {
                double d = Math.abs(targetPoint.x() - point.x());
                return d * d;

            }
            double d = Math.abs(targetPoint.y() - point.y());
            return d * d;
        }

        private Point2D closest(Point2D target, Point2D nearestPoint, Node node, boolean horizontalOrientation) {
            if (node == null) {
                return nearestPoint;
            }

            return closest(target, nearestPoint, node.nearest(target, nearestPoint, !horizontalOrientation));
        }

        private Point2D closest(Point2D target, Point2D p1, Point2D p2) {
            double d1 = target.distanceSquaredTo(p1);
            double d2 = target.distanceSquaredTo(p2);
            if (d1 < d2) return p1;
            return p2;
        }
    }
}
