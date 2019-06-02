package hu.nagyf.algorithms.datastructures.heap;

import org.junit.Assert;
import org.junit.Test;

public class MaxHeapTest {
    
    @Test
    public void create() {
        var maxHeap = new MaxHeap<>(10);
        Assert.assertEquals(0, maxHeap.getSize());
    }

    @Test
    public void createAndPutOverCapacity() {
        var maxHeap = new MaxHeap<String>(2);
        maxHeap.insert("test");
        maxHeap.insert("test2");
        maxHeap.insert("test3");

        Assert.assertEquals(3, maxHeap.getSize());
    }

    @Test
    public void getMaximumSingle() {
        var maxHeap = new MaxHeap<Integer>(10);
        maxHeap.insert(10);
        Assert.assertEquals(10, (int)maxHeap.peek().get());
    }

    @Test
    public void getMaximumEmpty() {
        var maxHeap = new MaxHeap<>(10);
        Assert.assertTrue(maxHeap.peek().isEmpty());
    }

    @Test
    public void getMaximum() {
        var maxHeap = new MaxHeap<Integer>(10);
        maxHeap.insert(5);
        maxHeap.insert(2);
        maxHeap.insert(-50);
        maxHeap.insert(1);
        maxHeap.insert(9);
        Assert.assertEquals(5, maxHeap.getSize());
    }

    @Test
    public void peekMaximumEmpty() {
        var heap = new MaxHeap<Integer>(10);
        Assert.assertTrue(heap.peek().isEmpty());
    }

    @Test
    public void peekMaximum() {
        var maxHeap = new MaxHeap<Integer>(10);
        maxHeap.insert(5);
        maxHeap.insert(2);
        maxHeap.insert(-50);
        maxHeap.insert(1);
        maxHeap.insert(9);
        Assert.assertEquals(9, (int)maxHeap.peek().get());
    }

    @Test
    public void popMaximumEmpty() {
        var heap = new MaxHeap<Integer>(10);
        Assert.assertTrue(heap.pop().isEmpty());
    }

    @Test
    public void popMaximumSingleton() {
        var heap = new MaxHeap<Integer>(10);
        heap.insert(10);
        Assert.assertEquals(1, heap.getSize());
        Assert.assertEquals(10, (int)heap.pop().get());
        Assert.assertEquals(0, heap.getSize());
    }

    @Test
    public void popMaximum() {
        var heap = new MaxHeap<Integer>(10);
        heap.insert(5);
        heap.insert(0);
        heap.insert(2);
        heap.insert(-50);
        heap.insert(1);
        Assert.assertEquals(5, (int)heap.pop().get());
        Assert.assertEquals(2, (int)heap.pop().get());
        Assert.assertEquals(1, (int)heap.pop().get());
        Assert.assertEquals(0, (int)heap.pop().get());
        Assert.assertEquals(-50, (int)heap.pop().get());
        Assert.assertEquals(0, heap.getSize());
    }
}
