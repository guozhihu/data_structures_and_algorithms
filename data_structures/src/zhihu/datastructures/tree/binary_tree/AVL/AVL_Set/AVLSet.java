package zhihu.datastructures.tree.binary_tree.AVL.AVL_Set;

import zhihu.datastructures.set.Set;
import zhihu.datastructures.tree.binary_tree.AVL.AVL_Tree.AVLTree;

/**
 * Author: zhihu
 * Description: 基于平衡二叉树实现的Set集合
 * Date: Create in 2019/2/19 17:13
 */
public class AVLSet<E extends Comparable<E>> implements Set<E> {
    
    private AVLTree<E, Object> avl;
    
    public AVLSet() {
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
    public void add(E e) {
        avl.add(e, null);
    }
    
    @Override
    public boolean contains(E e) {
        return avl.contains(e);
    }
    
    @Override
    public void remove(E e) {
        avl.remove(e);
    }
}
