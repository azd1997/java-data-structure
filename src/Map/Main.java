package Map;

import utils.FileOperation;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // 准备工作：读取文件
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();
        boolean successed = FileOperation.readFile("./resources/text/pride-and-prejudice.txt", words);
        if (!successed) {
            System.out.println("Read failed");
            return;
        }
        System.out.println("Total words: " + words.size());

        BSTMap<String, Integer> map1 = new BSTMap<String, Integer>();
        LinkedListMap<String, Integer> map2 = new LinkedListMap<String, Integer>();

        double t1 = testMap(words, map1);
        double t2 = testMap(words, map2);

        System.out.println("t1 = " + t1 + " t2 = " + t2);
        //Pride and Prejudice
        //Total words: 125901
        //Total different words: 6530
        //Frequency of word [pride]: 53
        //Total different words: 6530
        //Frequency of word [pride]: 53
        //t1 = 0.143156641 t2 = 17.236708858
    }

    private static double testMap(ArrayList<String> words, Map<String, Integer> map) {
        long startTime = System.nanoTime();

        // 测试Set添加操作
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.add(word, 1);
            }
        }
        System.out.println("Total different words: " + map.size());
        System.out.println("Frequency of word [pride]: " + map.get("pride"));

        long stopTime = System.nanoTime();

        return (stopTime - startTime) / 1000000000.0;   // 返回s数
    }
}
