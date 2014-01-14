package du.cs.ds;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit tests for {@link LinkedList}
 * @author daviduvalle
 *
 */
@RunWith(JUnit4.class)
public class LinkedListTest {

    private LinkedList<String> linkedList = null;
    
    private void insertElements() {
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
    }
    
    @Before
    public void setup() {
        linkedList = new LinkedList<String>();
        insertElements();
    }
    
    @Test
    public void testDefaultCtr() {
        assertNotNull(linkedList);
    }
    
    @Test
    public void testAddingElements() {
        assertTrue(linkedList.size() == 3);
        assertEquals("a", linkedList.get(0));
        assertEquals("b", linkedList.get(1));
        assertEquals("c", linkedList.get(2));
    }
    
    @Test
    public void testDeletingElementsFromHead() {
        linkedList.delete(0);
        linkedList.delete(0);
        assertTrue(linkedList.size() == 1);
        assertEquals("c", linkedList.get(0));
    }
    
    @Test
    public void testDeletingElementsFromTail() {
        linkedList.delete(linkedList.size()-1);
        assertTrue(linkedList.size() == 2);
        linkedList.delete(linkedList.size()-1);
        assertTrue(linkedList.size() == 1);
    }
    
    @Test
    public void testDeletingFromMiddle() {
        assertTrue(linkedList.size() == 3);
        linkedList.delete(1);
        assertEquals("a", linkedList.get(0));
        assertEquals("c", linkedList.get(1));
    }
    
    @Test
    public void testDeleteFromLast() {
        linkedList.deleteLast();
        linkedList.deleteLast();
        linkedList.deleteLast();
        assertTrue(linkedList.size() == 0);
        linkedList.deleteLast();
        assertTrue(linkedList.size() == 0);
    }
    
    @Test
    public void testClearElements() {
        linkedList.clear();
        assertTrue(linkedList.size() == 0);
    }
    
    @Test
    public void testReverseList() {
        linkedList.reverse();
        assertEquals("c", linkedList.get(0));
        assertEquals("b", linkedList.get(1));
        assertEquals("a", linkedList.get(2));
    }
    
    @Test
    public void testListIterator() {
        assertTrue(linkedList.hasNext());
        assertEquals("a", linkedList.getNext());
        assertEquals("b", linkedList.getNext());
        assertEquals("c", linkedList.getNext());
        assertFalse(linkedList.hasNext());
    }
    
    @Test
    public void testToString() {
        assertEquals("a -> b -> c", linkedList.toString());
    }
}
