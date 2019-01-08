package algorithms.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class UndirectedGraph implements Graph {
    private Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

    @Override
    public void addEdge(int v, int w) {
        addDirectedEdge(v, w);
        addDirectedEdge(w, v);
    }

    @Override
    public Collection<Integer> getVertices() {
        return adjacencyList.keySet();
    }

    @Override
    public Collection<Integer> adjacentVertices(int v) {
        return adjacencyList.getOrDefault(v, emptyList());
    }

    @Override
    public int getNumberOfVertices() {
        return adjacencyList.size();
    }

    @Override
    public int getNumberOfEdges() {
        return adjacencyList.values().stream()
                            .flatMap(List::stream)
                            .mapToInt(v -> v)
                            .sum() / 2;
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

    private void addDirectedEdge(int v, int w) {
        adjacencyList.computeIfAbsent(v, key -> new LinkedList<>())
                     .add(w);
    }
}
