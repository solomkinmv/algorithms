package algorithms.graph;

import java.util.Collection;

public interface Graph {

    void addEdge(int v, int w);

    boolean removeEdge(int v, int w);

    Collection<Integer> getVertices();

    Collection<Integer> adjacentVertices(int v);
    int getNumberOfVertices();
    int getNumberOfEdges();

    boolean isEmpty();
}
