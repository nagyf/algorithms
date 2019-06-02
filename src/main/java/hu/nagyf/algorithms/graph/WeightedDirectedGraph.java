package hu.nagyf.algorithms.graph;

import java.util.Optional;

import hu.nagyf.algorithms.graph.interfaces.WeightedGraph;

public class WeightedDirectedGraph<V, W> extends BaseGraph<V, W> implements WeightedGraph<V, W> {

    @Override
    public Optional<W> weight(final V from, final V to) {
        return getDirectedEdge(from, to).map(edge -> edge.weight);
    }

    @Override
    public void addEdge(final V from, final V to, final W weight) {
        addDirectedWeightedEdge(from, to, weight);
    }
}
