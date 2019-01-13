package algorithms.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptySet;

public class UndirectedGraph implements Graph {
    private final Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();

    public UndirectedGraph() {
    }

    public UndirectedGraph(Graph graph) {
        for (int v1 : graph.getVertices()) {
            for (int v2 : graph.adjacentVertices(v1)) {
                addEdge(v1, v2);
            }
        }
    }

    @Override
    public void addEdge(int v, int w) {
        addDirectedEdge(v, w);
        addDirectedEdge(w, v);
    }

    @Override
    public boolean removeEdge(int v, int w) {
        return removeDirectedEdge(v, w) && removeDirectedEdge(w, v);
    }

    @Override
    public Collection<Integer> getVertices() {
        return adjacencyList.keySet();
    }

    @Override
    public Collection<Integer> adjacentVertices(int v) {
        return adjacencyList.getOrDefault(v, emptySet());
    }

    @Override
    public int getNumberOfVertices() {
        return adjacencyList.size();
    }

    @Override
    public int getNumberOfEdges() {
        return (int) (adjacencyList.values().stream()
                                   .flatMap(Set::stream)
                                   .mapToInt(v -> v)
                                   .count() / 2);
    }

    @Override
    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getClass().getSimpleName())
                .append(": ")
                .append(System.lineSeparator());

        for (int vertex : getVertices()) {
            for (int adjacentVertex : adjacentVertices(vertex)) {
                result.append(vertex)
                      .append(" -> ")
                      .append(adjacentVertex)
                      .append(System.lineSeparator());
            }
        }

        return result.toString();
    }

    private boolean removeDirectedEdge(int v, int w) {
        boolean deleted = adjacentVertices(v).removeIf(adj -> adj == w);
        if (adjacentVertices(v).size() == 0) {
            adjacencyList.remove(v);
        }
        return deleted;
    }

    private void addDirectedEdge(int v, int w) {
        adjacencyList.computeIfAbsent(v, key -> new HashSet<>())
                     .add(w);
    }
}
