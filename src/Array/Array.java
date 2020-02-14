package Array;

public class Array<E> {
// 泛型

    private E[] data;
    private int size;
    private int resizeFactor = 2;   // 扩容因子

    // 构造函数
    public Array(int capacity) {
        data = (E[])new Object[capacity];
        size = 0;
    }

    // 无参数的默认构造函数
    public Array() {
        this(10);
    }

    // 获取实际数据量
    public int size() {
        return size;
    }

    // 获取数组容量
    public int capacity() {
        return data.length;
    }

    // 是否为空
    public boolean isEmpty() {
        return size==0;
    }

    // 尾部插入元素 O(1)
    public void addLast(E e) {
        add(size, e);
    }

    // 头部插入元素 O(n)
    public void addFirst(E e) {
        add(0, e);
    }

    // 按索引插入元素 O(n)
    public void add(int idx, E e) {
        if (idx < 0 || idx > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and <= size");
        }
        if (size == data.length) {
            resize(resizeFactor * data.length);
        }

//        for (int i = size-1; i>=idx; i--) {
//            data[i+1] = data[i];
//        }
        System.arraycopy(data, idx, data, idx + 1, size - idx);
        data[idx] = e;
        size++;
    }

    // 查询数据 O(1)
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Require index >= 0 and < size");
        }
        return data[index];
    }

    // 查询数组第一个元素
    public E first() {
        return get(0);
    }

    // 查询数组最后一个元素
    public E last() {
        return get(size-1);
    }

    // 更新数据 O(1)
    public void set(int index, E newe) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Require index >= 0 and < size");
        }
        data[index] = newe;
    }

    // 查找数组是否包含有元素e O(n)
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    // 查找数组中元素e的下标 O(n)
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // 删除指定索引的数据，并返回该值 O(1)
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Require index >= 0 and < size");
        }
        E ret = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        data[size] = null; // 可选，手动去除原先最后一个元素的引用，进行垃圾回收

        // 是否缩容
        // 当size=cap/4时进行缩绒，而不是size=cap/2时就缩容，避免复杂度过度震荡
        if (size == data.length / (2 * resizeFactor) && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return ret;
    }

    // 删除末尾数据 O(1)
    public E removeLast() {
        return remove(size-1);
    }

    // 删除头部数据 O(n)
    public E removeFirst() {
        return remove(0);
    }

    // 删除指定元素 O(n)
    public void removeElem(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    // 数组扩容 O(n)
    private void resize(int newcap) {
        E[] newdata = (E[])new Object[newcap];
        System.arraycopy(data, 0, newdata, 0, size);
        data = newdata;
    }

    // 转为字符串打印
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }


    public static void main(String[] args) {
        // Java会自动将基本数据类型(int,char,...)转换为包装类型(Integer,Char,...)
        Array<Integer> arr = new Array<Integer>();
        for (int i = 0; i < 10; i++) {
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
