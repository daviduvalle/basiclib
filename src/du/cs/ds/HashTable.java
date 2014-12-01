package du.cs.ds;

/**
 * Another HashTable implementation
 * This class is not thread-safe
 * @author david.uvalle@gmail.com
 *
 * @param <K> key type
 * @param <V> value type
 */
public class HashTable<K, V> {
        
    private static final int DEFAULT_SIZE = 10;
    private static final float LOAD_FACTOR = 0.7f;
    
    /**
     * Represents an element to be stored in a HashTable
     * @author david.uvalle@gmail.com
     *
     * @param <K> key
     * @param <V> value
     */
    private static class Entry<K, V> {
        private final K key;
        private final V value;
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
        
        int index = getIndex(key);
        
        entriesSize++;
        
        float load = (float) entriesSize / bucketsSize;
        
        if (load >= LOAD_FACTOR) {
            // Double the array size
            resizeArray(table.length * 2);
            // Rehash for this element
            index = getIndex(key);
        }
        
        Entry<K, V> entry = new Entry<K, V>(key, value);
        
        insertElement(table, index, entry);
    }
    
    /**
     * Removes an element from the HashTable
     * @param key element key
     * @return the element value
     */
    public V remove(K key) {
        /**
         * This method will do two things
         * 1 - If there are no collisions, just remove the first element making it null,
         * In case of collisions, find the element in the list and remove it, the first position
         * should be an special case
         * 2 - If after removing an element we find that the load factor is very low
         * (less than 0.3) then we should be able to decrease the array size by 50%
         */
        
        return null;
    }
    
    /**
     * Determines if an element exists in a HashTable
     * @param key element key
     * @return true if the element exists, false otherwise
     */
    public boolean containsKey(K key) {
        
        int index = getIndex(key);
        
        return table[index] != null ? true : false;
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
        
        if (current.getKey().equals(key)) {
            return current.getValue();
        }
        else {
            while (current.getNext() != null) {
                current = current.getNext();
                
                if (current.getKey().equals(key))
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
            
            Entry<K, V> current = (Entry<K, V>)table[index];
            
            // Separate chaining to deal with collisions
            while (current.getNext() != null) {
                current = current.getNext();
            }
            
            current.setNext(entry);
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
                Entry<K, V> entry = (Entry<K, V>) table[i];
                int index = getIndex(entry.getKey());
                insertElement(newTable, index, entry);
            }
            
            // free elements from the old table;
            table[i] = null;
        }
        
        table = newTable;
    }

    /**
     * Uses the hashcode of the key to return an index
     * @param key element key
     * @return an array index
     */
    private int getIndex(K key) {
        return key.hashCode() % bucketsSize;
    }
}
