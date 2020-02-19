package Map;

import utils.FileOperation;

import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V> implements Map<K,V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K k, V v, Node left, Node right) {
            this.key = k;
            this.value = v;
            this.left = left;
            this.right = right;
        }

        public Node(K k, V v) {
            this(k, v, null, null);
        }

        public Node() {
            this(null, null, null, null);
        }

        @Override
        public String toString() {
            return this.key.toString() + " : " + this.value.toString();
        }
    }


    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void add(K k, V v) {
        root = add(root, k, v);
    }

    // 将结点插入，返回新的根节点
    private Node add(Node node, K k, V v) {
        // 递归终止条件
        if (node == null) {
            size++;
            node = new Node(k, v);
        }

        if (k.compareTo(node.key) < 0) {
            node.left = add(node.left, k, v);
        } else if (k.compareTo(node.key) > 0) {
            node.right = add(node.right, k, v);
        } else {    // else if k == node.key 更新value
            node.value = v;
        }

        return node;
    }

    @Override
    public V remove(K k) {
        Node node = getNode(root, k);
        if (node == null) return null;

        // node != null
        root = remove(root, k);
        return node.value;
    }

    private Node remove(Node node, K k) {
        if (node == null) return null;

        if (k.compareTo(node.key) < 0) {
            node.left = remove(node.left, k);
            return node;
        } else if (k.compareTo(node.key) > 0) {
            node.right = remove(node.right, k);
            return node;
        } else {    // key相等
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;  // 去引用
                size--;
                return rightNode;   // rightNode替代node返回
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else {    // 左右子树均不为空
                Node successor = minimum(node.right);   // 后继结点
                // 注意removeMin中已经size--了
                successor.right = removeMin(node.right);    // 删除后继结点原先位置，并将剩下的子树挂载到后继结点右侧
                successor.left = node.left;     // 将left左子树挂载到后继结点左侧
                node.left = null; node.right = null;    // 去引用
                return successor;
            }
        }
    }

    private Node minimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {    // 到了左边最下边
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public boolean contains(K k) {
        return getNode(root, k) != null;
    }

    @Override
    public V get(K k) {
        Node node = getNode(root, k);
        return node == null ? null : node.value;
    }

    private Node getNode(Node node, K k) {
        if (node==null) return null;     // 没找到

        if (k.compareTo(node.key) == 0) {
            return node;
        } else if (k.compareTo(node.key) < 0) {
            return getNode(node.left, k);
        } else {    // >0
            return getNode(node.right, k);
        }
    }

    @Override
    public void set(K k, V v) {
        Node node = getNode(root, k);
        if (node == null) {
            throw new IllegalArgumentException("key [" + k.toString() + "] is not existed.");
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

        BSTMap<String, Integer> map = new BSTMap<String, Integer>();
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
    }
}
