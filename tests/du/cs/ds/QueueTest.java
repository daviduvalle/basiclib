package du.cs.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit tests for {@link Queue}
 * @author daviduvalle
 *
 */
@RunWith(JUnit4.class)
public class QueueTest {
    
    private Queue<String> queue;
    
    private void enqueueElements() {
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
    }
    
    @Before
    public void setup() {
        queue = new Queue<String>();
        enqueueElements();
    }
    
    @Test
    public void testSize() {
        assertEquals(3, queue.size());
    }
    
    @Test
    public void testEnqueue() {
        queue.clear();
        for (int i = 0; i < 100; i++) {
            queue.enqueue(String.valueOf(i));
        }
        
        assertFalse(queue.isEmpty());
        assertEquals(100, queue.size());
    }
    
    @Test
    public void testDequeue() {
        assertEquals("a", queue.dequeue());
        assertEquals("b", queue.dequeue());
        assertEquals("c", queue.dequeue());
    }
    
    @Test
    public void testClear() {
        queue.clear();
        assertTrue(queue.size() == 0);
        queue.clear();
        assertTrue(queue.size() == 0);
    }
    
    @Test
    public void testIsEmpty() {
        queue.clear();
        assertTrue(queue.isEmpty());
    }
}
