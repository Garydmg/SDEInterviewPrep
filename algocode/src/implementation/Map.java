package implementation;

public interface Map<K,V> {
    void clear();
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    V get(Object key);
    boolean isEmpty();
    V put(K key, V value);
    V remove(Object key);
    boolean remove(Object key, Object value);
    V replace(K key, V value);
    boolean replace(K key, V oldValue, V newValue);
    int size();
}
