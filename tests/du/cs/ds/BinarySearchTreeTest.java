package du.cs.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit tests for {@link BinarySearchTree}
 */
@RunWith(JUnit4.class)
public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> bsearchTree;

    @Before
    public void setup() {
        bsearchTree = new BinarySearchTree<>();
        bsearchTree.insert(10);
        bsearchTree.insert(5);
        bsearchTree.insert(15);
        bsearchTree.insert(7);
        bsearchTree.insert(3);
        bsearchTree.insert(12);
        bsearchTree.insert(20);
    }

    @Test
    public void testGetRoot() {
        assertEquals(10, bsearchTree.getRoot().getData().intValue());
    }

    @Test
    public void testSearch() {
        BinarySearchTree.Node<Integer> result = bsearchTree.search(12);
        assertTrue(result.getData() == 12);
        assertNull(bsearchTree.search(100));
    }

    @Test
    public void testMin() {
        BinarySearchTree.Node<Integer> min = bsearchTree.treeMininum(bsearchTree.getRoot());
        assertEquals(3, min.getData().intValue());
    }

    @Test
    public void testMax() {
        BinarySearchTree.Node<Integer> max = bsearchTree.treeMaximun
                (bsearchTree.getRoot());
        assertEquals(20, max.getData().intValue());
    }

    @Test
    public void testSuccessor() {
        BinarySearchTree.Node<Integer> successor = bsearchTree.successor
                (bsearchTree
                .search(new Integer(5)));
        assertEquals(7, successor.getData().intValue());
    }

    @Test
    public void testPredecessor() {
        BinarySearchTree.Node<Integer> predecessor = bsearchTree.predecessor
                (bsearchTree.search(new Integer(15)));
        assertEquals(12, predecessor.getData().intValue());
    }

    @Test
    public void testDeleteLeaf() {
        BinarySearchTree.Node<Integer> toDelete = bsearchTree.search(new Integer
                (3));
        bsearchTree.delete(toDelete);
        DynamicArray<Integer> array = bsearchTree.inorder(bsearchTree.getRoot
                ());
        assertEquals(5, array.get(0).intValue());
    }

    @Test
    public void testDeleteNodeWithChild() {
        BinarySearchTree.Node<Integer> toDelete = bsearchTree.search(new Integer
                (15));
        bsearchTree.delete(toDelete);
        assertEquals(12, bsearchTree.successor(bsearchTree.getRoot())
                .getData().intValue());
    }

    @Test
    public void testInorder() {
        DynamicArray<Integer> array = bsearchTree.inorder(bsearchTree.getRoot
                ());
        assertTrue(array.size() == 7);
        assertTrue(array.get(0).intValue() == new Integer(3));
        assertTrue(array.get(6).intValue() == new Integer(20));
    }

}
