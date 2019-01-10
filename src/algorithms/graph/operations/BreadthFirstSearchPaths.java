package algorithms.graph.operations;

import algorithms.graph.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class BreadthFirstSearchPaths extends SearchPaths {

    public BreadthFirstSearchPaths(Graph graph, int source) {
        super(graph, source);
    }

    public Map<Integer, Integer> getDistances() {
        return graph.getVertices().stream()
                    .collect(toMap(identity(), v -> pathTo(v).map(List::size)
                                                             .orElse(-1)));
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
