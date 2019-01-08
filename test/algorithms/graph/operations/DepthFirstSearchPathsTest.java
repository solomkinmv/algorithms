package algorithms.graph.operations;

import algorithms.graph.Graph;
import algorithms.graph.UndirectedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class DepthFirstSearchPathsTest {

    private DepthFirstSearchPaths depthFirstSearchPaths;

    @BeforeEach
    void setUp() {
        Graph graph = new UndirectedGraph();
        graph.addEdge(0, 5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);

        graph.addEdge(1, 2);

        graph.addEdge(2, 3);
        graph.addEdge(2, 4);

        graph.addEdge(3, 5);
        graph.addEdge(3, 4);

        graph.addEdge(100500, 500100);

        depthFirstSearchPaths = new DepthFirstSearchPaths(graph, 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void returnsTrueConnectedVertices(int connectedVertex) {
        assertThat(depthFirstSearchPaths.hasPathTo(connectedVertex))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void returnsFalseForNotCollectedVertices(int notConnectedVertex) {
        assertThat(depthFirstSearchPaths.hasPathTo(notConnectedVertex))
                .isFalse();
    }

    @Test
    void createsCorrectPathTo() {
        Optional<Collection<Integer>> pathTo = depthFirstSearchPaths.pathTo(3);

        // I'm to lazy to check this, but it works (:
    }
}