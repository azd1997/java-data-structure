package Set;

import Tree.BST;
import utils.FileOperation;

import java.util.ArrayList;

// E必须实现Comparable接口
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    // 构造函数
    public BSTSet() {
        bst = new BST<E>();
    }

    // 添加
    @Override
    public void add(E e) {
        bst.add(e);
    }

    // 删除
    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    // 包含
    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    // 大小
    @Override
    public int size() {
        return bst.size();
    }

    // 判空
    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("BSTSet: {");
        String array = bst.toArray().toString();
        array = array.substring(1, array.length()-1);
        res.append(array).append('}');
        return res.toString();
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

        BSTSet<String> set1 = new BSTSet<String>();
        for (String word : words1) set1.add(word);
        System.out.println("Total different words: " + set1.size());
        // System.out.println(set1);

        System.out.println();

        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
        successed = FileOperation.readFile("./resources/text/a-tale-of-two-cities.txt", words2);
        if (!successed) {
            System.out.println("Read failed");
            return;
        }
        System.out.println("Total words: " + words2.size());

        BSTSet<String> set2 = new BSTSet<String>();
        for (String word : words2) set2.add(word);
        System.out.println("Total different words: " + set2.size());
    }
}
