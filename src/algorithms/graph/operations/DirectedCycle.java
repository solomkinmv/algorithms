package algorithms.graph.operations;

import algorithms.graph.DirectedGraph;

import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptyList;

public class DirectedCycle {

    private final Set<Integer> investigated;
    private final Map<Integer, Integer> edgeTo;
    private final Set<Integer> onStack;

    private Deque<Integer> cycle;

    public DirectedCycle(DirectedGraph graph) {
        onStack = new HashSet<>();
        edgeTo = new HashMap<>();
        investigated = new HashSet<>();

        for (int v : graph.getVertices()) {
            if (!investigated.contains(v)) {
                dfs(graph, v);
            }
        }
    }

    public Collection<Integer> cycle() {
        return cycle == null ? emptyList() : cycle;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    private void dfs(DirectedGraph graph, int v) {
        onStack.add(v);
        investigated.add(v);

        for (int w : graph.adjacentVertices(v)) {
            if (hasCycle()) return;
            if (!investigated.contains(w)) {
                edgeTo.put(w, v);
                dfs(graph, w);
            } else if (onStack.contains(w)) {
                cycle = new LinkedList<>();
                for (int x = v; x != w; x = edgeTo.get(x)) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }

        onStack.remove(v);
    }
}
