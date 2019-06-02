package hu.nagyf.algorithms.graph;

import java.util.Optional;

import hu.nagyf.algorithms.graph.interfaces.WeightedGraph;

/**
 * This is a specialization of the {@link hu.nagyf.algorithms.graph.BaseGraph}.
 * This class implements an Undirected Weighted graph.
 *
 * @param <V> the type of the key that is used to identify a Vertex
 * @param <W> the type of the weight that is stored with each Edge
 */
public class WeightedUndirectedGraph<V, W> extends BaseGraph<V, W> implements WeightedGraph<V, W> {
    @Override
    public Optional<W> weight(V from, V to) {
        return getDirectedEdge(from, to)
                .or(() -> getDirectedEdge(to, from))
                .map(edge -> edge.weight);
    }

    @Override
    public void addEdge(V from, V to, W weight) {
        // Because it is an undirected graph, we add an edge in both directions
        addDirectedWeightedEdge(from, to, weight);
        addDirectedWeightedEdge(to, from, weight);
    }
}
