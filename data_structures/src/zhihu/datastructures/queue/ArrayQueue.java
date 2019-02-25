package zhihu.datastructures.queue;

import zhihu.datastructures.array.Array;

/**
 * Author: zhihu
 * Description: 基于动态数组实现的队列
 * Date: Create in 2019/2/25 11:58
 */
public class ArrayQueue<E> implements Queue<E> {
    
    private Array<E> array;
    
    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }
    
    public ArrayQueue() {
        array = new Array<>();
    }
    
    @Override
    public int getSize() {
        return array.getSize();
    }
    
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }
    
    public int getCapacity() {
        return array.getCapacity();
    }
    
    // 入队，时间复杂度为O(1)
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }
    
    // 出队，时间复杂度为O(n)
    @Override
    public E dequeue() {
        return array.removeFirst();
    }
    
    // 获得队首元素但不删除，时间复杂度为O(1)
    @Override
    public E getFront() {
        return array.getFirst();
    }
    
    @Override
    public String toString() {
        
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }
    
}
