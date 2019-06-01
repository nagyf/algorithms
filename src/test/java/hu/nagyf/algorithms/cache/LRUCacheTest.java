package hu.nagyf.algorithms.cache;

import org.junit.Assert;
import org.junit.Test;

public class LRUCacheTest {

    @Test(expected = IllegalArgumentException.class)
    public void testZeroCapacity() {
        new LRUCache<>(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeCapacity() {
        new LRUCache<>(-1);
    }

    @Test
    public void testGetEmpty() {
        var cache = new LRUCache<Integer, Integer>(2);
        var value = cache.get(10);
        Assert.assertTrue(value.isEmpty());
    }

    @Test
    public void testGetOtherValue() {
        var cache = new LRUCache<Integer, Integer>(2);
        cache.put(20, 42);
        var value = cache.get(10);
        Assert.assertTrue(value.isEmpty());
    }

    @Test
    public void testGet() {
        var cache = new LRUCache<Integer, Integer>(2);
        cache.put(20, 42);
        var value = cache.get(20);
        Assert.assertEquals(42, (int)value.get());
    }

    @Test
    public void testOverload() {
        var cache = new LRUCache<Integer, Integer>(2);
        cache.put(1, 42);
        cache.put(2, 0);
        cache.put(3, 5);

        Assert.assertTrue(cache.get(1).isEmpty());
        Assert.assertEquals(0, (int)cache.get(2).get());
        Assert.assertEquals(5, (int)cache.get(3).get());
    }

    @Test
    public void testUseKey() {
        var cache = new LRUCache<Integer, Integer>(2);
        cache.put(1, 42);
        cache.put(2, 0);
        cache.get(1);
        cache.put(3, 10);

        Assert.assertTrue(cache.get(2).isEmpty());
        Assert.assertEquals(42, (int)cache.get(1).get());
        Assert.assertEquals(10, (int)cache.get(3).get());
    }
}
