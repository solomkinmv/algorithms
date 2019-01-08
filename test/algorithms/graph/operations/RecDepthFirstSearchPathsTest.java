package algorithms.graph.operations;

public class RecDepthFirstSearchPathsTest extends DepthFirstSearchPathsTest {
    @Override
    DepthFirstSearchPaths createDepthFirstSearchPaths() {
        return new RecursiveDepthFirstSearchPaths(graph, source);
    }
}
