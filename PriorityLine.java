import java.util.Iterator;

/**
 * Implements a priority queue.
 *
 * @param <T> the type of elements in the priority queue.
 */
public class PriorityLine<T extends Comparable<T>> implements Iterable<T> {
    /**
     * Private queue to store the elements.
     */
    private SinglyLinkedList<T> queue;

    /**
     * Constructs an empty priority queue.
     */
    public PriorityLine() {
        queue = new SinglyLinkedList<>();
    }

    /**
     * Adds an element to the priority queue.
     *
     * @param element the element to add.
     */
    public void enqueue(T element) {
        queue.insert(element);
    }

    /**
     * Removes the element with the highest priority from the priority queue.
     *
     * @return removed element.
     */
    public T dequeue() {
        return queue.remove(0);
    }

    /**
     * Returns the number of elements in the priority queue.
     *
     * @return the number of elements in the priority queue.
     */
    public int size() {
        return queue.size();
    }

    /**
     * Returns true if the priority queue is empty, false otherwise.
     *
     * @return true if the priority queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Returns the element with the highest priority from the priority queue.
     *
     * @return the element with the highest priority from the priority queue.
     */
    public T peek() {
        return queue.get(0);
    }

    /**
     * Returns an iterator over the elements in the priority queue.
     *
     * @return an iterator over the elements in the priority queue.
     */
    public Iterator<T> iterator() {
        return queue.iterator();
    }
}
