package algorithms.graph.operations;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
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
                .contains(List.of(2, 4));
    }

    @Test
    void createsShortestPaths() {
        // self
        assertThat(searchPaths.pathTo(0))
                .contains(List.of());

        // dist 1
        assertThat(searchPaths.pathTo(1))
                .contains(List.of(1));
        assertThat(searchPaths.pathTo(2))
                .contains(List.of(2));
        assertThat(searchPaths.pathTo(5))
                .contains(List.of(5));

        // dist 2
        assertThat(searchPaths.pathTo(3))
                .matches(res -> res.filter(path -> path.equals(List.of(2, 3)) || path.equals(List.of(5, 3)))
                                   .isPresent());
        assertThat(searchPaths.pathTo(4))
                .contains(List.of(2, 4));
    }

    @Test
    void calculatesCorrectDistances() {
        Map<Integer, Integer> distances = ((BreadthFirstSearchPaths) searchPaths).getDistances();

        assertThat(distances)
                .isEqualTo(Map.of(0, 0,
                                  1, 1,
                                  2, 1,
                                  5, 1,
                                  3, 2,
                                  4, 2,
                                  100500, -1,
                                  500100, -1));
    }
}
