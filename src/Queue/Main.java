package Queue;

public class Main {
    public static void main(String[] args) {
        // Java会自动将基本数据类型(int,char,...)转换为包装类型(Integer,Char,...)
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
        for (int i = 0; i < 9; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);

        queue.enqueue(100);
        System.out.println(queue);

        queue.enqueue(-1);
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);
    }
}
