package du.cs.ds;

/**
 * A set abstraction supporting its operations with other
 * sets 
 * @author david.uvalle@gmail.com
 */
public class Set<T> {
    
    private HashTable<T, Object> table;

    /**
     * Creates a new set
     */
    public Set() {
        this.table = new HashTable<T, Object>();
    }
    
    /**
     * Creates a new set with initial size specified
     * @param initialSize the initial size of the set
     */
    public Set(int initialSize) {
        this.table = new HashTable<T, Object>(initialSize);
    }
    
    /**
     * Adds an element to the set
     * @param element element to add
     * @return true if the element was added, false otherwise
     */
    public boolean add(T element) {
        
        if (table.containsKey(element)) {
            return false;
        }
        
        table.put(element, null);
        
        return true;
    }
    
    /**
     * Removes an element from the set
     * @param element element to be removed
     * @return true if the element was removed, false otherwise
     */
    public boolean remove(T element) {
        
        if (!table.containsKey(element)) {
            return false;
        }
        
        table.remove(element);
        
        return true;
    }
    
    /**
     * Determines the size of the set
     * @return the size of the set
     */
    public int size() {
        return table.size();
    }
    
    /**
     * Performs an union with the given set
     * @param set set to perform an union with
     * @return the result of the union
     */
    @SuppressWarnings("unchecked")
    public Set<T> union(Set<T> set) {
        
        Set<T> resultSet = addElements();
        
        for (Object element : set.getElements()) {
            resultSet.add((T)element);
        }
        
        return resultSet;
    }
    
    /**
     * Performs an intersection with the given set
     * @param set set to intersect with
     * @return a result of the intersection of both sets
     */
    @SuppressWarnings("unchecked")
    public Set<T> intersect(Set<T> set) {
        
        Set<T> resultSet = new Set<T>();
        
        for (Object element : set.getElements()) {
            if (this.contains((T)element)) {
                resultSet.add((T)element);
            }
        }
        
        return resultSet;
    }
    
    /**
     * Removes elements of the input set
     * @param set elements to be removed
     * @return a new set without removed elements
     */
    @SuppressWarnings("unchecked")
    public Set<T> difference(Set<T> set) {
        
        Set<T> resultSet = addElements();
        
        for (Object element : set.getElements()) {
            resultSet.remove((T)element);
        }
        
        return resultSet;
    }
    
    /**
     * Gets all the elements in the set
     * @return array of elements in the set
     */
    public Object[] getElements() {
        return table.keySet();
    }
    
    /**
     * Removes all the elements from the set
     */
    public void clear() {
        table.clear();
    }
    
    /**
     * Determines if the element is contained in the set
     * @param element element to be tested
     * @return true if the element is in the set, false otherwise
     */
    public boolean contains(T element) {
        return table.containsKey(element);
    }
    
    /**
     * Determines if all the elements in the input
     * set are in the original set
     * @param set input set
     * @return true if is the same set, false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean same(Set<T> set) {
        if (this.size() != set.size()) {
            return false;
        }
        
        for (Object element : set.getElements()) {
            if (!this.contains((T)element)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Adds all the elements into a new set
     * @return a new set containing all elements from the original one
     */
    @SuppressWarnings("unchecked")
    private Set<T> addElements() {
        
        Set<T> resultSet = new Set<T>(this.size());
        
        for (Object element : this.getElements()) {
            resultSet.add((T)element);
        }
        
        return resultSet;
    }
}
