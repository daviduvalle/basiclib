package du.cs.ds;

/**
 * Another HashTable implementation, uses
 * a linked list to solve collisions
 * @author daviduvalle
 */
public class HashTable<K, V> {
    
    /**
     * Represents and entry in a map
     * @param <K> a key
     * @param <V> a value of that key
     */
    public class Entry<K, V> {
        private K key;
        private V value;
        
        public void setKey(K key) {
            this.key = key;
        }
        
        public void setValue(V value) {
            this.value = value;
        }
        
        public K getKey() {
            return this.key;
        }
        
        public V getValue() {
            return this.value;
        }
    }
    
    public void put(K key, V value) {
        // TODO: implkement insert
    }
    
    public V get(K key) {
        return null;
    }
    
    public int size() {
        return 0;
    }
}
