package algorithms.graph.operations.shortest_path;

import algorithms.graph.DirectedEdgeWeightedGraph;
import algorithms.graph.DirectedEdgeWeightedGraph.DirectedEdge;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1. Consider vertices in increasing order of distance from s (non-tree vertex with the lowest `distTo` value).
 * 2. Add vertex to tree and relax all edges pointing from that vertex.
 */
public class DijkstraShortestPath extends DirectedShortestPath {

    private final Map<Integer, Double> distTo = new HashMap<>();
    private final Map<Integer, DirectedEdge> pathTo = new HashMap<>();

    public DijkstraShortestPath(DirectedEdgeWeightedGraph graph, int startingVertex) {
        super(graph);

        findShortestPath(startingVertex);
    }

    @Override
    public double distTo(int v) {
        return distTo.getOrDefault(v, Double.MAX_VALUE);
    }

    @Override
    public Collection<DirectedEdge> pathTo(int v) {
        LinkedList<DirectedEdge> result = new LinkedList<>();
        DirectedEdge nextEdge = pathTo.get(v);
        while (nextEdge != null) {
            result.addFirst(nextEdge);
            nextEdge = pathTo.get(nextEdge.from());
        }
        return result;
    }

    @Override
    public boolean hasPathTo(int v) {
        return false;
    }

    private void findShortestPath(int startingVertex) {
        PriorityQueue<Integer> closestVertices = new PriorityQueue<>(Comparator.comparingDouble(this::distTo));
        closestVertices.add(startingVertex);
        distTo.put(startingVertex, 0.);
        pathTo.put(startingVertex, null);

        while (!closestVertices.isEmpty()) {
            int v = closestVertices.poll();
            for (DirectedEdge adjacentEdge : graph.adjacentEdges(v)) {
                relaxEdge(adjacentEdge, closestVertices);
            }
        }
    }

    private void relaxEdge(DirectedEdge edge, PriorityQueue<Integer> closestVertices) {
        double weightUsingEdge = distTo(edge.from()) + edge.getWeight();

        if (distTo(edge.to()) > weightUsingEdge) {
            distTo.put(edge.to(), weightUsingEdge);
            pathTo.put(edge.to(), edge);
            if (closestVertices.contains(edge.to())) {

                // these operations are very inefficient
                // todo: rewrite with custom implementation of PQ which allow to decreaseKey()
                closestVertices.remove(edge.to());
                closestVertices.add(edge.to());
            } else {
                closestVertices.add(edge.to());
            }
        }
    }
}
