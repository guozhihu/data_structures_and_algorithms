package zhihu.datastructures.set;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/2/19 17:12
 */
public interface Set<E> {
    void add(E e);
    
    boolean contains(E e);
    
    void remove(E e);
    
    int getSize();
    
    boolean isEmpty();
}
