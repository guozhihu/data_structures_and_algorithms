package zhihu.datastructures.stack;

/**
 * Author: zhihu
 * Description: 栈的接口定义
 * Date: Create in 2019/2/25 11:34
 */
public interface Stack<E> {
    int getSize();
    
    boolean isEmpty();
    
    void push(E e);
    
    E pop();
    
    E peek();
}
