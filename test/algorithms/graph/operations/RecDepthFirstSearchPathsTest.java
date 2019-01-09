package algorithms.graph.operations;

public class RecDepthFirstSearchPathsTest extends SearchPathsTest {
    @Override
    SearchPaths createSearchPaths() {
        return new RecursiveDepthFirstSearchPaths(graph, source);
    }
}
