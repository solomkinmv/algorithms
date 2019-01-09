package algorithms.graph.operations;

import algorithms.graph.Graph;

public class RecursiveDepthFirstSearchPaths extends DepthFirstSearchPaths {

    public RecursiveDepthFirstSearchPaths(Graph graph, int source) {
        super(graph, source);
    }

    @Override
    void dfs(int vertex) {
        markVertexAsInvestigated(vertex);

        for (int adjacentVertex : graph.adjacentVertices(vertex)) {
            if (hasPathTo(adjacentVertex)) continue;

            recordPathToVertex(adjacentVertex, vertex);
            dfs(adjacentVertex);
        }
    }
}
