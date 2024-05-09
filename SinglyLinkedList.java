import java.util.Iterator;

/**
 * Implements a singly linked list.
 * 
 * @param <T> the type of the values in the list.
 */
public class SinglyLinkedList<T extends Comparable<T>> implements Iterable<T> {
    /**
     * The head of the list.
     */
    private Node head;
    /**
     * The length of the list.
     */
    private int length;
    /**
     * The tail of the list.
     */
    private Node tail;

    /**
     * Constructs an empty list.
     */
    public SinglyLinkedList() {
        head = null;
        tail = null;
        length = 0;
    }

    /**
     * Adds a value to the end of the list.
     * 
     * @param value the value to add.
     */
    public void add(T value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        length++;
    }

    /**
     * Inserts a value to the proper location in the list so that the list order is
     * preserved (in descending order).
     * 
     * @param newValue the value to insert.
     */

    public void insert(T newValue) {
        Node newNode = new Node(newValue);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            if (head.value.compareTo(newValue) < 0) {
                newNode.next = head;
                head = newNode;
            } else {
                Node temp = head;
                while (temp.next != null && temp.next.value.compareTo(newValue) > 0) {
                    temp = temp.next;
                }
                newNode.next = temp.next;
                temp.next = newNode;
                if (newNode.next == null) {
                    tail = newNode;
                }
            }
        }
        length++;
    }

    /**
     * Removes the first item from the list.
     *
     * @return the removed item.
     */
    private T removeFirst() {
        if (length == 0) {
            throw new RuntimeException();
        }
        Node temp = head;
        if(length == 1){
            head = null;
            tail = null;
        }else{
            head = head.next;
            temp.next = null;
        }
        length--;
        return temp.value;
    }

    /**
     * Removes the last item from the list.
     *
     * @return the removed item.
     */
    private T removeLast() {
        if (length == 0) {
            throw new RuntimeException();
        }
        Node temp = head;
        if (length == 1) {
            head = null;
            tail = null;
            length--;
            return temp.value;
        }else{
            while (temp.next.next != null) {
                temp = temp.next;
            }
            Node temp2 = temp.next;
            temp.next = null;
            tail = temp;
            length--;
            return temp2.value;
        }
    }

    /**
     * Removes a single item from the list based on its index.
     * 
     * @param index the index of the item to be removed.
     * @return the removed item.
     */
    public T remove(int index) {
        if (index < 0 || index >= length) {
            throw new RuntimeException();
        }
        Node temp = head;
        if (index == 0) {
            return removeFirst();
        } else if (index == length - 1) {
            return removeLast();
        } else {
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            Node temp2 = temp.next;
            temp.next = temp2.next;
            temp2.next = null;
            length--;
            return temp2.value;
        }
    }

    /**
     * Returns (without removing) a single item from the list based on its index.
     * 
     * @param index the index of the item to be returned.
     * @return the item at the given index.
     */
    public T get(int index) {
        Node temp = head;
        if (index < 0 || index >= length) {
            throw new RuntimeException();
        }
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.value;
    }

    /**
     * Returns the length of the list.
     * 
     * @return the length of the list.
     */
    public int size() {
        return length;
    }

    /**
     * Returns whether the list is empty.
     * 
     * @return whether the list is empty.
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Iterator for the list.
     *
     * @return an iterator for the list.
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            /**
             * The current node.
             */
            private Node current = head;

            /**
             * Returns whether there is a next item in the list.
             * 
             * @return whether there is a next item in the list.
             */
            public boolean hasNext() {
                return current != null;
            }

            /**
             * Returns the next item in the list.
             * 
             * @return the next item in the list.
             */
            public T next() {
                if (!hasNext()) {
                    throw new RuntimeException();
                } else {
                    T value = current.value;
                    current = current.next;
                    return value;
                }
            }
        };
    }

    /**
     * Node class for the list.
     */
    private class Node {
        /**
         * The value of the node.
         */
        private T value;
        /**
         * The next node.
         */
        private Node next;

        /**
         * Constructs a node with a value.
         *
         * @param value the value of the node.
         */
        private Node(T value) {
            this.value = value;
        }
    }

}
