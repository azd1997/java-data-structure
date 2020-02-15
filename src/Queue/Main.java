package Queue;

import Array.Array;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int opCount = 100000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>();
        LoopQueue<Integer> loopQueue = new LoopQueue<Integer>();
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<Integer>();

        double t1 = testQueue(arrayQueue, opCount);
        double t2 = testQueue(loopQueue, opCount);
        double t3 = testQueue(linkedListQueue, opCount);

        System.out.println("t1 = " + t1 + " t2 = " + t2 + " t3 = " + t3);
        // t1 = 1.248188572 t2 = 0.041429475 t3 = 0.027550181
    }

    private static double testQueue(Queue<Integer> q, int opCount) {
        long startTime = System.nanoTime();

        // 测试QUEUE操作
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }

        long stopTime = System.nanoTime();

        return (stopTime - startTime) / 1000000000.0;   // 返回s数
    }
}
