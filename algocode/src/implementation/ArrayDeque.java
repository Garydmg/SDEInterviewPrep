package implementation;
/**
 * Data structure implementation for interview: A simple implementation of Java ArrayDeque
 * --- by Daming Gao, last edited on Dec 27, 2019
 *
 * Use fixed-sized circular array to implement Queue and Deque interfaces
 * Resizable when capacity is reached
 *
 * Head and tail are the front and back of the data structure
 * Semantic Rule:
 * 1) (head, tail) -> exclusive
 * 2) tail = head + 1 -> data structure is empty
 * 3) head = tail -> data structure is full
 * @param <E>
 */
public class ArrayDeque<E> implements Queue<E>, Deque<E> {
    final private int DEFAULT_CAPACITY = 5;
    private int size;
    private int capacity = DEFAULT_CAPACITY;
    private int head = 0;
    private int tail = head + 1;
    private E[] array;

    /**
     * Constructors
     * 1) Use default capacity
     * 2) Lower bound is the default capacity
     */
    ArrayDeque() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
    }

    ArrayDeque(int numElements) {
        // lower bound is the default capacity
        capacity = Math.max(numElements, DEFAULT_CAPACITY);
        array = (E[]) new Object[capacity];
    }

    /**
     * Methods for Queue interface (return special values instead of throwing exceptions)
     * 1) offer: append to the back -> returns true when successful
     * 2) peek: look at the front -> returns the front element, null when empty
     * 3) poll: remove from the front -> returns the removed element, null when empty
     * @param e
     * @return
     */
    @Override public boolean offer(E e) {
        // if the array is full, expand
        if (head == tail) {
            expandCapacity();
        }
        array[tail] = e;
        tail = idxForward(tail);
        size++;
        return true;
    }

    @Override public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[idxForward(head)];
    }

    @Override public E poll() {
        if (isEmpty()) {
            return null;
        }
        E headVal = peek();
        head = idxForward(head);
        array[head] = null;
        size--;
        return headVal;
    }

    /**
     * Methods for Deque interface (return special values instead of throwing exceptions)
     * 1) offerFirst/offerLast: append to the back/prepend to the front -> returns true when successful
     * 2) peekFirst/peekLast: look at the front/back -> returns the front/back element, null when empty
     * 3) pollFirst/pollLast: remove from the front/back -> returns the removed element, null when empty
     * @param e
     * @return
     */
    @Override public boolean offerFirst(E e) {
        if (head == tail) {
            expandCapacity();
        }
        array[head] = e;
        head = idxBackward(head);
        size++;
        return true;
    }

    @Override public boolean offerLast(E e) {
        return offer(e);
    }

    @Override public E peekFirst() {
        return peek();
    }

    @Override public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return array[idxBackward(tail)];
    }

    @Override public E pollFirst() {
        return poll();
    }

    @Override public E pollLast() {
        E lastElement = peekLast();
        int last = idxBackward(tail);
        array[last] = null;
        tail = last;
        return lastElement;
    }

    /**
     * Method common for Queue and Deque interfaces
     * @return
     */
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return tail == head + 1;
    }

    /**
     * Private methods
     * 1) expandCapacity: when capacity is reached, copy the old content to a new array, reset head and tail pointers
     * 2) idxForward: move pointer to the right, if exceed range (> array.length - 1), wrap around (start from 0)
     * 3) idxBackward: move pointer to the left, if exceed range (< 0), start from array.length - 1
     */
    private void expandCapacity() {
        // make a new array of expanded capacity
        capacity = (int) Math.round(capacity * 1.5);
        E[] newArray = (E[]) new Object[capacity];

        // copy old elements over
        int start = idxForward(head);
        int oldNumElements = size;
        for (int i = 0; i < oldNumElements; ++i) {
            // newhead = 0, tail = lastElement + 1
            newArray[i + 1] = array[start];
            start = idxForward(start);
        }
        array = newArray;

        // update head and tail
        head = 0;
        tail = head + 1 + oldNumElements;
    }

    private int idxForward(int idx) {
        return (idx + 1) % array.length;
    }

    private int idxBackward(int idx) {
        return (idx - 1 < 0) ? array.length - 1 : idx - 1;
    }

    /**
     * A temporary print function for checking program correctness
     */
    @Override public void print() {
        if (isEmpty()) {
            System.out.println("Empty!");
        }
        else {
            System.out.println("------------------------------------");
            for (int i = 0; i < array.length; ++i) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
            System.out.println("head: " + head + ", tail: " + tail);
            System.out.println("size = " + size);
        }
    }

    /**
     * Driver program for testing
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("---------TEST QUEUE----------");
        Queue<Integer> queue = new ArrayDeque<>(4);
        for (int i = 0; i < 6; ++i) {
            queue.offer(i);
            queue.print();
        }
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        queue.print();
        for (int i = 6; i < 10; ++i) {
            queue.offer(i);
            queue.print();
        }
        for (int i = 0; i < 7; ++i) {
            System.out.println(queue.poll());
        }
        queue.print();
        for (int i = 10; i < 25; ++i) {
            queue.offer(i);
            queue.print();
        }
        System.out.println(queue.peek());

        System.out.println("---------TEST DEQUE----------");
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 6; ++i) {
            deque.offerFirst(i);
            deque.print();
            System.out.println("Peek first: " + deque.peekFirst());
            System.out.println("Peek last: " + deque.peekLast());
            deque.offerLast(i - 10000);
            deque.print();
            System.out.println("Peek first: " + deque.peekFirst());
            System.out.println("Peek last: " + deque.peekLast());
        }
        for (int i = 0; i < 5; ++i) {
            deque.pollFirst();
        }
        deque.print();
        System.out.println("Peek first: " + deque.peekFirst());
        System.out.println("Peek last: " + deque.peekLast());
        for (int i = 0; i < 6; ++i) {
            deque.offerLast(i + 100);
        }
        deque.print();
        System.out.println("Peek first: " + deque.peekFirst());
        System.out.println("Peek last: " + deque.peekLast());
        deque.pollLast();
        deque.pollFirst();
        deque.print();
        System.out.println("Peek first: " + deque.peekFirst());
        System.out.println("Peek last: " + deque.peekLast());
        deque.pollLast();
        deque.print();
        System.out.println("Peek first: " + deque.peekFirst());
        System.out.println("Peek last: " + deque.peekLast());
    }
}
