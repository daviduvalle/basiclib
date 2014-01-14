package du.cs.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DynamicArrayTest {

    private DynamicArray<String> array;
    
    @Before
    public void setup() {
        this.array = new DynamicArray<String>();
        insertElements();
    }
    
    public void insertElements() {
        for (int i = 0; i < 100; i++) {
            array.add(String.valueOf(i));
        }
    }
    
    @Test
    public void testSize() {
        assertEquals(100, array.size());
    }
    
    @Test
    public void testDeleteMiddle() {
        array.remove(50);
        assertEquals("49", array.get(49));
        assertEquals("51", array.get(50));
    }
    
    @Test
    public void testDeleteStart() {
        for (int i = 0; i < 10; i++) {
            array.remove(0);
        }
        
        assertEquals("10", array.get(0));
        assertTrue(array.size() == 90);
    }
    
    @Test
    public void testDeleteEnd() {
        for (int i = 0; i < 20; i++) {
            array.remove(array.size());
        }
        
        assertEquals("80", array.get(array.size()));
        assertTrue(array.size() == 80);
    }
    
    @Test
    public void testClear() {
        array.clear();
        System.out.println(array.size());
        assertTrue(array.size() == 0);
    }
}
