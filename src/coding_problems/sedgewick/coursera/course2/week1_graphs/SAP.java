package coding_problems.sedgewick.coursera.course2.week1_graphs;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SAP {

    private final Digraph graph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        graph = G;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        String digraphPath = "resources/coding_problems/sedgewick/coursera/course2/week1_graphs/digraph25.txt";
        In in = new In(digraphPath);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
//        while (!StdIn.isEmpty()) {
//            int v = StdIn.readInt();
//            int w = StdIn.readInt();
//            int length = sap.length(v, w);
//            int ancestor = sap.ancestor(v, w);
//            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//        }
        System.out.println(sap.ancestor(Arrays.asList(13, 23, 24), Arrays.asList(6, 16, 17)));
//        System.out.println(sap.ancestor(13, 16));
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return shortestPathWithPrint(v, w).length();
    }

    // a ancestor ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return shortestPathWithPrint(v, w).ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return shortestPathWithPrint(v, w).length();
    }

    // a ancestor ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return shortestPathWithPrint(v, w).ancestor;
    }

    private ShortestPath shortestPathWithPrint(int v, int w) {
        ShortestPath result = shortestPath(v, w);
        System.out.println(result);
        return result;
    }

    private ShortestPath shortestPathWithPrint(Iterable<Integer> v, Iterable<Integer> w) {
        ShortestPath result = shortestPath(v, w);
        System.out.println(result);
        return result;
    }

    private ShortestPath shortestPath(Iterable<Integer> vIterable, Iterable<Integer> wIterable) {
        ShortestPath result = null;
        for (int v : vIterable) {
            result = shortestPath(result, shortestPath(v, wIterable));
        }
        return result;
    }

    private ShortestPath shortestPath(int v, Iterable<Integer> wIterable) {
        ShortestPath result = null;
        for (int w : wIterable) {
            result = shortestPath(result, shortestPath(v, w));
        }
        return result;
    }

    private ShortestPath shortestPath(ShortestPath nullablePath1, ShortestPath path2) {
        if (nullablePath1 == null) {
            return path2;
        }
        if (nullablePath1.length() > path2.length()) {
//            System.out.println("Path1: " + nullablePath1 + ", Path2: " + path2 + ". Shortest: " + path2);
            return path2;
        }
//        System.out.println("Path1: " + nullablePath1 + ", Path2: " + path2 + ". Shortest: " + nullablePath1);
        return nullablePath1;
    }

    private ShortestPath shortestPath(int v, int w) {
//        System.out.println(v + "\t" + w);

        // use 2 simultaneous BFS {
        Set<Integer> investigated1 = new HashSet<>();
        Set<Integer> investigated2 = new HashSet<>();

        Map<Integer, Integer> previous1 = new HashMap<>();
        previous1.put(v, v);
        Map<Integer, Integer> previous2 = new HashMap<>();
        previous2.put(w, w);

        Queue<Integer> queue1 = new LinkedList<>();
        queue1.offer(v);

        Queue<Integer> queue2 = new LinkedList<>();
        queue2.offer(w);

        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            ShortestPath w1 = checkNeighbourElementsFromQueue(investigated1, investigated2, previous1, previous2, queue1);
            if (w1 != null) return w1;
            ShortestPath w2 = checkNeighbourElementsFromQueue(investigated2, investigated1, previous2, previous1, queue2);
            if (w2 != null) return w2;
        }
        return ShortestPath.empty();
    }

    private ShortestPath checkNeighbourElementsFromQueue(Set<Integer> investigated1,
                                                         Set<Integer> investigated2,
                                                         Map<Integer, Integer> previous1,
                                                         Map<Integer, Integer> previous2,
                                                         Queue<Integer> queue) {
        if (!queue.isEmpty()) {
            int v1 = queue.poll();
            Iterable<Integer> adj1 = graph.adj(v1);
            for (int w1 : adj1) {
                if (!investigated1.contains(w1)) {
                    investigated1.add(w1);
                    previous1.put(w1, v1);
                    queue.offer(w1);
                }
                if (investigated2.contains(w1)) {
                    return new ShortestPath(w1, buildPath(previous1, previous2, w1));
                }
            }
        }
        return null;
    }

    private List<Integer> buildPath(Map<Integer, Integer> previous1, Map<Integer, Integer> previous2, int ancestor) {
        LinkedList<Integer> result = new LinkedList<>();
        int val = ancestor;
        while (val != (val = previous1.get(val))) {
            result.addFirst(val);
        }
        result.addLast(ancestor);
        val = ancestor;
        while (val != (val = previous2.get(val))) {
            result.addLast(val);
        }
        return result;
    }

    static class ShortestPath {
        final int ancestor;
        final List<Integer> path;

        ShortestPath(int ancestor, List<Integer> path) {
            this.ancestor = ancestor;
            this.path = path;
        }

        static ShortestPath empty() {
            return new ShortestPath(-1, Collections.emptyList());
        }

        int length() {
            return path.size() - 1;
        }

        @Override
        public String toString() {
            return "Ancestor: " + ancestor + ". Path: " + path;
        }
    }
}
