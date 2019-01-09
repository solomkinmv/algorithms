package algorithms.graph.operations;

public class IterativeDepthFirstSearchPathsTest extends SearchPathsTest {
    @Override
    SearchPaths createSearchPaths() {
        return new IterativeDepthFirstSearchPaths(graph, source);
    }
}
