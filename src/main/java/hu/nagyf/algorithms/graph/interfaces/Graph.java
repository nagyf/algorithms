package hu.nagyf.algorithms.graph.interfaces;

public interface Graph<V> {

    void addVertex(final V vertex);

    boolean removeVertex(final V vertex);

    boolean containsVertex(final V vertex);

    boolean isConnected(final V from, final V to);
}
