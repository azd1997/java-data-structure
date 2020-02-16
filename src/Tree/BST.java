package Tree;

import Array.Array;
import Queue.ArrayQueue;
import Stack.ArrayStack;

import java.util.Random;

// 二分搜索树
// 节点的值必须是可比较类型: E extends Comparable<E>
// 这里实现的BST不包含重复元素 left<node<right 如果重复元素加入，最终啥也不干
// 若要包含重复元素，可以考虑 left<=node<right 或者 left<node<=right
public class BST<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    // 树的根节点
    private Node root;
    // 记录树的元素数量
    private int size;

    // 构造空树
    public BST() {
        root = null;
        size = 0;
    }

    // 根据数组构造二叉搜索树
    public BST(E[] es) {
        for (E e : es) {
            add(e);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 添加
    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//                    size++;
//        } else {
//            add1(root, e);
//        }

        root = add2(root, e);
    }

    // 递归插入元素(实现1)
    private void add1(Node node, E e) {
        if (e.equals(node.e)) {     // 元素已经存在
            return;
        }
        if (e.compareTo(node.e) < 0) {
            if (node.left == null) {
                node.left = new Node(e);
                size++;
                return;
            }
            add1(node.left, e);
        } else {
            if (node.right == null) {
                node.right = new Node(e);
                size++;
                return;
            }
            add1(node.right, e);
        }
    }

    // 添加(实现2) 返回新的根节点
    private Node add2(Node node, E e) {
        // 递归终止
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add2(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add2(node.right, e);
        }   // 等于的情况就什么都不做

        return node;
    }

    // 查询是否存在
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) return false;

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    // 前中后序遍历又称深度优先遍历，而层序遍历则是广度优先遍历


    // 前序遍历(traverse)
    public void preOrder() {
        preOrder1(root);
        //preOrder2(root);
    }

    // 递归实现前序遍历
    private void preOrder1(Node node) {
        // 递归终止
        if (node == null) return;

        // 处理当前节点
        System.out.println(node.e);

        // 接着遍历左右子树
        preOrder1(node.left);
        preOrder1(node.right);
    }

    // 循环实现前序遍历(借助栈)
    private void preOrder2(Node node) {
        ArrayStack<Node> stack = new ArrayStack<Node>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
    }

    // 中序遍历(traverse)
    // 适用场景：输出顺序序列
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        // 递归终止
        if (node == null) return;

        // 先遍历左子树
        inOrder(node.left);

        // 再处理当前节点
        System.out.println(node.e);

        // 最后遍历右子树
        inOrder(node.right);
    }


    // 后序遍历(traverse)
    // 适用场景：
    // (1)二叉搜索树内存的释放(对于需要手动释放内存的语言)
    // (2)需要先处理完子问题才能合并得到当前节点的问题的情况，例如分治...
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        // 递归终止
        if (node == null) return;

        // 先遍历左右子树
        postOrder(node.left);
        postOrder(node.right);

        // 处理当前节点
        System.out.println(node.e);
    }

    // 层序遍历 （广度优先遍历）
    public void levelOrder() {
        ArrayQueue<Node> queue = new ArrayQueue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node cur = queue.dequeue();
            System.out.println(cur.e);

            if (cur.left != null) queue.enqueue(cur.left);
            if (cur.right != null) queue.enqueue(cur.right);
        }
    }

    // 寻找树中最小值
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }
        return minimum(root).e;
    }

    // 返回以node为根的最小值节点
    private Node minimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // 寻找树中最大值
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }
        return maximum(root).e;
    }

    // 返回以node为根的最大值节点
    private Node maximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // 删除树中最小值
    public E removeMin() {
        E min = minimum();
        root = removeMin(root);
        return min;
    }

    // 在以node为根的二分搜索树中，删除最小值，返回新的根
    private Node removeMin(Node node) {
        // 递归到底
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;  // 去除引用
            size--;
            return rightNode;
        }

        // 继续向左边走
        node.left = removeMin(node.left);
        // 最后返回node新根
        return node;
    }

    // 删除树中最大值
    public E removeMax() {
        E max = maximum();
        root = removeMax(root);
        return max;
    }

    // 在以node为根的二分搜索树中，删除最大值，返回新的根
    private Node removeMax(Node node) {
        // 递归到底
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;  // 去除引用
            size--;
            return leftNode;
        }

        // 继续向右边走
        node.right = removeMax(node.right);
        // 最后返回node新根
        return node;
    }

    // 删除任意值
    public void remove(E e) {
        root = remove(root, e);
    }

    // 移除node为根的子树中值为e的节点，返回新的根
    private Node remove(Node node, E e) {
        if (node == null) return null;

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {    // e == node.e，也就是要删除这个节点
            // 待删除节点左子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 待删除节点右子树为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 待删除节点左右子树都不为空
            // node.left != null && node.right != null
            // 删除左右都有孩子的节点d，要找到其后继s = min(d.right)，
            // 然后s.right = delmin(d.right)， s.left = d.left，
            // 然后删除d，s是新的子树的根
            // (这种情况的删除，还有另一种做法，就是用node的左子树最大值节点作为后继。)
            Node successor = minimum(node.right);   // 后继结点
            // 注意removeMin中已经size--了
            successor.right = removeMin(node.right);    // 删除后继结点原先位置，并将剩下的子树挂载到后继结点右侧
            successor.left = node.left;     // 将left左子树挂载到后继结点左侧
            node.left = null; node.right = null;    // 去引用
            return successor;
        }
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth)).append("null\n");
            return;
        }

        res.append(generateDepthString(depth)).append(node.e).append("\n");
        generateBSTString(node.left, depth+1, res);
        generateBSTString(node.right, depth+1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }


    public static void main(String[] args) {
//        Integer[] data = {5,3,6,8,4,2};
//        BST<Integer> bst = new BST<>(data);
//        bst.preOrder();
//        System.out.println("=============");
//        bst.inOrder();
//        System.out.println("=============");
//        bst.postOrder();
//        System.out.println("=============");
//        bst.levelOrder();

        //System.out.println(bst);


//        /*removeMin test*/
//        BST<Integer> bst = new BST<Integer>();
//        Random rand = new Random();
//
//        int n = 1000;
//        int bound = 10000;
//        for (int i = 0; i < n; i++) {
//            bst.add(rand.nextInt(bound));
//        }
//        // 得到的BST中不一定有n个数
//        Array<Integer> arr = new Array<Integer>(n);
//        while (!bst.isEmpty()) {
//            arr.addLast(bst.removeMin());
//        }
//        System.out.println(arr);
//        // 判断下arr是否从小到大
//        for (int i = 1; i < arr.size(); i++) {
//            if (arr.get(i) < arr.get(i-1)) {
//                throw new IllegalArgumentException("Error");
//            }
//        }
//        System.out.println("removeMin test completed");


//        /*removeMax test*/
//        BST<Integer> bst = new BST<Integer>();
//        Random rand = new Random();
//
//        int n = 1000;
//        int bound = 10000;
//        for (int i = 0; i < n; i++) {
//            bst.add(rand.nextInt(bound));
//        }
//        // 得到的BST中不一定有n个数
//        Array<Integer> arr = new Array<Integer>(n);
//        while (!bst.isEmpty()) {
//            arr.addLast(bst.removeMax());
//        }
//        System.out.println(arr);
//        // 判断下arr是否从小到大
//        for (int i = 1; i < arr.size(); i++) {
//            if (arr.get(i) > arr.get(i-1)) {
//                throw new IllegalArgumentException("Error");
//            }
//        }
//        System.out.println("removeMax test completed");


        /*remove test*/
        Integer[] data = {5,3,4,2,7,9};
        BST<Integer> bst = new BST<>(data);
        System.out.println(bst);
        bst.remove(3);
        System.out.println(bst);
    }
}
