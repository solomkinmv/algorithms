package algorithms.graph.operations.flow;

import algorithms.graph.FlowNetwork;
import algorithms.graph.FlowNetwork.FlowEdge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MaxFlowMinCutFordFulkerson {

    private final Map<Integer, FlowEdge> edgeTo = new HashMap<>(); // last edge on s -> v path
    private final Set<Integer> investigated = new HashSet<>(); // contains if s -> is residual network
    private double value; // value of flow

    public MaxFlowMinCutFordFulkerson(FlowNetwork graph, int startVertex, int targetVertex) {
        while (hasAugmentingPath(graph, startVertex, targetVertex)) {
            double bottle = Double.POSITIVE_INFINITY;
            // compute bottleneck capacity
            for (int v = targetVertex; v != startVertex; v = edgeTo.get(v).other(v)) {
                bottle = Math.min(bottle, edgeTo.get(v).residualCapacityTo(v));
            }

            // augment flow
            for (int v = targetVertex; v != startVertex; v = edgeTo.get(v).other(v)) {
                edgeTo.get(v).addResidualFlowTo(v, bottle);
            }

            value += bottle;
        }
    }

    public boolean inCut(int vertex) {
        return investigated.contains(vertex);
    }

    public double value() {
        return value;
    }

    private boolean hasAugmentingPath(FlowNetwork graph, int startVertex, int targetVertex) {
        edgeTo.clear();
        investigated.clear();

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startVertex);
        investigated.add(startVertex);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            for (FlowEdge edge : graph.adjacencyEdges(v)) {
                int w = edge.other(v);

                // found path from s to v in the residual network?
                if (edge.residualCapacityTo(w) > 0 && !investigated.contains(w)) {
                    edgeTo.put(w, edge);
                    investigated.add(w);
                    queue.offer(w);
                }
            }
        }

        return investigated.contains(targetVertex); // is target is reachable from start in residual network?
    }
}
