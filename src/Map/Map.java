package Map;

// K必须可比较
public interface Map<K extends Comparable<K>, V> {
    void add(K k, V v);
    V remove(K k);
    boolean contains(K k);
    V get(K k);
    void set(K k, V v);
    int size();
    boolean isEmpty();
}
