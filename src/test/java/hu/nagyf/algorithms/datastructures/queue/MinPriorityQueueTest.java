package hu.nagyf.algorithms.datastructures.queue;

import org.junit.Assert;
import org.junit.Test;

public class MinPriorityQueueTest {

    @Test
    public void testEmpty() {
        var queue = new MinPriorityQueue(10);
        Assert.assertTrue(queue.peek().isEmpty());
        Assert.assertTrue(queue.poll().isEmpty());
    }

    @Test
    public void testInsertWithPriority() {
        var queue = new MinPriorityQueue<Integer>(10);
        queue.offer(10, 3);
        queue.offer(20, 0);
        queue.offer(-50, 5);
        Assert.assertEquals(20, (int)queue.peek().get());
        Assert.assertEquals(20, (int)queue.poll().get());
        Assert.assertEquals(10, (int)queue.poll().get());
        Assert.assertEquals(-50, (int)queue.poll().get());
        Assert.assertTrue(queue.poll().isEmpty());
    }
}
