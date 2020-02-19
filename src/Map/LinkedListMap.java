package Map;

import LinkedList.LinkedList;
import utils.FileOperation;

import java.util.ArrayList;

public class LinkedListMap<K extends Comparable<K>, V> implements Map<K,V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K k, V v, Node next) {
            this.key = k;
            this.value = v;
            this.next = next;
        }

        public Node(K k, V v) {
            this(k, v, null);
        }

        public Node(K k) {
            this(k, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return this.key.toString() + " : " + this.value.toString();
        }
    }


    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    // 从链表中找到k对应的节点
    private Node getNode(K k) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(k)) return cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void add(K k, V v) {
        // 首先getNode检查是否存在键
        Node node = getNode(k);
        if (node == null) {
            // 直接加到链表头部
            dummyHead.next = new Node(k, v, dummyHead.next);
            size++;
        } else node.value = v;  // 存在的话更新值 (或者抛异常，取决于设计)
    }

    @Override
    public V remove(K k) {

        Node pre = dummyHead;
        V ret = null;
        while (pre.next != null) {
            if (pre.next.key.equals(k)) {
                ret = pre.next.value;
                pre.next = pre.next.next;
                size--;
            } else pre = pre.next;
        }

        return ret;
    }

    @Override
    public boolean contains(K k) {
        return getNode(k) != null;
    }

    @Override
    public V get(K k) {
        Node node = getNode(k);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K k, V v) {
        Node node = getNode(k);
        if (node == null) {
            // 这里选择抛异常，如果还加入的话，那么和add方法重复了
            throw new IllegalArgumentException("key [" + k + "] is not existed.");
        }

        node.value = v;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }




    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        boolean successed = FileOperation.readFile("./resources/text/pride-and-prejudice.txt", words1);
        if (!successed) {
            System.out.println("Read failed");
            return;
        }
        System.out.println("Total words: " + words1.size());

        LinkedListMap<String, Integer> map = new LinkedListMap<String, Integer>();
        for (String word : words1) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.add(word, 1);
            }
        }
        System.out.println("Total different words: " + map.size());
        System.out.println("Frequency of word [pride]: " + map.get("pride"));
        //
        // System.out.println(set1);

    }
}
