package algorithms.graph.operations;

public class IterativeDepthFirstSearchPathsTest extends DepthFirstSearchPathsTest {
    @Override
    DepthFirstSearchPaths createDepthFirstSearchPaths() {
        return new IterativeDepthFirstSearchPaths(graph, source);
    }
}
