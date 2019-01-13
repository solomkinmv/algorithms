package algorithms.graph.operations;

import algorithms.graph.UndirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EulerCycleTest {

    @Test
    void generatedCorrectEulerCycle() {
        UndirectedGraph graph = new UndirectedGraph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 6);
        graph.addEdge(1, 8);
        graph.addEdge(1, 9);

        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 8);

        graph.addEdge(3, 4);

        graph.addEdge(5, 7);
        graph.addEdge(5, 8);

        graph.addEdge(6, 9);

        graph.addEdge(7, 8);

        List<Integer> eulerCycle = new EulerCycle(graph).getEulerCycle();

        assertCycle(eulerCycle, graph);
    }

    @Test
    void generatedCorrectSmallEulerCycle() {
        UndirectedGraph graph = new UndirectedGraph();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);

        List<Integer> eulerCycle = new EulerCycle(graph).getEulerCycle();

        assertCycle(eulerCycle, graph);
    }

    private void assertCycle(List<Integer> path, UndirectedGraph graph) {
        assertThat(path).isNotNull();
        assertThat(path).last()
                        .isEqualTo(path.get(0));
        assertThat(path)
                .hasSize(graph.getNumberOfEdges() + 1);

        for (int i = 0; i < path.size() - 1; i++) {
            int last = path.get(i);
            int first = path.get(i + 1);

            assertThat(graph.adjacentVertices(last))
                    .contains(first);
        }
    }
}