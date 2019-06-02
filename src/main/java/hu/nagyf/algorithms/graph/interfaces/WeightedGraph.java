package hu.nagyf.algorithms.graph.interfaces;

import java.util.Optional;

public interface WeightedGraph<V, W> extends Graph<V> {

    Optional<W> weight(final V from, final V to);

    void addEdge(final V from, final V to, final W weight);
}
