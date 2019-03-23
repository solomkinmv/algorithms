package algorithms.graph.operations.shortest_path;

import algorithms.graph.DirectedEdgeWeightedGraph;
import algorithms.graph.DirectedEdgeWeightedGraph.DirectedEdge;

import java.util.Collection;

public abstract class DirectedShortestPath {

    final DirectedEdgeWeightedGraph graph;

    public DirectedShortestPath(DirectedEdgeWeightedGraph graph) {
        this.graph = graph;
    }

    public abstract double distTo(int v);

    public abstract Collection<DirectedEdge> pathTo(int v);

    public abstract boolean hasPathTo(int v);
}
