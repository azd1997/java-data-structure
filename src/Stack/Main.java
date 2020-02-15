package Stack;

import Queue.ArrayQueue;
import Queue.LoopQueue;
import Queue.Queue;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int opCount = 1000000;

        ArrayStack<Integer> arrayStack = new ArrayStack<Integer>();
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<Integer>();

        double t1 = testStack(arrayStack, opCount);
        double t2 = testStack(linkedListStack, opCount);

        System.out.println("t1 = " + t1 + " t2 = " + t2);
        //t1 = 0.138229973 t2 = 0.737718307
        // 在本机上链表栈更慢一些。但这不是必然的，取决于设备与JVM等等因素
        // 还跟数据、操作规模有关。
    }

    private static double testStack(Stack<Integer> stack, int opCount) {
        long startTime = System.nanoTime();

        // 测试QUEUE操作
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }

        long stopTime = System.nanoTime();

        return (stopTime - startTime) / 1000000000.0;   // 返回s数
    }
}
