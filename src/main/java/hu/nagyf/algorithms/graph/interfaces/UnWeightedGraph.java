package hu.nagyf.algorithms.graph.interfaces;

/**
 * Generic interface for Unweighted graphs.
 * Unweighted graphs does not store a weight for the Edges, so each Edge weights the same.
 *
 * @param <V> the type of the key that is used to identify a Vertex
 */
public interface UnWeightedGraph<V> extends Graph<V> {

    /**
     * Adds an edge between the 2 vertices.
     * Directed and undirected graphs may implement this method differently, so the order of the parameters may matter.
     *
     * @param from the starting vertex ID
     * @param to the ending vertex ID
     */
    void addEdge(final V from, final V to);
}
