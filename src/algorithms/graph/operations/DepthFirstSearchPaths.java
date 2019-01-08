package algorithms.graph.operations;

import algorithms.graph.Graph;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

public class DepthFirstSearchPaths {
    private final Graph graph;
    private final int source;
    private final boolean[] marked;
    private final int[] pathTo;

    public DepthFirstSearchPaths(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        marked = new boolean[graph.getNumberOfVertices()];
        pathTo = new int[graph.getNumberOfVertices()];

        dfs(source);
    }

    public boolean hasPathTo(int destination) {
        return marked[destination];
    }

    public Optional<Collection<Integer>> pathTo(int destination) {
        if (hasPathTo(destination)) {
            int current = destination;
            LinkedList<Integer> result = new LinkedList<>();
            while (current != source) {
                result.addFirst(current);
                current = pathTo[current];
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
        pathTo[vertex] = parentVertex;
    }

    private void markVertexAsInvestigated(int vertex) {
        marked[vertex] = true;
    }
}
