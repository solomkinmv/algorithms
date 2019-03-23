package algorithms.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class EdgeWeightedGraph {

    private final Map<Integer, Collection<Edge>> adjacencyMap = new HashMap<>();

    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);

        adjacencyMap.computeIfAbsent(v, HashSet::new).add(edge);
        adjacencyMap.computeIfAbsent(w, HashSet::new).add(edge);
    }

    public Collection<Edge> adj(int v) {
        return adjacencyMap.getOrDefault(v, Collections.emptyList());
    }

    public static class Edge implements Comparable<Edge> {
        private final int v;
        private final int w;
        private final double weight;

        public Edge(int v, int w, double weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int either() {
            return v;
        }

        @Override
        public int compareTo(Edge that) {
            if (this.weight < that.weight) return -1;
            if (this.weight > that.weight) return 1;
            return 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(v * w, weight);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge that = (Edge) o;
            return Double.compare(that.weight, weight) == 0 &&
                    (v == that.v && w == that.w) ||
                    (v == that.w && w == that.v);
        }

        private int other(int v) {
            if (this.v == v) {
                return w;
            }

            return this.v;
        }
    }
}
