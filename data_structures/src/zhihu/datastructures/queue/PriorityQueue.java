package zhihu.datastructures.queue;

import zhihu.datastructures.heap.MaxHeap;

/**
 * Author: zhihu
 * Description: 优先级队列实现(基于最大堆实现)
 * Date: Create in 2019/2/23 23:37
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private MaxHeap<E> maxHeap;
    
    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }
    
    @Override
    public int getSize() {
        return maxHeap.size();
    }
    
    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
    
    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
    
    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }
    
    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }
}
