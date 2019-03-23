package algorithms.graph;

import algorithms.graph.EdgeWeightedGraph.Edge;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EdgeWeightedGraphTest {

    @Test
    void theSameEdgesAreEqualAndHaveSameHashCode() {
        Edge edge = new Edge(1, 2, 15);

        assertEqualsAndHashCode(edge, edge);
    }

    @Test
    void edgesWithSameVerticesInDifferentOrderAreEqualAndHaveSameHashCode() {
        Edge edge1 = new Edge(1, 2, 15);
        Edge edge2 = new Edge(2, 1, 15);

        assertEqualsAndHashCode(edge1, edge2);
    }

    private void assertEqualsAndHashCode(Edge edge1, Edge edge2) {
        assertThat(edge1)
                .isEqualTo(edge2);
        assertThat(edge1.hashCode())
                .isEqualTo(edge2);
    }
}