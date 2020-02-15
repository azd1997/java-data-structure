package Stack;

import LinkedList.LinkedList;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<E>();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.first();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < list.size(); i++) {
            res.append(list.get(i));
            if (i != list.size() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }

    public static void main(String[] args) {
        // Java会自动将基本数据类型(int,char,...)转换为包装类型(Integer,Char,...)
        LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        System.out.println(stack);

        stack.push(100);
        System.out.println(stack);

        stack.push(-1);
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);
    }
}
