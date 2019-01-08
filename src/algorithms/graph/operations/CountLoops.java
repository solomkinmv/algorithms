package algorithms.graph.operations;

import algorithms.graph.Graph;

public class CountLoops {

    public static int countSelfLoops(Graph graph) {
        int count = 0;
        for (int vertex : graph.getVertices()) {
            for (int adjacentVertex : graph.adjacentVertices(vertex)) {
                if (vertex == adjacentVertex) count++;
            }
        }
        return count;
    }
}
