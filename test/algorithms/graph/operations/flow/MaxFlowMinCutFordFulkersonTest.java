package algorithms.graph.operations.flow;

import algorithms.graph.FlowNetwork;
import algorithms.graph.FlowNetwork.FlowEdge;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MaxFlowMinCutFordFulkersonTest {

    @Test
    void createsMaxFlowMinCutFromFlowNetwork() {
        FlowNetwork flow = new FlowNetwork();
        flow.addEdge(new FlowEdge(1, 2, 10));
        flow.addEdge(new FlowEdge(1, 4, 5));
        flow.addEdge(new FlowEdge(1, 7, 15));

        flow.addEdge(new FlowEdge(2, 3, 9));
        flow.addEdge(new FlowEdge(2, 4, 4));
        flow.addEdge(new FlowEdge(2, 5, 15));

        flow.addEdge(new FlowEdge(3, 5, 15));
        flow.addEdge(new FlowEdge(3, 6, 10));

        flow.addEdge(new FlowEdge(4, 5, 8));
        flow.addEdge(new FlowEdge(4, 7, 4));

        flow.addEdge(new FlowEdge(5, 6, 10));
        flow.addEdge(new FlowEdge(5, 8, 15));

        flow.addEdge(new FlowEdge(7, 8, 16));

        flow.addEdge(new FlowEdge(8, 6, 10));
        flow.addEdge(new FlowEdge(8, 4, 6));

        MaxFlowMinCutFordFulkerson maxFlowMinCutFordFulkerson = new MaxFlowMinCutFordFulkerson(flow, 1, 6);

        assertThat(maxFlowMinCutFordFulkerson.value()).isEqualTo(28);
    }
}