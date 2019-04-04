package algorithms.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyList;

public class FlowNetwork {

    private Map<Integer, Collection<FlowEdge>> adjacencyMap = new HashMap<>();

    public void addEdge(FlowEdge edge) {
        adjacencyMap.computeIfAbsent(edge.from(), ArrayList::new)
                    .add(edge);
        adjacencyMap.computeIfAbsent(edge.to(), ArrayList::new)
                    .add(edge);
    }

    public Collection<FlowEdge> adjacencyEdges(int vertex) {
        return adjacencyMap.getOrDefault(vertex, emptyList());
    }

    @Override
    public String toString() {
        return "FlowNetwork{" +
                "adjacencyMap=" + adjacencyMap +
                '}';
    }

    public static class FlowEdge {
        private int v;
        private int w;
        private double capacity;
        private double flow;

        public FlowEdge(int v, int w, double capacity) {
            this.v = v;
            this.w = w;
            this.capacity = capacity;
        }

        public int from() {
            return v;
        }

        public int to() {
            return w;
        }

        public double capacity() {
            return capacity;
        }

        public double flow() {
            return flow;
        }

        public int other(int vertex) {
            if (v == vertex) return w;
            if (w == vertex) return v;
            throw new IllegalArgumentException("Illegal vertex: " + vertex);
        }

        public double residualCapacityTo(int vertex) {
            if (vertex == v) return flow; // backward edge
            if (vertex == w) return capacity - flow; // forward edge
            throw new IllegalArgumentException("Illegal vertex: " + vertex);
        }

        public void addResidualFlowTo(int vertex, double delta) {
            if (vertex == v) flow -= delta; // backward edge
            else if (vertex == w) flow += delta; // forward edge
            else throw new IllegalArgumentException("Illegal vertex: " + vertex);
        }

        @Override
        public String toString() {
            return String.format("%d---%.2f/%.2f--->%d", v, flow, capacity, w);
        }
    }
}
