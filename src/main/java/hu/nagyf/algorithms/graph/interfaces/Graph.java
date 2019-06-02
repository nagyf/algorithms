package hu.nagyf.algorithms.graph.interfaces;

/**
 * Generic graph interface. Any kind of graph (directed/undirected, weighted/non-weighted) should implement these methods.
 *
 * @param <V> the type of the key that is used to identify a Vertex
 */
public interface Graph<V> {

    /**
     * Adds a new vertex to the graph.
     * @param vertex the vertex ID of type V
     */
    void addVertex(final V vertex);

    /**
     * Removes a vertex from the graph and every edge connected to that vertex.
     *
     * @param vertex the vertex ID of type V
     * @return true if there was a vertex removed, false if nothing changed (e.g. because the vertex was not in the graph)
     */
    boolean removeVertex(final V vertex);

    /**
     * Checks whether the specified vertex is in the graph.
     *
     * @param vertex the vertex ID of type V
     * @return true if the vertex is in the graph, false otherwise
     */
    boolean containsVertex(final V vertex);

    /**
     * Checks whether the 2 vertex is connected in the graph (i.e. there is at least 1 edge between them).
     * Directed and undirected graphs may implement this method differently.
     *
     * @param from the starting vertex ID of type V
     * @param to the ending vertex ID of type V
     * @return true if the 2 is connected, false otherwise
     */
    boolean isConnected(final V from, final V to);
}
