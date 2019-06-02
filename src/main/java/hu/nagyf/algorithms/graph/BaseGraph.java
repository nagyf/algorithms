package hu.nagyf.algorithms.graph;

import java.util.Objects;
import java.util.Optional;

import hu.nagyf.algorithms.datastructures.Array;
import hu.nagyf.algorithms.datastructures.LinkedList;
import hu.nagyf.algorithms.datastructures.map.HashTable;
import hu.nagyf.algorithms.graph.interfaces.Graph;

public abstract class BaseGraph<V, W> implements Graph<V> {

    private int vertexCount;
    private HashTable<V, Integer> indices;
    private Array<LinkedList<Edge<V, W>>> adjacencyList;

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
                    var removable = connections.findIndex(edge -> edge.vertex.equals(vertex));
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

        if (existingEdge.isEmpty()) {
            adjacencyList.get(fromIdx.get()).append(new Edge<>(to, weight));
        }
    }

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

    protected static class Edge<V, W> {
        public final V vertex;
        public final W weight;

        public Edge(V vertex, W weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
