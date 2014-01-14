package du.cs.ds;

/**
 * Singly linked list providing basic operations
 * This class is not thread-safe
 * @author daviduvalle
 *
 * @param <T> any type
 */
public class LinkedList<T> {
    
    private LinkedList<T> next;
    private LinkedList<T> head;
    private LinkedList<T> current;
    private LinkedList<T> iterator;
    private int size;
    private T value;

    /**
     * Default ctr.
     */
    public LinkedList() {
    }

    /**
     * Adds an element to the list
     * @param nodeValue element value
     */
    public void add(T nodeValue) {	
        LinkedList<T> tmp = new LinkedList<T>();
        tmp.value = nodeValue;
        tmp.next = null;

        if (head == null) {
            head = tmp;
            current = head;
        }
        else {
            current.next = tmp;
            current = current.next;
        }
        size++;
        iterator = head;
    }

    /**
     * Returns the element selected by the index
     * @param elementIndex position of the element
     * @return list element or null
     */
    public T get(int index) {
        if (head == null) {
            return null;
        }

        int count = 0;
        LinkedList<T> iterator = head;

        while (count < index) {
            if (iterator.next != null) {
                iterator = iterator.next;
                count++;
            }
            else {
                return null;
            }
        }

        return iterator.value;
    }

    /**
     * Deletes elements from the list
     * @param index index of the element to delete
     */
    public void delete(int index) {
        if (index > size || index < 0 || head == null) {
            return;
        }
        
        LinkedList<T> tmp = head;
        
        if (index == 0) {
            head = head.next;
            tmp.next = null;
            tmp = null;
            size--;
            return;
        }
        
        int count = 0;
        
        while (count < index) {
            if (count+1 == index) {
                LinkedList<T> selected = tmp.next;
                
                // Handles deleting the last node
                if (selected.next == null) {
                    selected = null;
                    size--;
                    return;
                }
                
                tmp.next = tmp.next.next;
                selected.next = null;
                selected = null;
                size--;
                return;
            }
            count++;
            tmp = tmp.next;
        }
    }
    
    /**
     * Deletes the last element of the list
     */
    public T deleteLast() {
        if (head == null || current == null) {
            return null;
        }
        
        if (head == current) {
            head = null;
            size--;
            return current.value;
        }
        
        LinkedList<T> tmp = head;
        
        while (tmp != current) {
            if (tmp.next != null && tmp.next == current) {
                tmp.next = null;
                LinkedList<T> lastNode = current;
                current = null;
                current = tmp;
                size--;
                return lastNode.value;
            }
            tmp = tmp.next;
        }
        
        return null;
    }

    /**
     * Gets the size of the list
     * @return number of elements in the list
     */
    public int size() {
        return size;
    }
    
    /**
     * Deletes all elements of the list
     */
    public void clear() {
        if (head == null) {
           return;
        }
        
        LinkedList<T> tmp = head;
        
        while (head != null) {
            tmp.next = null;
            tmp = null;
            head = head.next;
        }
        
        size = 0;
    }
    
    /**
     * Reverse the list
     */
    public void reverse() {
        if (head == null || head.next == null) {
            return;
        }
        
        LinkedList<T> tmp = head;
        LinkedList<T> previous = null;
        
        while (tmp != null) {
            LinkedList<T> nextElement = tmp.next;
            tmp.next = previous;
            previous = tmp;
            tmp = nextElement;
        }
        
        head = previous;
        reset();
    }
    
    /**
     * Gets the next element in the list
     * @return the next element in the list
     */
    public T getNext() {
        LinkedList<T> next = iterator;
        iterator = iterator.next;
        return next.value;
    }
    
    /**
     * Gets the list status
     * @return true if there are more elements in the list
     */
    public boolean hasNext() {
        return iterator != null ? true : false;
    }

    /**
     * Resets the list iterator back to the first element
     */
    public void reset() {
        iterator = head;
    }
    
    /**
     * Overrides toString
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = 
                new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            T value = this.get(i);
            stringBuilder.append(value.toString());
            if (i != size-1) {
                stringBuilder.append(" -> ");
            }
        }
        
        return stringBuilder.toString();
    }
}
