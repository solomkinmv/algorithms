package algorithms.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DirectedGraph extends UndirectedGraph {

    private final Map<Integer, Integer> numberOfIncomingDegrees = new HashMap<>();

    @Override
    public void addEdge(int v, int w) {
        addDirectedEdge(v, w);
        numberOfIncomingDegrees.merge(v, 1, (oldVal, newVal) -> oldVal + newVal);
        adjacencyList.computeIfAbsent(w, key -> new HashSet<>());
        numberOfEdges++;
    }

    @Override
    public boolean removeEdge(int v, int w) {
        return removeDirectedEdge(v, w);
    }

    public DirectedGraph reverse() {
        DirectedGraph result = new DirectedGraph();
        for (int v : getVertices()) {
            for (int w : adjacentVertices(v)) {
                result.addEdge(w, v);
            }
        }

        return result;
    }

    @Override
    protected boolean removeDirectedEdge(int v, int w) {
        boolean deleted = adjacentVertices(v).removeIf(adj -> adj == w);
        Integer newValue = numberOfIncomingDegrees.computeIfPresent(w, (key, oldVal) -> {
            int nextNumberOfIndegree = oldVal - 1;
            if (nextNumberOfIndegree == 0) return null;
            return nextNumberOfIndegree;
        });
        if (newValue == null && adjacentVertices(v).size() == 0 ) {
            adjacencyList.remove(v);
        }
        return deleted;
    }
}
