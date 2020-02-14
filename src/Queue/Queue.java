package Queue;

public interface Queue<E> {
    void enqueue(E e);  // 入队
    E dequeue();    // 出队
    E front();  // 队首元素
    int size();
    boolean isEmpty();
}
