package zhihu.datastructures.queue;

/**
 * Author: zhihu
 * Description: 队列的规范接口
 * Date: Create in 2019/2/23 23:35
 */
public interface Queue<E> {
    
    int getSize();
    
    boolean isEmpty();
    
    void enqueue(E e);
    
    E dequeue();
    
    E getFront();
}
