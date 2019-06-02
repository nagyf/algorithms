package hu.nagyf.algorithms.graph.interfaces;

public interface UnWeightedGraph<V> extends Graph<V> {

    void addEdge(final V from, final V to);
}
