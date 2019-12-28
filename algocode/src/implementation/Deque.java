package implementation;

public interface Deque<E> {
    boolean offerFirst(E e);
    boolean offerLast(E e);
    E peekFirst();
    E peekLast();
    E pollFirst();
    E pollLast();
    int size();
    boolean isEmpty();
    void print(); // for viewing correctness of result
}
