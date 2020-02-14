package Queue;

import Array.Array;

public class LoopQueue<E> implements Queue<E> {

    // 使用capacity+1个实际容量，存储capacity个数据
    // 判空条件 head = tail
    // 判满条件 (tail + 1) % capacity = head

    // 由于数据的增删改查与head/tail指针密切相关，底层数组
    // 的扩容时需要伴随着head、tail的更新，因此这里不再使用
    // 自动扩容的Array类，而是使用原生数组

    private E[] data;
    private int front, tail;
    private int size;   // 如果没有size，当添加resize()功能时要极为小心
    private int resizeFactor = 2;   // 扩容/缩容因子

    public LoopQueue(int capacity) {
        data = (E[])new Object[capacity + 1];
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public void enqueue(E e) {
        // 判断循环队列是否已满，满则扩容
        if (isFull()) {
            resize(capacity() * resizeFactor);
        }
        // 队尾插入数据
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    // 循环队列相比于普通队列出对操作变为了O(1)
    @Override
    public E dequeue() {
        // 判断循环队列是否已空，空则异常
        if (isEmpty()) {
            throw new IllegalArgumentException("Deque failed. queue is empty.");
        }

        E ret = data[front];
        data[front] = null;     // 这句可以要也可以不要，不会访问到，而且插入到这个位置会覆盖原数据
        front = (front + 1) % data.length;
        size--;

        // 判断循环队列是否为容量1/4，并且缩容后至少要为1，是则缩容至1/2
        if (size == capacity() / (2 * resizeFactor) && capacity() / 2 != 0) {
            resize(capacity() / resizeFactor);
        }

        return ret;
    }

    @Override
    public E front() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Deque failed. queue is empty.");
        }
        return data[front];
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public boolean isFull() {
        return (tail + 1) % data.length == front;
    }

    private void resize(int newcap) {
        E[] newdata = (E[])new Object[newcap + 1];
        // 第一种循环遍历方法 A
        for (int i = 0; i < size; i++) {
            newdata[i] = data[(front + i) % data.length];
        }
        data = newdata;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue: size = %d, capacity = %d\n", size, capacity()));
        res.append("front [");
        // 另一种循环遍历方式 B
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }


    public static void main(String[] args) {
        // Java会自动将基本数据类型(int,char,...)转换为包装类型(Integer,Char,...)
        LoopQueue<Integer> queue = new LoopQueue<Integer>(5);
        for (int i = 0; i < 9; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);

        queue.enqueue(100);
        System.out.println(queue);

        queue.enqueue(-1);
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);
    }
}
