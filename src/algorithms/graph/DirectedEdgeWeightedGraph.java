package algorithms.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class DirectedEdgeWeightedGraph {

    private final Map<Integer, Collection<DirectedEdge>> adjacencyMap = new HashMap<>();
    private int numberOfEdges;

    public void addEdge(int v, int w, double weight) {
        addEdge(new DirectedEdge(v, w, weight));
    }

    public void addEdge(DirectedEdge edge) {
        adjacencyMap.computeIfAbsent(edge.from(), HashSet::new).add(edge);
        adjacencyMap.computeIfAbsent(edge.to(), HashSet::new);
        numberOfEdges++;
    }

    public int getNumberOfVertices() {
        return adjacencyMap.size();
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public Collection<DirectedEdge> adjacentEdges(int v) {
        return adjacencyMap.getOrDefault(v, Collections.emptyList());
    }

    public static class DirectedEdge implements Comparable<DirectedEdge> {
        private final int v;
        private final int w;
        private final double weight;

        public DirectedEdge(int v, int w, double weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int from() {
            return v;
        }

        public int to() {
            return w;
        }

        public double getWeight() {
            return weight;
        }

        @Override
        public int compareTo(DirectedEdge that) {
            if (this.weight < that.weight) return -1;
            if (this.weight > that.weight) return 1;
            return 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(v, w, weight);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DirectedEdge that = (DirectedEdge) o;
            return Double.compare(that.weight, weight) == 0 &&
                    v == that.v &&
                    w == that.w;
        }

        @Override
        public String toString() {
            return v + "---" + weight + "--->" + w;
        }
    }
}
