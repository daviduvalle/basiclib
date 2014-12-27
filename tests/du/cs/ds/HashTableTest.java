package du.cs.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import du.cs.ds.HashTable.Entry;

@RunWith(JUnit4.class)
public class HashTableTest {
    
    private HashTable<String, String> hashTable;
    
    @Before
    public void setup() {
        hashTable = new HashTable<String, String>();
    }
    
    @Test
    public void testPutGet() {
        hashTable.put("a", "b");
        assertEquals("b", hashTable.get("a"));
    }
    
    @Test
    public void testPutGetMany() {
        addEntries(10000);
        
        for (int i = 0; i < 10000; i++) {
            assertEquals(Integer.toString(i), hashTable.get(Integer.toString(i)));
        }
    }
    
    @Test
    public void testPutOverrideKey() {
        hashTable.put("a", "b");
        assertEquals("b", hashTable.get("a"));
        
        hashTable.put("a", "c");
        assertEquals("c", hashTable.get("a"));
    }
    
    @Test
    public void testRemove() {
        hashTable.put("a", "b");
        assertEquals("b", hashTable.remove("a"));
        assertEquals(0, hashTable.size());
    }
    
    @Test
    public void testRemoveNonExistingElement() {
        assertNull(hashTable.remove("a"));
    }
    
    @Test
    public void testSize() {
        addEntries(1000);
        
        assertTrue(hashTable.size() == 1000);
    }
    
    @Test
    public void testContains() {
        hashTable.put("hashtable", "test");
        assertTrue(hashTable.containsKey("hashtable"));
    }
    
    @Test
    public void testEntrySet() {
        addEntries(30);
        
        Entry<String, String>[] entries = hashTable.entrySet();
        
        assertEquals(30, entries.length);
    }
    
    @Test
    public void testClear() {
        addEntries(100);
        
        hashTable.clear();
        
        assertEquals(0, hashTable.size());
    }
    
    @Test
    public void testKeySet() {
        addEntries(10);
        
        Object[] keys = hashTable.keySet();
        
        assertEquals(10, keys.length);
        assertEquals("0", keys[0].toString());
        assertEquals("9", keys[9].toString());
    }
    
    private void addEntries(int n) {
        for (int i = 0; i < n; i++) {
            hashTable.put(Integer.toString(i), Integer.toString(i));
        }
    }
}
