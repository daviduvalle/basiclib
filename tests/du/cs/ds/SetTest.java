package du.cs.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit tests for {@link Set}
 * @author david.uvalle@gmail.com
 * 
 */
@RunWith(JUnit4.class)
public class SetTest {
    
    private Set<String> set;
    
    @Before
    public void setup() {
        set = new Set<String>();
    }
    
    @Test
    public void testAdd() {
        set.add("a");
        set.add("b");
        set.add("b");
        
        assertEquals(2, set.size());
    }
    
    @Test
    public void testRemove() {
        set.add("a");
        set.remove("a");
        
        assertEquals(0, set.size());
    }
    
    @Test
    public void testContains() {
        set.add("car");
        
        assertTrue(set.contains("car"));
    }
    
    @Test
    public void testClear() {
        set.add("a");
        set.add("b");
        
        set.clear();
        
        assertEquals(0, set.size());
    }
    
    @Test
    public void testUnion() {
        set.add("a");
        set.add("b");
        
        Set<String> otherSet = new Set<String>();
        otherSet.add("c");
        otherSet.add("d");
        otherSet.add("a");
        
        Set<String> result = set.union(otherSet);
        
        assertEquals(4, result.size());
        assertTrue(result.contains("c"));
        assertTrue(result.contains("d"));
    }
    
    @Test
    public void testIntersect() {
        set.add("a");
        set.add("b");
        
        Set<String> otherSet = new Set<String>();
        otherSet.add("a");
        otherSet.add("c");
        
        Set<String> resultSet = set.intersect(otherSet);
        
        assertEquals(1, resultSet.size());
        assertTrue(resultSet.contains("a"));
    }
    
    @Test
    public void testDifference() {
        set.add("a");
        set.add("b");
        set.add("c");
        
        Set<String> otherSet = new Set<String>();
        otherSet.add("c");
        
        Set<String> resultSet = set.difference(otherSet);
        
        assertEquals(2, resultSet.size());
        assertTrue(!set.contains("c"));
    }
    
    @Test
    public void testSameSet() {
        set.add("a");
        set.add("b");
        
        Set<String> otherSet = new Set<String>();
        otherSet.add("a");
        otherSet.add("b");
        
        assertTrue(set.same(otherSet));
    }
}
