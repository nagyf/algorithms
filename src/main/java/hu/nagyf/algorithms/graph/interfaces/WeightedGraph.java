package hu.nagyf.algorithms.graph.interfaces;

import java.util.Optional;

/**
 * Generic interface for Weighted graphs.
 * Weighted graphs store a weight for each edge.
 *
 * @param <V> the type of the key that is used to identify a Vertex
 * @param <W> the type of the weight that is stored with each Edge
 */
public interface WeightedGraph<V, W> extends Graph<V> {

    /**
     * Determines the weight of the edge between the two vertices.
     *
     * @param from the starting vertex of the edge
     * @param to the ending vertex of the edge
     * @return an optional weight, or an empty value if there is no Edge between the two vertices
     */
    Optional<W> weight(final V from, final V to);

    /**
     * Adds a weighted edge between the 2 vertices.
     * Directed and undirected graphs may implement this method differently, so the order of the parameters may matter.
     *
     * @param from the starting vertex ID
     * @param to the ending vertex ID
     * @param weight the weight of the edge
     */
    void addEdge(final V from, final V to, final W weight);
}
