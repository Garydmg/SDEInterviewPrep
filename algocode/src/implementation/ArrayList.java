package implementation;

public class ArrayList<E> implements List<E> {
    private final int DEFAULT_CAPACITY = 10;
    private int capacity = DEFAULT_CAPACITY;
    private int size = 0;
    E[] array;

    // constructors
    ArrayList() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
    }
    ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        capacity = initialCapacity;
        array = (E[]) new Object[capacity];
    }
    // frequently used methods
    // returns the element at the specified position in this list
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }
    // replaces the element at the specified position in this list with specified element
    // returns previous element
    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException{
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E prev = array[index];
        array[index] = element;
        return prev;
    }
    // returns true if this list contains the specified element
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; ++i) {
            if (o.equals(array[i])) {
                return true;
            }
        }
        return false;
    }
    // appends the specified element to the end of the list
    @Override
    public boolean add(E e) {
        if (size == capacity) {
            expandCapacity(); // update capacity
        }
        size++;
        array[size - 1] = e;
        return true;
    }
    // inserts the specified element at specified position
    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        // IMPORTANT: index > size, not >=
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == capacity) {
            expandCapacity(); // update capacity
        }
        // add to other places
        if (index != size) {
            // shift all elements > index to the right
            for (int i = size; i > index; --i) {
                array[i] = array[i - 1];
            }
        }
        size++;
        // include adding to end position
        array[index] = element;
    }
    // removes the element at the specified position in the list
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E tobeRemoved = array[index];
        // if index is not the last element
        if (index != size - 1) {
            for (int i = index; i < size - 1; ++i) {
                array[i] = array[i + 1];
            }
        }
        size--;
        return tobeRemoved;
    }
    // removes all elements
    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    // print contents of array
    @Override
    public void print() {
        System.out.println("----------------------------");
        for (int i = 0; i < size; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        System.out.println("Size: " + size + " Capacity: " + capacity);
    }
    // helper function to expand capacity
    private void expandCapacity() {
        // if initial capacity is set to 0
        if (capacity == 0) {
            capacity = 1;
        }
        // make sure it rounds up: 1.5 -> 2
        capacity = (int) Math.round(capacity * 1.5);
        E[] newArray = (E[]) new Object[capacity];

        // copy old elements over
        for (int i = 0; i < size; ++i) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
    // driver program
    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>(0);
        System.out.println(array.isEmpty());
        for (int i = 0; i < 5; ++i) {
            array.add(i);
            array.print();
        }
        System.out.println("---------TEST EMPTY--------------");
        System.out.println(array.isEmpty());
        System.out.println("---------TEST GET--------");
        System.out.println(array.get(4));
        System.out.println(array.get(0));
        System.out.println("---------TEST SET--------");
        System.out.println(array.set(1, 999));
        System.out.println(array.set(4, 9999));
        array.print();
        System.out.println("---------TEST ADD--------");
        array.add(5, 100);
        array.print();
        System.out.println("---------TEST REMOVE---------");
        System.out.println((array.remove(1)));
        System.out.println((array.remove(4)));
        System.out.println((array.remove(0)));
        array.print();
        System.out.println("---------TEST ADD--------");
        array.add(2, 100);
        array.print();
        array.clear();
        array.add(2);
        array.add(3);
        array.print();
        System.out.println("---------TEST CONTAINS--------");
        System.out.println(array.contains(3));
        System.out.println(array.contains(1));

        ArrayList<String> stringArray = new ArrayList<>(3);
        stringArray.add("Hello");
        stringArray.add("World");
        stringArray.print();
        stringArray.add(2, "!");
        stringArray.print();
        stringArray.add(3, "!!");
        stringArray.print();
        stringArray.remove(2);
        stringArray.print();
    }
}
