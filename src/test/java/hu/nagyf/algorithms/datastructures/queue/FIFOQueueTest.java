package hu.nagyf.algorithms.datastructures.queue;

import org.junit.Assert;
import org.junit.Test;

public class FIFOQueueTest {

    @Test
    public void testPeekEmpty() {
        var queue = new FIFOQueue<Integer>();
        var next = queue.peek();
        Assert.assertTrue(next.isEmpty());
    }

    @Test
    public void testPollEmpty() {
        var queue = new FIFOQueue<Integer>();
        var next = queue.poll();
        Assert.assertTrue(next.isEmpty());
    }

    @Test
    public void testPeek() {
        var queue = new FIFOQueue<Integer>();
        queue.offer(10);
        Assert.assertEquals(10, (int)queue.peek().get());
        Assert.assertEquals(10, (int)queue.peek().get());
    }

    @Test
    public void testPoll() {
        var queue = new FIFOQueue<Integer>();
        queue.offer(10);
        Assert.assertEquals(10, (int)queue.poll().get());
        Assert.assertTrue(queue.poll().isEmpty());
    }

    @Test
    public void testOrder() {
        var queue = new FIFOQueue<Integer>();
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);
        Assert.assertEquals(10, (int)queue.poll().get());
        Assert.assertEquals(20, (int)queue.poll().get());
        Assert.assertEquals(30, (int)queue.poll().get());
        Assert.assertTrue(queue.poll().isEmpty());
    }
}
