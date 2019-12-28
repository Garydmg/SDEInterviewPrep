package implementation;

public interface Queue<E> {
    boolean offer(E e);
    E peek();
    E poll();
    int size();
    boolean isEmpty();
    void print(); // for viewing correctness of result
}
