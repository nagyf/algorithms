package hu.nagyf.algorithms.graph;

import java.util.Optional;

import hu.nagyf.algorithms.graph.interfaces.WeightedGraph;

/**
 * This is a specialization of the {@link hu.nagyf.algorithms.graph.BaseGraph}.
 * This class implements a Directed Weighted graph.
 *
 * @param <V> the type of the key that is used to identify a Vertex
 * @param <W> the type of the weight that is stored with each Edge
 */
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
