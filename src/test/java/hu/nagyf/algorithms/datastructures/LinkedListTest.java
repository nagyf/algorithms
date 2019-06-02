package hu.nagyf.algorithms.datastructures;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest {

    @Test
    public void testIsEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testIsNotEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void testFirst() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        Assert.assertTrue(list.first().isPresent());
        Assert.assertEquals((int)list.first().get(), 10);
    }

    @Test
    public void testLast() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        Assert.assertTrue(list.first().isPresent());
        Assert.assertEquals((int)list.last().get(), 30);
    }

    @Test
    public void testInsert() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        list.insert(1);
        Assert.assertTrue(list.first().isPresent());
        Assert.assertEquals((int)list.first().get(), 1);
    }

    @Test
    public void testInsert2() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(1);
        Assert.assertTrue(list.first().isPresent());
        Assert.assertEquals((int)list.first().get(), 1);
    }

    @Test
    public void testRemoveFirst() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        var first = list.removeFirst();
        Assert.assertEquals((int)first.get(), 10);
        Assert.assertEquals((int)list.first().get(), 20);
    }

    @Test
    public void testRemoveFirstEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        var first = list.removeFirst();
        Assert.assertTrue(first.isEmpty());
    }

    @Test
    public void testRemoveLast() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        var last = list.removeLast();
        Assert.assertEquals((int)last.get(), 30);
        Assert.assertEquals((int)list.last().get(), 20);
    }

    @Test
    public void testRemoveLastEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        var last = list.removeLast();
        Assert.assertTrue(last.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testAppendListNull() {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> other = null;
        list.append(other);
    }

    @Test
    public void testAppendListEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> other = new LinkedList<>();
        list.append(other);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testAppendList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(1);
        list.append(2);
        LinkedList<Integer> other = new LinkedList<>();
        other.append(3);
        other.append(4);

        list.append(other);
        Assert.assertEquals(4, list.size());
        var result = list.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(", "));
        Assert.assertEquals("1, 2, 3, 4", result);
    }

    @Test
    public void sizeZero() {
        LinkedList<Integer> list = new LinkedList<>();
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void sizeZero2() {
        LinkedList<Integer> list = new LinkedList<>();
        list.removeFirst();
        list.removeLast();
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void sizeAfterAppend() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void sizeAfterInsert() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(10);
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void sizeAfterRemove() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(10);
        list.removeLast();
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void findIndexEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        var index = list.findIndex(20);
        Assert.assertTrue(index.isEmpty());
    }

    @Test
    public void findIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        var index = list.findIndex(20);
        Assert.assertFalse(index.isEmpty());
        Assert.assertEquals(1, (int)index.get());
    }

    @Test
    public void findIndexLast() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        var index = list.findIndex(30);
        Assert.assertFalse(index.isEmpty());
        Assert.assertEquals(2, (int)index.get());
    }

    @Test
    public void findIndexNotContains() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        var index = list.findIndex(40);
        Assert.assertTrue(index.isEmpty());
    }

    @Test
    public void atEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        var value = list.at(0);
        Assert.assertTrue(value.isEmpty());
    }

    @Test
    public void atFirst() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        var value = list.at(0);
        Assert.assertFalse(value.isEmpty());
        Assert.assertEquals(10, (int)value.get());
    }

    @Test
    public void at() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        var value = list.at(1);
        Assert.assertFalse(value.isEmpty());
        Assert.assertEquals(20, (int)value.get());
    }

    @Test
    public void sizeEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        var value = list.size();
        Assert.assertEquals(0, value);
    }

    @Test
    public void size() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        var value = list.size();
        Assert.assertEquals(3, value);
    }

    @Test
    public void removeAtEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        var value = list.removeAt(0);
        Assert.assertTrue(value.isEmpty());
    }

    @Test
    public void removeAt0() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        var value = list.removeAt(0);
        Assert.assertFalse(value.isEmpty());
        Assert.assertEquals(10, (int)value.get());
        Assert.assertEquals(20, (int)list.first().get());
    }

    @Test
    public void removeAt1() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        var value = list.removeAt(1);
        Assert.assertFalse(value.isEmpty());
        Assert.assertEquals(20, (int)value.get());
        Assert.assertEquals(10, (int)list.first().get());
    }

    @Test
    public void testIteratorEmpty() {
        var list = new LinkedList<String>();
        var result = new ArrayList<String>();
        list.iterator().forEachRemaining(result::add);
        Assert.assertEquals("", String.join(", ", result));
    }

    @Test
    public void testIteratorSingleton() {
        var list = new LinkedList<String>();
        list.append("A");
        var result = new ArrayList<String>();
        list.iterator().forEachRemaining(result::add);
        Assert.assertEquals("A", String.join(", ", result));
    }

    @Test
    public void testIterator() {
        var list = new LinkedList<String>();
        list.append("A");
        list.append("B");
        list.append("C");
        list.append("D");
        var result = new ArrayList<String>();
        list.iterator().forEachRemaining(result::add);
        Assert.assertEquals("A, B, C, D", String.join(", ", result));
    }
}
