package algorithms.graph.operations;

import algorithms.graph.Graph;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearchPaths extends SearchPaths {

    public BreadthFirstSearchPaths(Graph graph, int source) {
        super(graph, source);
    }

    @Override
    void search(int source) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int adjacentVertex : graph.adjacentVertices(vertex)) {
                if (hasPathTo(adjacentVertex)) continue;

                markVertexAsInvestigated(adjacentVertex);
                recordPathToVertex(adjacentVertex, vertex);
                queue.offer(adjacentVertex);
            }
        }
    }
}
