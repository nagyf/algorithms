package hu.nagyf.algorithms.datastructures.heap;

import org.junit.Assert;
import org.junit.Test;

public class MinHeapTest {

    @Test
    public void create() {
        var minHeap = new MinHeap<>(10);
        Assert.assertEquals(0, minHeap.getSize());
    }

    @Test
    public void getMinimumSingle() {
        var minHeap = new MinHeap<Integer>(10);
        minHeap.insert(10);
        Assert.assertEquals(10, (int)minHeap.peek().get());
    }

    @Test
    public void getMinimumEmpty() {
        var minHeap = new MinHeap<>(10);
        Assert.assertTrue(minHeap.peek().isEmpty());
    }

    @Test
    public void getMinimum() {
        var minHeap = new MinHeap<Integer>(10);
        minHeap.insert(5);
        minHeap.insert(2);
        minHeap.insert(-50);
        minHeap.insert(1);
        minHeap.insert(9);
        Assert.assertEquals(5, minHeap.getSize());
    }

    @Test
    public void peekMinimumEmpty() {
        var heap = new MinHeap<Integer>(10);
        Assert.assertTrue(heap.peek().isEmpty());
    }

    @Test
    public void peekMinimum() {
        var minHeap = new MinHeap<Integer>(10);
        minHeap.insert(5);
        minHeap.insert(2);
        minHeap.insert(-50);
        minHeap.insert(1);
        minHeap.insert(9);
        Assert.assertEquals(-50, (int)minHeap.peek().get());
    }

    @Test
    public void popMinimumEmpty() {
        var heap = new MinHeap<Integer>(10);
        Assert.assertTrue(heap.pop().isEmpty());
    }

    @Test
    public void popMinimumSingleton() {
        var heap = new MinHeap<Integer>(10);
        heap.insert(10);
        Assert.assertEquals(1, heap.getSize());
        Assert.assertEquals(10, (int)heap.pop().get());
        Assert.assertEquals(0, heap.getSize());
    }

    @Test
    public void popMinimum() {
        var heap = new MinHeap<Integer>(10);
        heap.insert(5);
        heap.insert(0);
        heap.insert(2);
        heap.insert(-50);
        heap.insert(1);
        Assert.assertEquals(-50, (int)heap.pop().get());
        Assert.assertEquals(0, (int)heap.pop().get());
        Assert.assertEquals(1, (int)heap.pop().get());
        Assert.assertEquals(2, (int)heap.pop().get());
        Assert.assertEquals(5, (int)heap.pop().get());
        Assert.assertEquals(0, heap.getSize());
    }
}
