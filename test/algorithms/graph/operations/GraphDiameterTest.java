package algorithms.graph.operations;

import algorithms.graph.UndirectedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class GraphDiameterTest {

    private GraphDiameter graphDiameter;

    @BeforeEach
    void setUp() {
        UndirectedGraph graph = new UndirectedGraph();
        graph.addEdge(0, 5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);

        graph.addEdge(1, 2);

        graph.addEdge(2, 3);
        graph.addEdge(2, 4);

        graph.addEdge(3, 5);
        graph.addEdge(3, 4);
        graph.addEdge(3, 6);

        graphDiameter = new GraphDiameter(graph);
    }

    @Test
    @SuppressWarnings("unchecked")
    void findsCorrectDiameter() {
        assertThat((List<Integer>) graphDiameter.getDiameter().toSeq().toJavaList())
                .containsOnly(0, 6);
    }
}