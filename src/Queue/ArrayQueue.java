package Queue;

import Array.Array;

public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<E>(capacity);
    }

    public ArrayQueue() {
        array = new Array<E>();
    }

    // 队尾入队
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    // 队首出队
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E front() {
        return array.first();
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front [");
        int n = array.size();
        for (int i = 0; i < n; i++) {
            res.append(array.get(i));
            if (i < n - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");

        return res.toString();
    }

    public static void main(String[] args) {
        // Java会自动将基本数据类型(int,char,...)转换为包装类型(Integer,Char,...)
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
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
