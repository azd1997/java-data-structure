package Set;

import utils.FileOperation;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int opCount = 100000;

        // 准备工作：读取文件
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();
        boolean successed = FileOperation.readFile("./resources/text/pride-and-prejudice.txt", words);
        if (!successed) {
            System.out.println("Read failed");
            return;
        }
        System.out.println("Total words: " + words.size());

        BSTSet<String> set1 = new BSTSet<String>();
        LinkedListSet<String> set2 = new LinkedListSet<String>();

        double t1 = testSet(words, set1);
        double t2 = testSet(words, set2);

        System.out.println("t1 = " + t1 + " t2 = " + t2);
        //Pride and Prejudice
        //Total words: 125901
        //Total different words: 6530
        //Total different words: 6530
        //t1 = 0.0647482 t2 = 3.270024038
    }

    private static double testSet(ArrayList<String> words, Set<String> set) {
        long startTime = System.nanoTime();

        // 测试Set添加操作
        for (String word : words) set.add(word);
        System.out.println("Total different words: " + set.size());

        long stopTime = System.nanoTime();

        return (stopTime - startTime) / 1000000000.0;   // 返回s数
    }
}
