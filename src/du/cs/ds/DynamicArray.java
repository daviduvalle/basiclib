package du.cs.ds;

/**
 * Dynamic array capable of growing and
 * shrinking based on the number of elements
 * contained
 * @author daviduvalle
 *
 * @param <T> any type
 */
public class DynamicArray<T> {
    private Object[] array;
    private int size;
    
    /**
     * Default ctr.
     */
    public DynamicArray() {
        this(10);
    }
    
    /**
     * Ctr with initial capacity
     * @param initialCapacity initial array size
     */
    public DynamicArray(int initialCapacity) {
        if (initialCapacity <= 0) {
            return;
        }
        this.array = new Object[initialCapacity];
    }
    
    /**
     * Adds an element to the array
     * @param element of type T
     */
    public void add(T element) {
        if (isSpaceAvailable()) {
            array[size++] = element;
        }
        else {
            // It will increase array size
            // to store more elements
            updateArraySize(array.length);
            add(element);
        }
    }
    
    /**
     * Removes an element in the array in the
     * given index
     * @param index of the element to be removed
     */
    public void remove(int index) {
        if (index < 0 || index > size) {
            return;
        }
        
        // Removes the element by overwriting it
        for (int i = index; i < size && i < array.length-1; i++) {
            array[i] = array[i+1];
        }
        
        if (size < array.length)
        {
            array[size] = null;
        }
        
        // Check if the array size needs to be decreased
        if (array.length > (size * 2)) {
            updateArraySize(size);
        }
        
        size--;
    }
    
    /**
     * Gets the size of the array
     * @return array size
     */
    public int size() {
        return size;
    }
    
    /**
     * Removes all the elements from the array
     */
    public void clear() {
        clear(0, array.length);
        size = 0;
    }
    
    /**
     * Returns an element in the array given an index
     * @param index element index
     * @return element
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }
    
    /**
     * Cleans a range of elements in the array
     * @param startIndex start index
     * @param endIndex end index
     */
    private void clear(int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            array[i] = null;
        }
    }
    
    /**
     * Determines if there is space available in the array
     * @return true if there is space, false otherwise
     */
    private boolean isSpaceAvailable() {
        if (size == array.length) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Updates the array size by creating a new one
     * and coping elements of the old array to the new
     * created one until the stop index
     * @param stopIndex determines where to stop copying elements
     */
    private void updateArraySize(int stopIndex) {
        Object[] newArray = new Object[size*2];
        copyElements(this.array, newArray, stopIndex);
        clear(0, array.length);
        this.array = null;
        this.array = newArray;
    }
    
    /**
     * Copies elements from a source array to a destination one
     * @param source source array
     * @param destination destination array
     * @param stopIndex where to stop copying elements
     */
    private void copyElements(Object[] source, Object[] destination, int stopIndex) {
        for (int i = 0; i < stopIndex; i++) {
            destination[i] = source[i];
        }
    }
}
