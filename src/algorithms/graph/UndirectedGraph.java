package algorithms.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptySet;

public class UndirectedGraph implements Graph {
    private final Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();

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
    public void removeEdge(int v, int w) {
        removeDirectedEdge(v, w);
        removeDirectedEdge(w, v);
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
        return adjacencyList.values().stream()
                            .flatMap(Set::stream)
                            .mapToInt(v -> v)
                            .sum() / 2;
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
                result.append(adjacentVertex)
                      .append(" -> ")
                      .append(adjacentVertex)
                      .append(System.lineSeparator());
            }
        }

        return result.toString();
    }

    private void removeDirectedEdge(int v, int w) {
        if (!adjacentVertices(v).removeIf(adj -> adj == w)) {
            throw new IllegalArgumentException(String.format("Couldn't delete edge %d -> %d: %s", v, w, adjacencyList));
        }
        if (adjacentVertices(v).size() == 0) {
            adjacencyList.remove(v);
        }
    }

    private void addDirectedEdge(int v, int w) {
        adjacencyList.computeIfAbsent(v, key -> new HashSet<>())
                     .add(w);
    }
}
