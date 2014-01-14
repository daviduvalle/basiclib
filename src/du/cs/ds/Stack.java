package du.cs.ds;

/**
 * Singly-linked list backed Stack
 * @author daviduvalle
 *
 */
public class Stack<T> {
    
    private DynamicArray<T> array;
    
    /**
     * Default ctr
     */
    public Stack() {
        this.array = new DynamicArray<T>();
    }
    
    /**
     * Pushes a new element into the stack
     * @param element an element
     */
    public void push(T element) {
        array.add(element);
    }
    
    /**
     * Pops an element from the stack
     * @return an element
     */
    public T pop() {
        
        if (array.size() == 0)
        {
            return null;
        }
        
        T element = array.get(array.size()-1);
        array.remove(array.size()-1);
        return element;
    }
    
    /**
     * Shows but does not pops the top element in the stack
     * @return an element
     */
    public T peek() {
        
        if (array.size() > 0) {
            return array.get(array.size()-1);
        }
        
        return null;
    }
    
    /**
     * Returns size of the stack
     * @return size of the stack
     */
    public int size() {
        return array.size();
    }
    
    /**
     * Deletes all the elements in the stack
     */
    public void clear() {
        array.clear();
    }
    
    /**
     * Determines if the stack is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        if (array.size() == 0) {
            return true;
        }
        
        return false;
    }
}
