package hu.nagyf.algorithms.graph;

import hu.nagyf.algorithms.graph.interfaces.UnWeightedGraph;

/**
 * This is a specialization of the {@link hu.nagyf.algorithms.graph.BaseGraph}.
 * This class implements a Directed Unweighted graph.
 *
 * Under the hood each edge has a weight of {@link #DEFAULT_WEIGHT}.
 *
 * @param <V> the type of the key that is used to identify a Vertex
 */
public class DirectedGraph<V> extends BaseGraph<V, Integer> implements UnWeightedGraph<V> {

    /**
     * The weight of each edge.
     */
    private static final Integer DEFAULT_WEIGHT = 1;

    @Override
    public void addEdge(final V from, final V to) {
        addDirectedWeightedEdge(from, to, DEFAULT_WEIGHT);
    }
}
