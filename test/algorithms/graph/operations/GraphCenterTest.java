package algorithms.graph.operations;

import algorithms.graph.UndirectedGraph;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GraphCenterTest {

    private GraphCenter graphCenter;

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

        graphCenter = new GraphCenter(graph);
    }

    @Test
    void findsCorrectDiameter() {
        assertThat(graphCenter.getCenter())
                .is(new Condition<>(v -> v == 5 || v == 2, null));
    }

}