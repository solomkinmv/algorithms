package algorithms.graph.operations;

import algorithms.graph.DirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class TopologicalSortTest {

    @Test
    void returnsEmptyCollectionForEmptyGraph() {
        TopologicalSort topologicalSort = new TopologicalSort(new DirectedGraph());

        assertThat(topologicalSort.getOrderedVertices())
                .isEmpty();
    }

    @Test
    void sortsVertices() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(1, 4);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(3, 6);
        graph.addEdge(5, 2);
        graph.addEdge(6, 0);
        graph.addEdge(6, 4);

        TopologicalSort topologicalSort = new TopologicalSort(graph);

        assertThat(topologicalSort.getOrderedVertices())
                .isEqualTo(Arrays.asList(4, 1, 2, 5, 0, 6, 3));
    }
}