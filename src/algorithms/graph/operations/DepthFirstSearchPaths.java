package algorithms.graph.operations;

import algorithms.graph.Graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DepthFirstSearchPaths {
    private final Graph graph;
    private final int source;
    private final Map<Integer, Boolean> marked = new HashMap<>();
    private final Map<Integer, Integer> pathTo = new HashMap<>();

    public DepthFirstSearchPaths(Graph graph, int source) {
        this.graph = graph;
        this.source = source;

        graph.getVertices()
             .forEach(v -> marked.put(v, false));

        dfs(source);
    }

    public boolean hasPathTo(int destination) {
        return marked.getOrDefault(destination, false);
    }

    public Optional<Collection<Integer>> pathTo(int destination) {
        if (hasPathTo(destination)) {
            int current = destination;
            LinkedList<Integer> result = new LinkedList<>();
            while (current != source) {
                result.addFirst(current);
                current = pathTo.get(current);
            }
            result.addFirst(current);
            return Optional.of(result);
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        String prefix = getClass().getSimpleName() +
                ": " +
                System.lineSeparator() +
                "Source = " +
                source +
                System.lineSeparator();

        return prefix + graph.getVertices().stream()
                             .filter(this::hasPathTo)
                             .map(String::valueOf)
                             .collect(Collectors.joining(", "));
    }

    private void dfs(int vertex) {
        markVertexAsInvestigated(vertex);

        for (int adjacentVertex : graph.adjacentVertices(vertex)) {
            if (hasPathTo(adjacentVertex)) continue;

            recordPathToVertex(adjacentVertex, vertex);
            dfs(adjacentVertex);
        }
    }

    private void recordPathToVertex(int vertex, int parentVertex) {
        pathTo.putIfAbsent(vertex, parentVertex);
    }

    private void markVertexAsInvestigated(int vertex) {
        marked.put(vertex, true);
    }
}
