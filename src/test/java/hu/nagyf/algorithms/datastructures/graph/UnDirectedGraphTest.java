package hu.nagyf.algorithms.datastructures.graph;

import org.junit.Assert;
import org.junit.Test;

import hu.nagyf.algorithms.graph.UnDirectedGraph;

public class UnDirectedGraphTest {
    @Test
    public void addVertex() {
        var graph = new UnDirectedGraph<String>();
        graph.addVertex("A");
        Assert.assertTrue(graph.containsVertex("A"));
    }

    @Test
    public void addEdgeFrom() {
        var graph = new UnDirectedGraph<String>();
        graph.addVertex("B");
        graph.addEdge("A", "B");
        Assert.assertTrue(graph.containsVertex("A"));
        Assert.assertTrue(graph.isConnected("A", "B"));
    }

    @Test
    public void addEdgeTo() {
        var graph = new UnDirectedGraph<String>();
        graph.addVertex("A");
        graph.addEdge("A", "B");
        Assert.assertTrue(graph.containsVertex("B"));
        Assert.assertTrue(graph.isConnected("A", "B"));
    }

    @Test
    public void addEdgeBoth() {
        var graph = new UnDirectedGraph<String>();
        graph.addEdge("A", "B");
        Assert.assertTrue(graph.containsVertex("A"));
        Assert.assertTrue(graph.containsVertex("B"));
        Assert.assertTrue(graph.isConnected("A", "B"));
    }

    @Test
    public void isConnectedNonExisting() {
        var graph = new UnDirectedGraph<String>();
        graph.addVertex("A");
        Assert.assertFalse(graph.isConnected("B", "C"));
        Assert.assertFalse(graph.isConnected("A", "C"));
    }

    @Test
    public void isConnectedTrue() {
        var graph = new UnDirectedGraph<String>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        Assert.assertTrue(graph.isConnected("A", "B"));
        Assert.assertTrue(graph.isConnected("B", "A"));
    }

    @Test
    public void isConnectedFalse() {
        var graph = new UnDirectedGraph<String>();
        graph.addVertex("A");
        graph.addVertex("B");
        Assert.assertFalse(graph.isConnected("A", "B"));
        Assert.assertFalse(graph.isConnected("B", "A"));
    }

    @Test
    public void removeVertexNotExisting() {
        var graph = new UnDirectedGraph<String>();
        Assert.assertFalse(graph.removeVertex("A"));
    }

    @Test
    public void removeVertex() {
        var graph = new UnDirectedGraph<String>();
        graph.addVertex("A");
        Assert.assertTrue(graph.removeVertex("A"));
        Assert.assertFalse(graph.containsVertex("A"));
    }

    @Test
    public void removeVertexConnections() {
        var graph = new UnDirectedGraph<String>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("C", "A");

        Assert.assertTrue(graph.removeVertex("A"));
        Assert.assertFalse(graph.containsVertex("A"));
        Assert.assertFalse(graph.isConnected("A", "B"));
        Assert.assertFalse(graph.isConnected("C", "A"));
    }
}
