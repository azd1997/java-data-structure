package Stack;

public class Main {
    public static void main(String[] args) {
        // Java会自动将基本数据类型(int,char,...)转换为包装类型(Integer,Char,...)
        ArrayStack<Integer> stack = new ArrayStack<Integer>();
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
