package Tree;

import Queue.ArrayQueue;
import Stack.ArrayStack;

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
        Integer[] data = {5,3,6,8,4,2};
        BST<Integer> bst = new BST<>(data);
        bst.preOrder();
        System.out.println("=============");
        bst.inOrder();
        System.out.println("=============");
        bst.postOrder();
        System.out.println("=============");
        bst.levelOrder();

        //System.out.println(bst);
    }
}
