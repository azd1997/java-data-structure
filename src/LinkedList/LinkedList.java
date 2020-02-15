package LinkedList;

public class LinkedList<E> {

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

    // 内部变量
    private Node head;
    private int size;

    // 生成空链表
    public LinkedList() {
        head = new Node(null, null);
        size = 0;
    }

    // 根据数组生成链表
    public LinkedList(E[] es) {
        Node front = new Node();
        Node node = front;
        for (E e : es) {
            node.next = new Node(e);
            node = node.next;
        }
        head = front;
        size = es.length;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        // index 非法
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. require 0 <= index <= size.");
        }

        // index >= 0
        Node pre = head;
        // 从前向后遍历，pre停在原第index-1个节点(pre需更新index次)
        for (int i = 0; i < index; i++) {   // 迭代index-1次
            pre = pre.next;
        }
        // pre 现在为原index节点的前一个

        // 将pre.next接在一个新节点后，再把新节点接在pre后面
        pre.next = new Node(e, pre.next);
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    // 查询
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. require 0 <= index < size");
        }

        // 从虚拟头结点开始迭代index+1次到达index位置
        Node cur = head;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E first() {
        return get(0);
    }

    public E last() {
        return get(size-1);
    }

    // 修改
    public void set(int index, E newe) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. require 0 <= index < size.");
        }

        Node cur = head.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        cur.e = newe;
    }

    // 查询是否存在
    public boolean contains(E e) {
        Node cur = head.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // 删除
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. require 0 <= index < size.");
        }

        // 需将pre指向原index-1位置节点，需更新pre index 次
        Node pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        Node
                retNode = pre.next;
        pre.next = retNode.next;    // pre指向index节点后一个
        retNode.next = null;    // 消除index节点对其他节点的引用，让其早点回收
        size--;

        return retNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LinkedList: size = %d\n", size));
        res.append("head -> ");     // 虚拟头结点

        Node cur = head.next;
        while (cur != null) {
            res.append(cur).append(" -> ");
            cur = cur.next;
        }
        res.append("null"); //末尾

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            list1.add(i, i);
            System.out.println(list1);
        }
        list1.addFirst(99);
        System.out.println(list1);

        list1.removeFirst();
        System.out.println(list1);

        Integer[] data = {1,2,3};
        LinkedList<Integer> list2 = new LinkedList<Integer>(data);
        System.out.println(list2);
    }
}
