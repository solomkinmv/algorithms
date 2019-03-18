package algorithms.graph.operations;

import algorithms.graph.DirectedGraph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Definition:
 * Vertices v and w are strongly connected if there is a
 * directed path from v to w and directed path from w to v.
 * <p>
 * A strong component is a maximum subset of strongly connected vertices.
 */
public class StronglyConnectedComponents {

    private final LinkedList<Integer> postOrder = new LinkedList<>();
    private final Set<Integer> investigated = new HashSet<>();
    private final Set<Set<Integer>> stronglyConnectedComponents = new HashSet<>();
    private final Set<Integer> stronglyConnectedComponent = new HashSet<>();

    public StronglyConnectedComponents(DirectedGraph graph) {
        // Compute reverse postorder
        DirectedGraph reversedGraph = graph.reverse();
        for (int v : reversedGraph.getVertices()) {
            if (!investigated.contains(v)) {
                dfsToGetPostOrder(reversedGraph, v);
            }
        }

        investigated.clear();

        // Find strongly connected components by visiting vertices in reverse postorder
        for (int v : postOrder) {
            if (!investigated.contains(v)) {
                dfsToMarkConnectedComponents(graph, v);
                stronglyConnectedComponents.add(new HashSet<>(stronglyConnectedComponent));
                stronglyConnectedComponent.clear();
            }
        }
    }

    public Set<Set<Integer>> getStronglyConnectedComponents() {
        return stronglyConnectedComponents;
    }

    private void dfsToMarkConnectedComponents(DirectedGraph graph, int v) {
        investigated.add(v);
        stronglyConnectedComponent.add(v);

        for (int w : graph.adjacentVertices(v)) {
            if (!investigated.contains(w)) {
                dfsToMarkConnectedComponents(graph, w);
            }
        }
    }

    private void dfsToGetPostOrder(DirectedGraph reversedGraph, int v) {
        investigated.add(v);

        for (int w : reversedGraph.adjacentVertices(v)) {
            if (!investigated.contains(w)) {
                dfsToGetPostOrder(reversedGraph, w);
            }
        }

        postOrder.push(v);
    }
}
