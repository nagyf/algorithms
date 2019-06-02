package hu.nagyf.algorithms.datastructures.graph;

import org.junit.Assert;
import org.junit.Test;

import hu.nagyf.algorithms.graph.WeightedDirectedGraph;

public class WeightedDirectedGraphTest {

    @Test
    public void testWeightedEdge() {
        var graph = new WeightedDirectedGraph<String, Integer>();
        graph.addEdge("A", "B", 10);

        Assert.assertEquals(10, (int)graph.weight("A", "B").get());
        Assert.assertFalse(graph.weight("B", "A").isPresent());
    }

    @Test
    public void testWeightedEdgeNotExisting() {
        var graph = new WeightedDirectedGraph<String, Integer>();
        graph.addEdge("A", "B", 10);

        Assert.assertTrue(graph.weight("B", "A").isEmpty());
        Assert.assertTrue(graph.weight("C", "A").isEmpty());
        Assert.assertTrue(graph.weight("B", "C").isEmpty());
    }
}
