package hu.nagyf.algorithms.graph;

import hu.nagyf.algorithms.graph.interfaces.UnWeightedGraph;

public class UnDirectedGraph<V> extends BaseGraph<V, Integer> implements UnWeightedGraph<V> {

    private static final Integer DEFAULT_WEIGHT = 1;

    @Override
    public void addEdge(V from, V to) {
        addDirectedWeightedEdge(from, to, DEFAULT_WEIGHT);
        addDirectedWeightedEdge(to, from, DEFAULT_WEIGHT);
    }
}
