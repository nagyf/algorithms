package hu.nagyf.algorithms.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class StackTest {

    @Test
    public void isEmpty() {
        var stack = new Stack<Integer>();
        Assert.assertTrue(stack.isEmpty());
    }

    @Test
    public void notEmpty() {
        var stack = new Stack<Integer>();
        stack.push(10);
        Assert.assertFalse(stack.isEmpty());
    }

    @Test
    public void push() {
        var stack = new Stack<Integer>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        Assert.assertEquals(30, (int)stack.pop().get());
        Assert.assertEquals(20, (int)stack.pop().get());
        Assert.assertEquals(10, (int)stack.pop().get());
    }

    @Test
    public void peek() {
        var stack = new Stack<Integer>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        Assert.assertEquals(30, (int)stack.peek().get());
        Assert.assertEquals(30, (int)stack.peek().get());
    }

    @Test
    public void popEmpty() {
        var stack = new Stack<Integer>();
        Assert.assertTrue(stack.pop().isEmpty());
    }

    @Test
    public void peekEmpty() {
        var stack = new Stack<Integer>();
        Assert.assertTrue(stack.peek().isEmpty());
    }
}
