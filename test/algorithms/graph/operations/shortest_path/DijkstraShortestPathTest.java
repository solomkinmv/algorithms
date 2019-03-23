package algorithms.graph.operations.shortest_path;

import algorithms.graph.DirectedEdgeWeightedGraph;
import algorithms.graph.DirectedEdgeWeightedGraph.DirectedEdge;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DijkstraShortestPathTest {


    @Test
    void findsShortestPath() {
        DirectedEdge zeroOne = new DirectedEdge(0, 1, 5);
        DirectedEdge zeroFour = new DirectedEdge(0, 4, 9);
        DirectedEdge zeroSeven = new DirectedEdge(0, 7, 8);
        DirectedEdge oneTwo = new DirectedEdge(1, 2, 12);
        DirectedEdge oneThree = new DirectedEdge(1, 3, 15);
        DirectedEdge oneSeven = new DirectedEdge(1, 7, 4);
        DirectedEdge twoThree = new DirectedEdge(2, 3, 3);
        DirectedEdge twoSix = new DirectedEdge(2, 6, 11);
        DirectedEdge threeSix = new DirectedEdge(3, 6, 9);
        DirectedEdge fourFive = new DirectedEdge(4, 5, 4);
        DirectedEdge fourSix = new DirectedEdge(4, 6, 20);
        DirectedEdge fourSeven = new DirectedEdge(4, 7, 5);
        DirectedEdge fiveTwo = new DirectedEdge(5, 2, 1);
        DirectedEdge fiveSix = new DirectedEdge(5, 6, 13);
        DirectedEdge sevenTwo = new DirectedEdge(7, 2, 7);
        DirectedEdge sevenFive = new DirectedEdge(7, 5, 6);
        DirectedEdgeWeightedGraph graph = new DirectedEdgeWeightedGraph();

        graph.addEdge(zeroOne);
        graph.addEdge(zeroFour);
        graph.addEdge(zeroSeven);

        graph.addEdge(oneTwo);
        graph.addEdge(oneThree);
        graph.addEdge(oneSeven);

        graph.addEdge(twoThree);
        graph.addEdge(twoSix);

        graph.addEdge(threeSix);

        graph.addEdge(fourFive);
        graph.addEdge(fourSix);
        graph.addEdge(fourSeven);

        graph.addEdge(fiveTwo);
        graph.addEdge(fiveSix);

        graph.addEdge(sevenTwo);
        graph.addEdge(sevenFive);

        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph, 0);

        assertThat(dijkstraShortestPath.pathTo(1))
                .isEqualTo(Arrays.asList(zeroOne));
        assertThat(dijkstraShortestPath.pathTo(2))
                .isEqualTo(Arrays.asList(zeroFour, fourFive, fiveTwo));
        assertThat(dijkstraShortestPath.pathTo(3))
                .isEqualTo(Arrays.asList(zeroFour, fourFive, fiveTwo, twoThree));
        assertThat(dijkstraShortestPath.pathTo(4))
                .isEqualTo(Arrays.asList(zeroFour));
        assertThat(dijkstraShortestPath.pathTo(5))
                .isEqualTo(Arrays.asList(zeroFour, fourFive));
        assertThat(dijkstraShortestPath.pathTo(6))
                .isEqualTo(Arrays.asList(zeroFour, fourFive, fiveTwo, twoSix));
        assertThat(dijkstraShortestPath.pathTo(7))
                .isEqualTo(Arrays.asList(zeroSeven));
    }
}