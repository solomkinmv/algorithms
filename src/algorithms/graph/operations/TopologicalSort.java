package algorithms.graph.operations;

import algorithms.graph.DirectedGraph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class TopologicalSort {

    private final Set<Integer> investigated;
    private final LinkedList<Integer> order;

    public TopologicalSort(DirectedGraph graph) {
        if (new DirectedCycle(graph).hasCycle()) {
            throw new IllegalArgumentException("Can't make topological sort for graph with cycles");
        }

        investigated = new HashSet<>();
        order = new LinkedList<>();

        for (int v : graph.getVertices()) {
            if (!investigated.contains(v)) {
                dfs(graph, v);
            }
        }

        Collections.reverse(order);
    }

    public Collection<Integer> getOrderedVertices() {
        return order;
    }

    private void dfs(DirectedGraph graph, int v) {
        investigated.add(v);

        for (int w : graph.adjacentVertices(v)) {
            if (!investigated.contains(w)) {
                dfs(graph, w);
            }
        }

        order.push(v);
    }
}
