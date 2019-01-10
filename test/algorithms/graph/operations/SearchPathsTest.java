package algorithms.graph.operations;

import algorithms.graph.Graph;
import algorithms.graph.UndirectedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

abstract class SearchPathsTest {

    final int source = 0;
    Graph graph;
    SearchPaths searchPaths;

    @BeforeEach
    void setUp() {
        graph = new UndirectedGraph();
        graph.addEdge(source, 5);
        graph.addEdge(source, 1);
        graph.addEdge(source, 2);

        graph.addEdge(1, 2);

        graph.addEdge(2, 3);
        graph.addEdge(2, 4);

        graph.addEdge(3, 5);
        graph.addEdge(3, 4);

        graph.addEdge(100500, 500100);

        searchPaths = createSearchPaths();
    }

    abstract SearchPaths createSearchPaths();

    @ParameterizedTest
    @ValueSource(ints = {source, 1, 2, 3, 4, 5})
    void returnsTrueConnectedVertices(int connectedVertex) {
        assertThat(searchPaths.hasPathTo(connectedVertex))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {100500, 500100})
    void returnsFalseForNotCollectedVertices(int notConnectedVertex) {
        assertThat(searchPaths.hasPathTo(notConnectedVertex))
                .isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void createsCorrectPathTo(int vertexDestination) {
        Optional<List<Integer>> pathTo = searchPaths.pathTo(vertexDestination);

        assertThat(pathTo).isPresent();
        assertPath(pathTo.get(), vertexDestination);
    }

    @Test
    void pathToSelfIsEmpty() {
        Optional<List<Integer>> pathTo = searchPaths.pathTo(source);

        assertThat(pathTo)
                .contains(emptyList());
    }

    @ParameterizedTest
    @ValueSource(ints = {100500, 500100})
    void returnsEmptyOptionalForNotConnectedVertex(int notConnectedVertex) {
        Optional<List<Integer>> pathTo = searchPaths.pathTo(notConnectedVertex);

        assertThat(pathTo).isEmpty();
    }

    private void assertPath(List<Integer> path, int destination) {
        assertThat(path)
                .last()
                .isEqualTo(destination);

        int prev = source;
        for (int current : path) {
            assertThat(graph.adjacentVertices(prev))
                    .contains(current);
            prev = current;
        }
    }
}