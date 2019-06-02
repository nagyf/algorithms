package hu.nagyf.algorithms.graph;

import hu.nagyf.algorithms.graph.interfaces.UnWeightedGraph;

public class DirectedGraph<V> extends BaseGraph<V, Integer> implements UnWeightedGraph<V> {

    private static final Integer DEFAULT_WEIGHT = 1;

    @Override
    public void addEdge(final V from, final V to) {
        addDirectedWeightedEdge(from, to, DEFAULT_WEIGHT);
    }
}
