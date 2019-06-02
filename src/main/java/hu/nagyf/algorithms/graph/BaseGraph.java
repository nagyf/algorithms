package hu.nagyf.algorithms.graph;

import java.util.Objects;
import java.util.Optional;

import hu.nagyf.algorithms.datastructures.Array;
import hu.nagyf.algorithms.datastructures.LinkedList;
import hu.nagyf.algorithms.datastructures.map.HashTable;
import hu.nagyf.algorithms.graph.interfaces.Graph;

/**
 * The base class for all Graph implementations.
 * This class implements all the common functionality of the graphs.
 * It is implemented in a way to support both Directed/Undirected and Weighted/Unweighted graphs.
 * The implementation uses an Adjacency List representation for the graph,
 * i.e.  it associates each vertex in the graph with the collection of its neighboring vertices or edges.
 *
 * @param <V> the type of the key that is used to identify a Vertex
 * @param <W> the type of the weight that is stored with each Edge
 */
public abstract class BaseGraph<V, W> implements Graph<V> {

    /**
     * The number of vertices stored in the graph.
     */
    private int vertexCount;
    /**
     * This table stores the index of the vertex in the {@link #adjacencyList} array.
     */
    private HashTable<V, Integer> indices;
    private Array<LinkedList<Edge<V, W>>> adjacencyList;

    /**
     * Constructs an empty graph
     */
    public BaseGraph() {
        vertexCount = 0;
        indices = new HashTable<>();
        adjacencyList = new Array<>();
    }

    @Override
    public void addVertex(final V vertex) {
        indices.put(vertex, vertexCount);
        adjacencyList.set(vertexCount, new LinkedList<>());
        ++vertexCount;
    }

    @Override
    public boolean removeVertex(V vertex) {
        var idx = indices.get(vertex);

        if (idx.isEmpty()) {
            return false;
        }

        indices.remove(vertex);
        adjacencyList.set(idx.get(), adjacencyList.get(vertexCount - 1));
        adjacencyList.set(vertexCount - 1, null);
        --vertexCount;

        adjacencyList.stream()
                .filter(Objects::nonNull)
                .forEach(connections -> {
                    var removable = connections.findFirstIndex(edge -> edge.vertex.equals(vertex));
                    removable.ifPresent(connections::removeAt);
                });

        return true;
    }

    @Override
    public boolean containsVertex(V vertex) {
        return indices.get(vertex).isPresent();
    }

    @Override
    public boolean isConnected(V from, V to) {
        var fromIdx = indices.get(from);
        var toIdx = indices.get(to);

        if (fromIdx.isEmpty() || toIdx.isEmpty()) {
            return false;
        }

        return adjacencyList.get(fromIdx.get()).stream().anyMatch(edge -> edge.vertex.equals(to));
    }

    /**
     * Adds a directed and weighted edge between the 2 specified vertices.
     * Multiple edges from the same source to the same destination is not allowed.
     *
     * @param from the edge originates from this vertex
     * @param to the edge ends in this vertex
     * @param weight the weight of this edge
     */
    protected void addDirectedWeightedEdge(final V from, final V to, final W weight) {
        var fromIdx = indices.get(from);
        var toIdx = indices.get(to);

        if (fromIdx.isEmpty()) {
            addVertex(from);
            fromIdx = indices.get(from);
        }

        if (toIdx.isEmpty()) {
            addVertex(to);
        }

        var existingEdge = adjacencyList.get(fromIdx.get()).stream()
                .filter(e -> e.vertex.equals(to))
                .findFirst();

        // Only adds an edge if it doesn't exist already
        if (existingEdge.isEmpty()) {
            adjacencyList.get(fromIdx.get()).append(new Edge<>(to, weight));
        }
    }

    /**
     * Returns a directed edge between the 2 vertices. The order of the parameters matter!
     *
     * @param from the edge originates from this vertex
     * @param to the edge ends in this vertex
     * @return an optional edge, that is empty if there is no edge between these vertices
     */
    protected Optional<Edge<V, W>> getDirectedEdge(final V from, final V to) {
        var fromIdx = indices.get(from);
        var toIdx = indices.get(to);

        if (fromIdx.isEmpty() || toIdx.isEmpty()) {
            return Optional.empty();
        }

        return adjacencyList.get(fromIdx.get()).stream()
                .filter(e -> e.vertex.equals(to))
                .findFirst();
    }

    /**
     * A minimal implementation of an Edge in the graph.
     * The source vertex is not stored because it would be redundant.
     *
     * @param <V> the type of the key that is used to identify a Vertex
     * @param <W> the type of the weight that is stored with each Edge
     */
    protected static class Edge<V, W> {
        public final V vertex;
        public final W weight;

        public Edge(V vertex, W weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
