package algorithms.graph.operations;

import algorithms.graph.Graph;
import algorithms.graph.UndirectedGraph;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

abstract class DepthFirstSearchPathsTest {

    final int source = 0;
    Graph graph;
    private DepthFirstSearchPaths depthFirstSearchPaths;

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

        depthFirstSearchPaths = createDepthFirstSearchPaths();
    }

    abstract DepthFirstSearchPaths createDepthFirstSearchPaths();

    @ParameterizedTest
    @ValueSource(ints = {source, 1, 2, 3, 4, 5})
    void returnsTrueConnectedVertices(int connectedVertex) {
        assertThat(depthFirstSearchPaths.hasPathTo(connectedVertex))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {100500, 500100})
    void returnsFalseForNotCollectedVertices(int notConnectedVertex) {
        assertThat(depthFirstSearchPaths.hasPathTo(notConnectedVertex))
                .isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {source, 1, 2, 3, 4, 5})
    void createsCorrectPathTo(int vertexDestination) {
        Optional<List<Integer>> pathTo = depthFirstSearchPaths.pathTo(vertexDestination);

        assertThat(pathTo).isPresent();
        assertPath(pathTo.get(), vertexDestination);
    }

    @ParameterizedTest
    @ValueSource(ints = {100500, 500100})
    void returnsEmptyOptionalForNotConnectedVertex(int notConnectedVertex) {
        Optional<List<Integer>> pathTo = depthFirstSearchPaths.pathTo(notConnectedVertex);

        assertThat(pathTo).isEmpty();
    }

    private void assertPath(List<Integer> path, int destination) {
        assertThat(path)
                .first()
                .isEqualTo(source);

        assertThat(path)
                .last()
                .isEqualTo(destination);

        int prev = source;
        for (int current : path.drop(1)) {
            assertThat(graph.adjacentVertices(prev))
                    .contains(current);
            prev = current;
        }
    }
}