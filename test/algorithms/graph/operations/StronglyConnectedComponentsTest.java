package algorithms.graph.operations;

import algorithms.graph.DirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class StronglyConnectedComponentsTest {

    @Test
    void returnsNoConnectedComponentsForEmptyGraph() {
        StronglyConnectedComponents scc = new StronglyConnectedComponents(new DirectedGraph());

        assertThat(scc.getStronglyConnectedComponents())
                .isEmpty();
    }

    @Test
    void returnsStronglyConnectedComponents() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 5);

        graph.addEdge(2, 0);
        graph.addEdge(2, 3);

        graph.addEdge(3, 2);
        graph.addEdge(3, 5);

        graph.addEdge(4, 2);
        graph.addEdge(4, 3);

        graph.addEdge(5, 4);

        graph.addEdge(6, 0);
        graph.addEdge(6, 4);
        graph.addEdge(6, 8);

        graph.addEdge(7, 6);
        graph.addEdge(7, 9);

        graph.addEdge(8, 6);

        graph.addEdge(9, 10);
        graph.addEdge(9, 11);

        graph.addEdge(10, 12);

        graph.addEdge(11, 4);
        graph.addEdge(11, 12);

        graph.addEdge(12, 9);

        StronglyConnectedComponents stronglyConnectedComponents = new StronglyConnectedComponents(graph);

        assertThat(stronglyConnectedComponents.getStronglyConnectedComponents())
                .containsOnly(setOf(1),
                              setOf(7),
                              setOf(6, 8),
                              setOf(9, 10, 11, 12),
                              setOf(0, 2, 3, 4, 5));
    }

    private Set<Integer> setOf(Integer... values) {
        return new HashSet<>(Arrays.asList(values));
    }
}