package zhihu.datastructures.set.BST_Set;

import zhihu.datastructures.set.Set;
import zhihu.datastructures.tree.binary_tree.binary_search_tree.BST;

/**
 * Author: zhihu
 * Description: 基于二分搜索树实现的集合
 * Date: Create in 2019/2/22 13:15
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {
    
    private BST<E> bst;
    
    public BSTSet() {
        bst = new BST<>();
    }
    
    @Override
    public int getSize() {
        return bst.size();
    }
    
    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
    
    @Override
    public void add(E e) {
        bst.add(e);
    }
    
    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }
    
    @Override
    public void remove(E e) {
        bst.remove(e);
    }
}
