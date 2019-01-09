package algorithms.graph.operations;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BreadthFirstSearchPathsTest extends SearchPathsTest {
    @Override
    SearchPaths createSearchPaths() {
        return new BreadthFirstSearchPaths(graph, source);
    }

    @Test
    void findsShortestPath() {
        Optional<List<Integer>> pathTo = searchPaths.pathTo(4);

        assertThat(pathTo)
                .contains(List.of(0, 2, 4));
    }

}
