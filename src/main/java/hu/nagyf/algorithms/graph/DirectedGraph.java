package hu.nagyf.algorithms.graph;

import java.util.Objects;

import hu.nagyf.algorithms.datastructures.Array;
import hu.nagyf.algorithms.datastructures.LinkedList;
import hu.nagyf.algorithms.datastructures.map.HashTable;

public class DirectedGraph<V> {

    private int vertexCount;
    private HashTable<V, Integer> indices;
    private Array<LinkedList<V>> adjacencyList;

    public DirectedGraph() {
        vertexCount = 0;
        indices = new HashTable<>();
        adjacencyList = new Array<>();
    }

    public void addVertex(final V vertex) {
        indices.put(vertex, vertexCount);
        adjacencyList.set(vertexCount, new LinkedList<>());
        ++vertexCount;
    }

    public boolean removeVertex(final V vertex) {
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
                    var removable = connections.findIndex(vertex);
                    removable.ifPresent(connections::removeAt);
                });

        return true;
    }

    public boolean containsVertex(final V vertex) {
        return indices.get(vertex).isPresent();
    }

    public boolean isConnected(final V from, final V to) {
        var fromIdx = indices.get(from);
        var toIdx = indices.get(to);

        if (fromIdx.isEmpty() || toIdx.isEmpty()) {
            return false;
        }

        return adjacencyList.get(fromIdx.get()).findIndex(to).isPresent();
    }

    public void addEdge(final V from, final V to) {
        var fromIdx = indices.get(from);
        var toIdx = indices.get(to);

        if (fromIdx.isEmpty()) {
            addVertex(from);
            fromIdx = indices.get(from);
        }

        if (toIdx.isEmpty()) {
            addVertex(to);
        }

        var existingEdge = adjacencyList.get(fromIdx.get()).findIndex(to);

        if (existingEdge.isEmpty()) {
            adjacencyList.get(fromIdx.get()).append(to);
        }
    }
}
