package hu.nagyf.algorithms.datastructures.tree;

import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class BinaryTreeTest {

    @Test
    public void newTree() {
        var tree = new BinaryTree<>("1");
        Assert.assertEquals("1", tree.getRoot().value);
        Assert.assertNull(tree.getRoot().left);
        Assert.assertNull(tree.getRoot().right);
    }

    @Test
    public void insertLeft() {
        var tree = new BinaryTree<>("1");
        tree.getRoot().insertLeft("2");
        Assert.assertEquals("1", tree.getRoot().value);
        Assert.assertNotNull(tree.getRoot().left);
        Assert.assertEquals("2", tree.getRoot().left.value);
        Assert.assertNull(tree.getRoot().right);
    }

    @Test
    public void insertRight() {
        var tree = new BinaryTree<>("1");
        tree.getRoot().insertRight("3");
        Assert.assertEquals("1", tree.getRoot().value);
        Assert.assertNotNull(tree.getRoot().right);
        Assert.assertEquals("3", tree.getRoot().right.value);
        Assert.assertNull(tree.getRoot().left);
    }

    @Test
    public void insertLeftTwice() {
        var tree = new BinaryTree<>("1");
        tree.getRoot().insertLeft("2");
        tree.getRoot().insertLeft("3");

        Assert.assertEquals("1", tree.getRoot().value);
        Assert.assertEquals("3", tree.getRoot().left.value);
        Assert.assertEquals("2", tree.getRoot().left.left.value);
    }

    @Test
    public void insertRightTwice() {
        var tree = new BinaryTree<>("1");
        tree.getRoot().insertRight("2");
        tree.getRoot().insertRight("3");

        Assert.assertEquals("1", tree.getRoot().value);
        Assert.assertEquals("3", tree.getRoot().right.value);
        Assert.assertEquals("2", tree.getRoot().right.right.value);
    }

    @Test
    public void preorderIteratorSingleton() {
        var tree = new BinaryTree<>("A");
        var nodeList = tree.preOrderIterator().toList();
        var result = nodeList.stream().map((node) -> node.value).collect(Collectors.joining(", "));
        Assert.assertEquals("A", result);
    }

    @Test
    public void preorderIterator() {
        var tree = buildTestTree();
        var nodeList = tree.preOrderIterator().toList();
        var result = nodeList.stream().map((node) -> node.value).collect(Collectors.joining(", "));
        Assert.assertEquals("F, B, A, D, C, E, G, I, H", result);
    }

    @Test
    public void inorderIteratorSingleton() {
        var tree = new BinaryTree<>("A");
        var nodeList = tree.inOrderIterator().toList();
        var result = nodeList.stream().map((node) -> node.value).collect(Collectors.joining(", "));
        Assert.assertEquals("A", result);
    }

    @Test
    public void inorderIterator() {
        var tree = buildTestTree();
        var nodeList = tree.inOrderIterator().toList();
        var result = nodeList.stream().map((node) -> node.value).collect(Collectors.joining(", "));

        Assert.assertEquals("A, B, C, D, E, F, G, H, I", result);
    }

    @Test
    public void postorderIteratorSingleton() {
        var tree = new BinaryTree<>("A");
        var nodeList = tree.postOrderIterator().toList();
        var result = nodeList.stream().map((node) -> node.value).collect(Collectors.joining(", "));

        Assert.assertEquals("A", result);
    }

    @Test
    public void postorderIterator() {
        var tree = buildTestTree();
        var nodeList = tree.postOrderIterator().toList();
        var result = nodeList.stream().map((node) -> node.value).collect(Collectors.joining(", "));

        Assert.assertEquals("A, C, E, D, B, H, I, G, F", result);
    }

    @Test
    public void breadthFirstIteratorSingleton() {
        var tree = new BinaryTree<>("A");
        var nodeList = tree.breadthFirstIterator().toList();
        var result = nodeList.stream().map((node) -> node.value).collect(Collectors.joining(", "));

        Assert.assertEquals("A", result);
    }

    @Test
    public void breadthFirstIterator() {
        var tree = buildTestTree();
        var nodeList = tree.breadthFirstIterator().toList();
        var result = nodeList.stream().map((node) -> node.value).collect(Collectors.joining(", "));

        Assert.assertEquals("F, B, G, A, D, I, C, E, H", result);
    }

    /**
     * |        F
     * |      /  \
     * |     B    G
     * |    / \    \
     * |   A  D    I
     * |     /\   /
     * |    C E  H
     */
    private BinaryTree<String> buildTestTree() {
        var tree = new BinaryTree<>("F");
        var root = tree.getRoot();

        root.insertRight("G").insertRight("I").insertLeft("H");
        var rootLeft = root.insertLeft("B");
        rootLeft.insertLeft("A");
        var d = rootLeft.insertRight("D");
        d.insertLeft("C");
        d.insertRight("E");

        return tree;
    }
}
