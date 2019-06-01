package hu.nagyf.algorithms.datastructures.tree;

import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTreeTest {

    @Test
    public void isPresent() {
        var tree = new BinarySearchTree<>(5);
        Assert.assertTrue(tree.isPresent(5));
    }

    @Test
    public void isNotPresent() {
        var tree = new BinarySearchTree<>(6);
        Assert.assertFalse(tree.isPresent(5));
    }

    @Test
    public void insertLeft() {
        var tree = new BinarySearchTree<>(5);
        tree.insert(1);
        Assert.assertTrue(tree.isPresent(1));
    }

    @Test
    public void insertRight() {
        var tree = new BinarySearchTree<>(5);
        tree.insert(10);
        Assert.assertTrue(tree.isPresent(10));
    }

    @Test
    public void insertMultiple() {
        var searchTree = new BinarySearchTree<>("F");
        searchTree.insert("B");
        searchTree.insert("G");
        searchTree.insert("A");
        searchTree.insert("D");
        searchTree.insert("I");
        searchTree.insert("C");
        searchTree.insert("E");
        searchTree.insert("H");

        var tree = searchTree.getTree();
        var result = tree.inOrderIterator().toList().stream().map((n) -> n.value).collect(Collectors.joining(", "));
        Assert.assertEquals("A, B, C, D, E, F, G, H, I", result);
    }

    @Test
    public void comparator() {
        var searchTree = new BinarySearchTree<>("F", (left, right) -> left.compareTo(right) * -1);
        searchTree.insert("B");
        searchTree.insert("G");
        searchTree.insert("A");
        searchTree.insert("D");
        searchTree.insert("I");
        searchTree.insert("C");
        searchTree.insert("E");
        searchTree.insert("H");

        var tree = searchTree.getTree();
        var result = tree.inOrderIterator().toList().stream().map((n) -> n.value).collect(Collectors.joining(", "));
        Assert.assertEquals("I, H, G, F, E, D, C, B, A", result);
    }

    @Test
    public void remove() {
        var searchTree = new BinarySearchTree<>("F");
        searchTree.insert("A");
        searchTree.remove("A");

        var tree = searchTree.getTree();
        var result = tree.inOrderIterator().toList().stream().map((n) -> n.value).collect(Collectors.joining(", "));
        Assert.assertEquals("F", result);
    }

    @Test
    public void removeWithOneChild() {
        var searchTree = new BinarySearchTree<>("F");
        searchTree.insert("B");
        searchTree.insert("A");
        searchTree.remove("B");

        var tree = searchTree.getTree();
        var result = tree.inOrderIterator().toList().stream().map((n) -> n.value).collect(Collectors.joining(", "));
        Assert.assertEquals("A, F", result);
    }

    @Test
    public void removeWithTwoChild() {
        var searchTree = new BinarySearchTree<>("F");
        searchTree.insert("B");
        searchTree.insert("A");
        searchTree.insert("C");

        searchTree.remove("B");

        var tree = searchTree.getTree();
        var result = tree.inOrderIterator().toList().stream().map((n) -> n.value).collect(Collectors.joining(", "));
        Assert.assertEquals("A, C, F", result);
    }

    /*@Test
    public void removeRoot() {
        var searchTree = new BinarySearchTree<>("F");
        searchTree.insert("B");
        searchTree.insert("A");
        searchTree.insert("C");

        searchTree.remove("F");

        var tree = searchTree.getTree();
        var result = tree.inOrderIterator().toList().stream().map((n) -> n.value).collect(Collectors.joining(", "));
        Assert.assertEquals("A, B, C", result);
    }*/
}
