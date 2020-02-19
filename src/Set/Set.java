package Set;

// 集合，不能重复添加元素

public interface Set<E> {
    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int size();
    boolean isEmpty();
}
