package du.cs.ds;

/**
 * A HashTable implementation, that uses separate
 * chaining to deal with collisions, support a null
 * key and null values
 * This class is not thread-safe
 * @author david.uvalle@gmail.com
 *
 * @param <K> key type
 * @param <V> value type
 */
public class HashTable<K, V> {

    // Default starting table size
    private static final int DEFAULT_SIZE = 10;
    // Load factor required to resize and rehash
    private static final float LOAD_FACTOR = 0.7f;
    
    /**
     * Represents an element to be stored in a HashTable
     * @author david.uvalle@gmail.com
     *
     * @param <K> key a key 
     * @param <V> value a value 
     */
    public static class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;
        
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey() {
            return this.key;
        }
        
        public V getValue() {
            return this.value;
        }
        
        public void setValue(V value) {
            this.value = value;
        }
        
        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
        
        public Entry<K, V> getNext() {
            return this.next;
        }
    }
    
    private int entriesSize;
    private int bucketsSize;
    @SuppressWarnings("rawtypes")
    private Entry[] table;
    
    /**
     * Default ctr.
     */
    public HashTable() {
        this(DEFAULT_SIZE);
    }
    
    /**
     * Constructs a HashTable with the given initial size
     * @param initialSize size of the table
     */
    public HashTable(int initialSize) {
        table = new Entry[initialSize];
        bucketsSize = initialSize;
    }
    
    /**
     * Inserts an element into the HashTable
     * @param key element key
     * @param value element value
     */
    public void put(K key, V value) {
        
        float load = (float) entriesSize / bucketsSize;
        
        if (load >= LOAD_FACTOR) {
            // Double the array size
            resizeArray(bucketsSize * 2);
        }
        
        Entry<K, V> entry = new Entry<K, V>(key, value);
        int index = getIndex(key);
        
        insertElement(table, index, entry);
        entriesSize++;
    }
    
    /**
     * Removes an element from the HashTable
     * @param key element key
     * @return the element value
     */
    @SuppressWarnings("unchecked")
    public V remove(K key) {
        
        if (!containsKey(key)) {
            return null;
        }
        
        int index = getIndex(key);
        
        Entry<K, V> entry = (Entry<K, V>) table[index];
        Entry<K, V> current = entry;
        
        if (current.getKey().equals(key)) {
            
            if (current.getNext() != null)
            {
                table[index] = current.getNext();
            }
            else
            {
                table[index] = null;
            }
        }
        else {
            
            Entry<K, V> last = null;
            
            while (!current.getKey().equals(key)) {
                last = current;
                current = current.getNext();
            }
            
            if (current.getNext() != null) {
                last.setNext(current.getNext());
                current.setNext(null);
            }
            else {
                last.setNext(null);
            }
        }
        
        entriesSize--;
        
        float load = (float) entriesSize / bucketsSize;

        // Shrink the table if it's not used
        if (load <= 0.3f) {
            resizeArray(table.length / 2);
        }
        
        return current.getValue();
    }
    
    /**
     * Determines if an element exists in a HashTable
     * @param key element key
     * @return true if the element exists, false otherwise
     */
    public boolean containsKey(K key) {
                
        int index = getIndex(key);
        
        if (table[index] != null && 
                ((table[index].getKey() == null && key == null) || 
                (table[index].getKey() != null && table[index].getKey().equals(key)))) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Gets an element given its key
     * @param key element key
     * @return element value
     */
    @SuppressWarnings("unchecked")
    public V get(K key) {
        
        int index = getIndex(key);
        
        if (table[index] == null) {
            return null;
        }
        
        Entry<K, V> current = (Entry<K, V>)table[index];
        
        if ((current.getKey() == null && key == null) || current.getKey().equals(key)) {
            return current.getValue();
        }
        else {
            while (current.getNext() != null) {
                current = current.getNext();
                
                if ((current.getKey() == null && key == null) || current.getKey().equals(key))
                {
                    return current.getValue();
                }
                
            }
        }
        
        return null;
    }
    

    /**
     * Inserts the given entry into the an array with the given index
     * @param table array to store the given entry
     * @param index the position of where to store the entry
     * @param entry the entry to store
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void insertElement(Entry[] table, int index, Entry<K, V> entry) {
        
        if (table[index] != null) {
            
            boolean addToEnd = true;
            
            Entry<K, V> current = (Entry<K, V>)table[index];
            
            if (current.getKey().equals(entry.getKey())) {
                current.setValue(entry.getValue());
            }
            else {
                while (current.getNext() != null) {
                    if (current.getNext().getKey().equals(entry.getKey())) {
                        current.getNext().setValue(entry.getValue());
                        addToEnd = false;
                        break;
                    }
                    
                    current = current.getNext();
                }
                
                if (addToEnd) {
                    current.setNext(entry);
                }
            }
        }
        else {
            table[index] = entry;
        }
    }
    
    /**
     * Resizes the backing array and rehashes its elements
     * @param size the new size of the array
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void resizeArray(int size) {
        
        Entry[] newTable = new Entry[size];
        bucketsSize = size;
        
        for (int i = 0; i < table.length; i++) {
            
            if (table[i] != null) {
                // Simple insert
                Entry<K, V> entry = (Entry<K, V>) table[i];
                
                if (entry.getNext() == null) {
                    int index = getIndex(entry.getKey());
                    insertElement(newTable, index, entry);
                }
                else {
                    Entry<K, V> current = entry;
                    Entry<K, V> next = null;
                    
                    while (current != null) {
                        next = current.getNext();
                        current.setNext(null);
                        int index = getIndex(current.getKey());
                        insertElement(newTable, index, current);
                        current = next;
                    }
                    
                }
                
                table[i] = null;
            }
        }
        
        table = newTable;
    }

    /**
     * Uses the hashcode of the key to return an index
     * @param key element key
     * @return an array index
     */
    private int getIndex(K key) {
        
        int hashCode = key != null ? key.hashCode() : "null".hashCode();

        return hashCode % bucketsSize;
    }
    
    /**
     * Returns the size of the hashtable
     * @return size size of the hashtable
     */
    public int size() {
        return entriesSize;
    }
    
    /**
     * Gets the entries set from the table as an array
     * @return an array containing all of the entries
     */
    @SuppressWarnings("unchecked")
    public Entry<K, V>[] entrySet() {
        Entry<K, V>[] entries = new Entry[entriesSize];
        int count = 0;
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Entry<K, V> entry = (Entry<K, V>) table[i];
                entries[count++] = entry;
                
                while (entry.getNext() != null) {
                    entries[count++] = entry.getNext();
                    entry = entry.getNext();
                }
            }
        }
        
        return entries;
    }
    
    /**
     * Gets an array of all the keys in the hashtable
     * @return an array of keys in the hashtable
     */
    public Object[] keySet() {
        Object[] keys = new Object[entriesSize];
        
        Entry<K, V>[] entries = entrySet();
        int count = 0;
        
        for (Entry<K, V> entry : entries) {
            keys[count++] = entry.getKey();
        }
        
        return keys;
    }
    
    /**
     * Removes all the elements from the hashTable
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        
        Object[] keys = keySet();
        
        for (Object key : keys) {
            this.remove((K)key);
        }
    }
}
