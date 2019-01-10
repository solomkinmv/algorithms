package algorithms.graph.operations;

import algorithms.graph.UndirectedGraph;
import lombok.Getter;

import java.util.List;

public class GraphCenter {

    private final UndirectedGraph graph;
    @Getter
    private final int center;

    public GraphCenter(UndirectedGraph graph) {
        this.graph = graph;

        center = findCenter();
    }

    private int findCenter() {
        GraphDiameter diameter = new GraphDiameter(graph);

        List<Integer> diameterPath = new BreadthFirstSearchPaths(graph, diameter.getDiameter()._1)
                .pathTo(diameter.getDiameter()._2)
                .orElseThrow();

        return diameterPath.get(diameterPath.size() / 2);
    }

}
