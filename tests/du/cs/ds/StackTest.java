package du.cs.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit tests for {@link Stack}
 * @author daviduvalle
 */
@RunWith(JUnit4.class)
public class StackTest {
    
    private Stack<Integer> stack = null;
    
    private void pushElements() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }
    
    private void clearStack() {
        stack.clear();
    }
    
    @Before
    public void setup() {
        stack = new Stack<Integer>();
        pushElements();
    }
    
    @Test
    public void testPush() {
        clearStack();
        for (int i = 0; i < 1000; i++) {
            stack.push(new Integer(i));
        }
        assertTrue(stack.size() == 1000);
    }
    
    @Test
    public void testPop() {
        for (int i = stack.size(); i >= 0; i--) {
            stack.pop();
        }
        
        assertTrue(stack.size() == 0);
        assertTrue(stack.isEmpty());
    }
    
    @Test 
    public void testClear() {
        stack.clear();
        assertEquals(0,  stack.size());
        assertTrue(stack.isEmpty());
    }
    
    @Test
    public void testFirstInLastOut() {
        assertEquals(new Integer(3), stack.pop());
        assertEquals(new Integer(2), stack.pop());
        assertEquals(new Integer(1), stack.pop());
    }
    
    @Test
    public void testPopAllElements() {
        clearStack();
        assertNull(stack.pop());
    }
    
    @Test
    public void testIsEmpty() {
        clearStack();
        assertTrue(stack.isEmpty());
    }
    
    @Test
    public void testIsNotEmpty() {
        assertFalse(stack.isEmpty());
    }
    
    @Test
    public void testPeek() {
        assertEquals(new Integer(3), stack.peek());
    }
}
