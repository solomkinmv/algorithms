package algorithms.graph.operations;

import algorithms.graph.Graph;

public class DegreeCounter {

    public static int computeVertexDegree(Graph graph, int vertex) {
        return graph.adjacentVertices(vertex).size();
    }

    public static int computeMaxVertexDegree(Graph graph) {
        return graph.getVertices().stream()
                    .mapToInt(v -> computeVertexDegree(graph, v))
                    .max()
                    .orElse(0);
    }

    public static int computeAverageDegree(Graph graph) {
        return 2 * graph.getNumberOfEdges() / graph.getNumberOfVertices();
    }
}
