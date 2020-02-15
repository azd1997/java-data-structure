package Queue;

public class LinkedListQueue<E> implements Queue<E> {


    // 内部私有类 Node
    private class Node {
        public E e; // 节点值
        public Node next;   // 后继指针

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }


    // 这里head和tail都指向真正有数据的节点，并不是虚拟节点
    private Node head, tail;
    private int size;

    // 对于基于有头尾节点的单向链表而言，
    // 其尾部插入操作为O(1)，但尾部删除仍为O(n)
    // 因此改造成单端队列时，应该从链表尾部push，从头部pop

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {     // 链表队列为空
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {     // 队列为空
            throw new IllegalArgumentException("Dequeue failed. queue is empty.");
        }

        // 要注意检查队列是否只有一个元素，那么出队之后，tail也要为空
        // head = head.next = null
        if (size == 1) {
            tail = null;
        }

        Node ret = head;
        head = head.next;
        ret.next = null;    // 消除ret节点对其他后继结点的引用
        size--;

        return ret.e;
    }

    @Override
    public E front() {
        if (isEmpty()) {     // 队列为空
            throw new IllegalArgumentException("Dequeue failed. queue is empty.");
        }
        return head.e;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        // head==null; tail==null都可以作为判空条件
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front [");

        Node cur = head;
        while (cur != null) {
            if (cur.next != null) {
                res.append(cur.e).append(", ");
            } else {
                res.append(cur.e);
            }
            cur = cur.next;
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
