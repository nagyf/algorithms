package hu.nagyf.algorithms.graph;

import java.util.Optional;

import hu.nagyf.algorithms.graph.interfaces.WeightedGraph;

public class WeightedUndirectedGraph<V, W> extends BaseGraph<V, W> implements WeightedGraph<V, W> {
    @Override
    public Optional<W> weight(V from, V to) {
        return getDirectedEdge(from, to)
                .or(() -> getDirectedEdge(to, from))
                .map(edge -> edge.weight);
    }

    @Override
    public void addEdge(V from, V to, W weight) {
        addDirectedWeightedEdge(from, to, weight);
        addDirectedWeightedEdge(to, from, weight);
    }
}
