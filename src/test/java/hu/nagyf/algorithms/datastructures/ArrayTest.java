package hu.nagyf.algorithms.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class ArrayTest {

    @Test
    public void createDefaultCapacity() {
        var array = new Array<Integer>();
        Assert.assertEquals(10, array.getSize());
    }

    @Test
    public void createWithCapacity() {
        var array = new Array<Integer>(100);
        Assert.assertEquals(100, array.getSize());
    }

    @Test
    public void getUnset() {
        var array = new Array<Integer>(10);
        Assert.assertNull(array.get(9));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getOutOfBounds() {
        var array = new Array<Integer>(10);
        array.get(10);
    }

    @Test
    public void set() {
        var array = new Array<String>(10);
        array.set(0, "test");

        Assert.assertEquals("test", array.get(0));
    }

    @Test
    public void setWithGrow() {
        var array = new Array<String>(10);
        array.set(0, "fitting");
        array.set(11, "not-fitting");

        Assert.assertEquals("fitting", array.get(0));
        Assert.assertEquals("not-fitting", array.get(11));
    }

    @Test
    public void iterateEmpty() {
        var array = new Array<String>(10);
        array.stream().forEach(Assert::assertNull);
    }

    @Test
    public void iterate() {
        var array = new Array<String>(1);
        array.set(0, "test");
        array.stream().forEach((item) -> Assert.assertEquals("test", item));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void swapIllegal() {
        var array = new Array<String>(10);
        array.swap(0, 11);
    }

    @Test
    public void swapWithNull() {
        var array = new Array<String>(10);
        array.set(0, "test");
        array.swap(0, 1);

        Assert.assertEquals("test", array.get(1));
        Assert.assertNull(array.get(0));
    }

    @Test
    public void swap() {
        var array = new Array<String>(10);
        array.set(0, "test");
        array.set(1, "other");
        array.swap(0, 1);

        Assert.assertEquals("test", array.get(1));
        Assert.assertEquals("other", array.get(0));
    }
}
