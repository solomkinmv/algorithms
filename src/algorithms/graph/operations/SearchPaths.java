package algorithms.graph.operations;

import algorithms.graph.Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class SearchPaths {
    final Graph graph;
    private final int source;
    private final Map<Integer, Boolean> marked = new HashMap<>();
    private final Map<Integer, Integer> pathTo = new HashMap<>();

    public SearchPaths(Graph graph, int source) {
        this.graph = graph;
        this.source = source;

        search(source);
    }

    public boolean hasPathTo(int destination) {
        return marked.getOrDefault(destination, false);
    }

    public Optional<java.util.List<Integer>> pathTo(int destination) {
        if (hasPathTo(destination)) {
            int current = destination;
            LinkedList<Integer> result = new LinkedList<>();
            while (current != source) {
                result.addFirst(current);
                current = pathTo.get(current);
            }
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

    abstract void search(int vertex);

    void recordPathToVertex(int vertex, int parentVertex) {
        pathTo.putIfAbsent(vertex, parentVertex);
    }

    void markVertexAsInvestigated(int vertex) {
        marked.put(vertex, true);
    }
}
