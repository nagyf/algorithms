package hu.nagyf.algorithms.datastructures.map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HashTableTest {

    @Test
    public void empty() {
        var table = new HashTable<String, Integer>();
        Assert.assertTrue(table.get("test").isEmpty());
    }

    @Test
    public void putGet() {
        var table = new HashTable<String, Integer>();
        table.put("test", 123);
        Assert.assertEquals(123, (int)table.get("test").get());
    }

    @Test
    public void putGetCollision() {
        var table = new HashTable<String, Integer>();
        table = spy(table);
        doReturn(42).when(table).hash(anyString());

        table.put("test", 123);
        table.put("otherTestString", 2);
        Assert.assertEquals(123, (int)table.get("test").get());
        Assert.assertEquals(2, (int)table.get("otherTestString").get());
    }

    @Test(expected = IllegalArgumentException.class)
    public void putNullKey() {
        var table = new HashTable<String, Integer>();
        table.put(null, 123);
    }

    @Test
    public void testLotsOfItems() {
        var table = new HashTable<String, Integer>(10);
        for (int i = 0; i < 1000; ++i) {
            table.put(Integer.toString(i), i);
        }

        for (int i = 0; i < 1000; ++i) {
            Assert.assertEquals(i, (int)table.get(Integer.toString(i)).get());
        }
    }

    @Test
    public void testItemEquals() {
        var first = new HashTable.Item<>("test", 42);
        var second = new HashTable.Item<>("test", 32);
        Assert.assertEquals(first, second);
    }

    @Test
    public void testItemNotEquals() {
        var first = new HashTable.Item<>("test", 42);
        var second = new HashTable.Item<>("test123", 42);
        Assert.assertNotEquals(first, second);
    }

    @Test
    public void testRemoveNonExistentItem() {
        var table = new HashTable<>();
        table.remove("test");
    }

    @Test
    public void testRemove() {
        var table = new HashTable<>();
        table.put("test", 10);
        table.remove("test");
        Assert.assertTrue(table.get("test").isEmpty());
    }
}
