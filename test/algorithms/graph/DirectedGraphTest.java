package algorithms.graph;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectedGraphTest {

    @Test
    void addsEdge() {
        DirectedGraph graph = new DirectedGraph();

        int v1 = 1, v2 = 2;
        graph.addEdge(v1, v2);

        assertThat(graph.getNumberOfVertices())
                .isEqualTo(2);
        assertThat(graph.getNumberOfEdges())
                .isEqualTo(1);
        assertThat(graph.getVertices())
                .containsOnly(v1, v2);
        assertThat(graph.adjacentVertices(v1))
                .containsOnly(v2);
        assertThat(graph.adjacentVertices(v2))
                .isEmpty();
        assertThat(graph.isEmpty())
                .isFalse();
    }

    @Test
    void emptyGraphHasNoVerticesAndEdges() {
        DirectedGraph graph = new DirectedGraph();

        assertThat(graph.isEmpty())
                .isTrue();
        assertThat(graph.getNumberOfEdges())
                .isZero();
        assertThat(graph.getNumberOfVertices())
                .isZero();
    }
}