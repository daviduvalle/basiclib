package du.cs.ds;

/**
 * Implements queue (FIFO) backed by a {@link DynamicArray}
 * @author daviduvalle
 *
 */
public class Queue<T> {

    private DynamicArray<T> array;
    
    /**
     * Default ctr
     */
    public Queue() {
        array = new DynamicArray<T>();
    }
    
    /**
     * Enqueues an element in the queue
     * @param element
     */
    public void enqueue(T element) {
        array.add(element);
    }
    
    /**
     * Dequeues an element from the queue
     * @return an element
     */
    public T dequeue() {
        if (array.size() > 0) {
            T element = array.get(0);
            array.remove(0);
            return element;
        }
        
        return null;
    }
    
    /**
     * Gets the size of the queue
     * @return size of the queue
     */
    public int size() {
        return array.size();
    }
    
    /**
     * Removes all the elements of the list
     */
    public void clear() {
        array.clear();
    }
    
    /**
     * Determines if the list is empty or not
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return array.size() == 0;
    }
}
