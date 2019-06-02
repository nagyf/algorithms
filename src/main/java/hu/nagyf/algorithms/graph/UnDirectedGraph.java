package hu.nagyf.algorithms.graph;

import hu.nagyf.algorithms.graph.interfaces.UnWeightedGraph;

/**
 * This is a specialization of the {@link hu.nagyf.algorithms.graph.BaseGraph}.
 * This class implements an Undirected Unweighted graph.
 *
 * Under the hood each edge has a weight of {@link #DEFAULT_WEIGHT}.
 *
 * @param <V> the type of the key that is used to identify a Vertex
 */
public class UnDirectedGraph<V> extends BaseGraph<V, Integer> implements UnWeightedGraph<V> {

    private static final Integer DEFAULT_WEIGHT = 1;

    @Override
    public void addEdge(V from, V to) {
        // Because it is an undirected graph, we add an edge in both directions
        addDirectedWeightedEdge(from, to, DEFAULT_WEIGHT);
        addDirectedWeightedEdge(to, from, DEFAULT_WEIGHT);
    }
}
