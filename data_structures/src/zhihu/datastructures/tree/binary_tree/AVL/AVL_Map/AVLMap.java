package zhihu.datastructures.tree.binary_tree.AVL.AVL_Map;

import zhihu.datastructures.map.Map;
import zhihu.datastructures.tree.binary_tree.AVL.AVL_Tree.AVLTree;

/**
 * Author: zhihu
 * Description: 基于平衡二叉树实现的Map集合
 * Date: Create in 2019/2/19 17:09
 */
public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {
    
    private AVLTree<K, V> avl;
    
    public AVLMap() {
        avl = new AVLTree<>();
    }
    
    @Override
    public int getSize() {
        return avl.getSize();
    }
    
    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }
    
    @Override
    public void add(K key, V value) {
        avl.add(key, value);
    }
    
    @Override
    public boolean contains(K key) {
        return avl.contains(key);
    }
    
    @Override
    public V get(K key) {
        return avl.get(key);
    }
    
    @Override
    public void set(K key, V newValue) {
        avl.set(key, newValue);
    }
    
    @Override
    public V remove(K key) {
        return avl.remove(key);
    }
}
