package zhihu.datastructures.map;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/2/19 17:09
 */
public interface Map<K, V> {
    
    void add(K key, V value);
    
    boolean contains(K key);
    
    V get(K key);
    
    void set(K key, V newValue);
    
    V remove(K key);
    
    int getSize();
    
    boolean isEmpty();
}
