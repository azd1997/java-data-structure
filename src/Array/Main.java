package Array;

public class Main {
    public static void main(String[] args) {
        // Java会自动将基本数据类型(int,char,...)转换为包装类型(Integer,Char,...)
        Array<Integer> arr = new Array<Integer>();
        for (int i = 0; i<10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        // [-1, 0, 100, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        arr.remove(2);
        System.out.println(arr);

        arr.removeElem(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);
    }
}
