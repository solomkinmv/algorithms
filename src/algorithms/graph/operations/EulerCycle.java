package algorithms.graph.operations;

import algorithms.graph.UndirectedGraph;
import io.vavr.collection.List;
import lombok.Getter;

public class EulerCycle {

    private final UndirectedGraph graph;
    @Getter
    private final java.util.List<Integer> eulerCycle;

    public EulerCycle(UndirectedGraph graph) {
        validate(graph);
        this.graph = new UndirectedGraph(graph);

        eulerCycle = findCycle();
    }

    private void validate(UndirectedGraph graph) {
        if (graph.getVertices().stream()
                 .map(vertex -> numberOfEdges(graph, vertex))
                 .anyMatch(size -> size % 2 == 1)) {
            throw new IllegalArgumentException("Impossible to find Euler cycle for graph");
        }
    }

    private java.util.List<Integer> findCycle() {
        int first = findFirstVertex();
        List<Integer> cycle = findCycle(first);
        if (!graph.isEmpty()) return null;

        return cycle.reverse().toJavaList();
    }

    /*
    If current vertex has no out-going edges (i.e. neighbors) - add it to circuit, remove the last vertex
    from the stack and set it as the current one. Otherwise (in case it has out-going edges, i.e. neighbors) -
    add the vertex to the stack, take any of its neighbors, remove the edge between that vertex and selected neighbor,
    and set that neighbor as the current vertex.
    Repeat step 2 until the current vertex has no more out-going edges (neighbors) and the stack is empty.
     */
    private List<Integer> findCycle(int currentVertex) {
        return List.ofAll(graph.adjacentVertices(currentVertex))
                   .flatMap(nextVertex -> {
                       if (graph.removeEdge(currentVertex, nextVertex)) {
                           return findCycle(nextVertex);
                       }
                       return List.empty();
                   })
                   .append(currentVertex);
    }

    private int numberOfEdges(UndirectedGraph graph, int vertex) {
        return graph.adjacentVertices(vertex)
                    .size();
    }

    private int findFirstVertex() {
        return graph.getVertices().iterator().next();
    }

}
