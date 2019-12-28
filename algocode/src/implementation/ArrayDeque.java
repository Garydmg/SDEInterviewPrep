package implementation;

public class ArrayDeque<E> implements Queue<E>, Deque<E> {
    final private int DEFAULT_CAPACITY = 5;
    private int size;
    private int capacity = DEFAULT_CAPACITY;
    // (head, tail) -> exclusive
    // empty: tail = head + 1
    // full: head = tail
    private int head = 0;
    private int tail = head + 1;
    E[] array;

    // constructor
    ArrayDeque() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
    }
    ArrayDeque(int numElements) {
        // lower bound is the default capacity
        capacity = Math.max(numElements, DEFAULT_CAPACITY);
        array = (E[]) new Object[capacity];
    }

    // methods for Queue interface
    @Override
    public boolean offer(E e) {
        // if the array is full
        if (head == tail) {
            expandCapacity();
        }
        array[tail] = e;
        tail = idxForward(tail);
        size++;
        return true;
    }
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[idxForward(head)];
    }
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E headVal = peek();
        head = idxForward(head);
        array[head] = null;
        size--;
        return headVal;
    }

    // methods for Deque interface
    @Override
    public boolean offerFirst(E e) {
        if (head == tail) {
            expandCapacity();
        }
        array[head] = e;
        head = idxBackward(head);
        size++;
        return true;
    }
    @Override
    public boolean offerLast(E e) {
        return offer(e);
    }
    @Override
    public E peekFirst() {
        return peek();
    }
    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return array[idxBackward(tail)];
    }
    @Override
    public E pollFirst() {
        return poll();
    }
    @Override
    public E pollLast() {
        E lastElement = peekLast();
        int last = idxBackward(tail);
        array[last] = null;
        tail = last;
        return lastElement;
    }

    // methods common for Queue and Deque interfaces
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return tail == head + 1;
    }

    // private method
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

    @Override
    public void print() {
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
