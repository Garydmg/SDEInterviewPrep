package implementation;

public interface List<E> {
    boolean add(E e);
    void add(int index, E element);
    void clear();
    boolean contains(Object o);
    E get(int index);
    boolean isEmpty();
    E remove(int index);
    E set(int index, E element);
    int size();
    void print(); // for viewing correctness of result
}
