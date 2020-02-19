package Set;

import LinkedList.LinkedList;
import utils.FileOperation;

import java.util.ArrayList;

// 链表并不要求元素具备可比性
public class LinkedListSet<E extends Comparable<E>> implements Set<E> {

    // 基于单链表的集合其增(增之前要先查)/删/查操作复杂度都为O(n)
    private LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<E>();
    }

    @Override
    public void add(E e) {
        // 链表中没有重复元素的限制

        if (!list.contains(e)) {
            list.addFirst(e);   // 对于单链表头部插入O(1)
        }
        // 存在则不修改
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedListSet: {");
        String array = list.toArray().toString();
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

        LinkedListSet<String> set1 = new LinkedListSet<String>();
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

        LinkedListSet<String> set2 = new LinkedListSet<String>();
        for (String word : words2) set2.add(word);
        System.out.println("Total different words: " + set2.size());


        // 执行之后，很明显可以看出来比BSTSet慢很多，BST一下子就出了结果，LinkedListSet卡了一会
    }
}
