package algorithms.graph.operations;

import algorithms.graph.Graph;

import java.util.LinkedList;

public class IterativeDepthFirstSearchPaths extends DepthFirstSearchPaths {

    public IterativeDepthFirstSearchPaths(Graph graph, int source) {
        super(graph, source);
    }

    @Override
    void dfs(int vertex) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(vertex);

        while (!stack.isEmpty()) {
            int v = stack.pop();
            for (int adjacentV : graph.adjacentVertices(v)) {
                if (hasPathTo(adjacentV)) continue;

                markVertexAsInvestigated(adjacentV);
                recordPathToVertex(adjacentV, v);
                stack.push(adjacentV);
            }
        }
    }
}
