package zhihu.datastructures.heap;

import zhihu.datastructures.array.Array;

/**
 * Author: zhihu
 * Description: 二叉堆实现(最大堆)
 * Date: Create in 2019/2/23 23:25
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;
    
    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }
    
    public MaxHeap() {
        data = new Array<>();
    }
    // 这里采用heapify将一个数组转换成最大堆，算法复杂度为O(n),如果采用将数组中的元素依次添加到一个空堆中，其算法复杂度为O(nlogn)
    // 如果采用向空堆中插入元素，则对于每个元素都要执行一次上升操作
    // 而对于heapify则可以不用管叶子节点，直接从非叶子节点开始执行一次下降操作即可
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        // 从第一个非叶子节点开始进行下降操作
        for (int i = parent(arr.length - 1); i >= 0; i--)
            siftDown(i);
    }
    
    // 返回堆中的元素个数
    public int size() {
        return data.getSize();
    }
    
    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }
    
    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }
    
    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }
    
    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }
    
    // 向堆中添加元素
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }
    
    private void siftUp(int k) {
        
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }
    
    // 看堆中的最大元素
    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }
    
    // 取出堆中最大元素
    public E extractMax() {
        
        E ret = findMax();
        
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        
        return ret;
    }
    
    // 下降操作，时间复杂度为O(logn)
    private void siftDown(int k) {
        
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k); // 在此轮循环中,data[k]和data[j]交换位置
            if (j + 1 < data.getSize() &&
                data.get(j + 1).compareTo(data.get(j)) > 0)
                j++;
            // data[j] 是 leftChild 和 rightChild 中的最大值
            
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;
            
            data.swap(k, j);
            k = j;
        }
    }
    
    // 取出堆中的最大元素，并且替换成元素e
    // 这里有两种实现方式：
    // 第一种：可以先extractMax，再add，两次O(logn)的操作
    // 第二种：可以直接将堆顶元素替换以后siftDown，一次O(logn)的操作
    public E replace(E e) {
        
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }
}
