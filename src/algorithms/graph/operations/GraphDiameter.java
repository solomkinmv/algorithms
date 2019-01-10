package algorithms.graph.operations;

import algorithms.graph.UndirectedGraph;
import io.vavr.Tuple2;
import lombok.Getter;

import java.util.Comparator;
import java.util.Map;

public class GraphDiameter {

    private final UndirectedGraph graph;
    @Getter
    private final Tuple2<Integer, Integer> diameter;

    public GraphDiameter(UndirectedGraph graph) {
        this.graph = graph;

        diameter = findDiameter();
    }

    private Tuple2<Integer, Integer> findDiameter() {
        int anyVertex = findFirstVertex();
        int farthestVertex1 = findFarthestFromVertex(anyVertex);
        int farthestVertex2 = findFarthestFromVertex(farthestVertex1);
        return new Tuple2<>(farthestVertex1, farthestVertex2);
    }

    private int findFarthestFromVertex(int vertex) {
        return new BreadthFirstSearchPaths(graph, vertex).getDistances()
                                                         .entrySet().stream()
                                                         .max(Comparator.comparingInt(Map.Entry::getValue))
                                                         .map(Map.Entry::getKey)
                                                         .orElseThrow();
    }

    private Integer findFirstVertex() {
        return graph.getVertices().iterator().next();
    }
}
