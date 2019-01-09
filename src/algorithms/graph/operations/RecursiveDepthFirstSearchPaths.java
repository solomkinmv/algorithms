package algorithms.graph.operations;

import algorithms.graph.Graph;

public class RecursiveDepthFirstSearchPaths extends SearchPaths {

    public RecursiveDepthFirstSearchPaths(Graph graph, int source) {
        super(graph, source);
    }

    @Override
    void search(int vertex) {
        markVertexAsInvestigated(vertex);

        for (int adjacentVertex : graph.adjacentVertices(vertex)) {
            if (hasPathTo(adjacentVertex)) continue;

            recordPathToVertex(adjacentVertex, vertex);
            search(adjacentVertex);
        }
    }
}
