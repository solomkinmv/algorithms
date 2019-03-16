package algorithms.graph.operations;

import algorithms.graph.DirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DirectedCycleTest {

    @Test
    void doesNotDetectCycleOnEmptyGraph() {
        DirectedGraph graph = new DirectedGraph();
        DirectedCycle directedCycle = new DirectedCycle(graph);

        assertThat(directedCycle.hasCycle())
                .isFalse();
        assertThat(directedCycle.cycle())
                .isEmpty();
    }

    @Test
    void detectsCycle() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 21);
        graph.addEdge(1, 22);
        graph.addEdge(1, 23);

        graph.addEdge(2, 3);
        graph.addEdge(2, 30);
        graph.addEdge(2, 31);
        graph.addEdge(2, 32);
        graph.addEdge(2, 33);

        graph.addEdge(3, 4);

        graph.addEdge(4, 5);

        graph.addEdge(5, 6);

        graph.addEdge(6, 3);
        graph.addEdge(6, 70);

        graph.addEdge(21, 22);
        graph.addEdge(22, 23);

        DirectedCycle directedCycle = new DirectedCycle(graph);

        assertThat(directedCycle.hasCycle())
                .isTrue();
        assertThat(directedCycle.cycle())
                .isEqualTo(Arrays.asList(6, 3, 4, 5, 6));
    }
}