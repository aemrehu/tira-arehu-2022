package oy.tol.tra;

import javax.swing.DefaultBoundedRangeModel;

public class QueueImplementation<E> implements QueueInterface<E> {
    
    private Object [] itemArray;
    private int capacity;       //max amount of elements
    private int count;          //amount of elements in the queue
    private int head;           //index of the head
    private int tail;           //index of the tail

    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Allocates a queue with default capacity.
     * @throws QueueAllocationException
     */
    public QueueImplementation() throws QueueAllocationException {
        try {
            capacity = DEFAULT_CAPACITY;
            itemArray = new Object[capacity];
            head = 0;
            tail = 0;
            count = 0;
        } catch (Exception e) {
            throw new QueueAllocationException(e.getMessage());
        }
    }

    /**
     * Allocates a queue with desired capacity.
     * @param size
     * @throws QueueAllocationException
     */
    public QueueImplementation(int size) throws QueueAllocationException {
        if (size < 2) {
            throw new QueueAllocationException("Queue size should be greater than 1");
        }
        try {
            capacity = size;
            itemArray = new Object[capacity];
            head = 0;
            tail = 0;
            count = 0;
        } catch (Exception e) {
            throw new QueueAllocationException(e.getMessage());
        }
    }

    //@Override
    public int capacity() {
        return capacity;
    }

    //@Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        if (count == capacity) {
            try {
                int oldCapacity = capacity;
                capacity *= 2; //double the queue size
                final Object [] newArray = new Object[capacity]; //new queue object
                for (int i = 0; i < count; i++) {
                    newArray[i] = itemArray[head];
                    head++;
                    if (head >= oldCapacity) {
                        head = 0;
                    }
                }
                head = 0;
                tail = oldCapacity;
                itemArray = newArray;
            } catch (Exception e) {
                throw new QueueAllocationException("Reallocation failed.");
            }
        }
        if (tail >= capacity && head > 0) {
            tail = 0;
        }
        itemArray[tail] = element;
        count++;
        tail++;
    }

    //@Override
    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty() == true) {
            throw new QueueIsEmptyException("Not implemented yet.");
        }
        int index = head;
        head++;
        count--;
        if (head >= capacity) {
            head = 0;
        }
        return (E) itemArray[index];
    }

    //@Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty() == true) {
            throw new QueueIsEmptyException("The queue is empty.");
        } else {
            return (E) itemArray[head];
        }
    }

    //@Override
    public int size() {
        return count;
    }

    //@Override
    public boolean isEmpty() {
        if ( count == 0) {
            return true;
        } else {
            return false;
        }
    }

    //@Override
    public void clear() {
        final Object [] newArray = new Object[capacity];
        itemArray = newArray;
        head = 0;
        tail = 0;
        count = 0;
    }

    //@Override
    public String toString() {
        if (isEmpty() == true) {
            return "[]";
        }
        int x = head;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i=0; i < count; i++) {
            sb.append(itemArray[x].toString());
            x++;
            if (x >= capacity) {
                x = 0;
            }
            if (i < count - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
