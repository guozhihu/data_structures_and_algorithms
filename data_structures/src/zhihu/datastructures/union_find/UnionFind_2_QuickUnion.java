package zhihu.datastructures.union_find;

/**
 * Author: zhihu
 * Description: 并查集的简单实现-实现了Quick Find和Quick Union
 * Quick Find的时间复杂度为O(h),h为树的高度
 * Quick Union的时间复杂度为O(h),h为树的高度
 * Date: Create in 2019/1/27 20:47
 */
public class UnionFind_2_QuickUnion implements IUnionFind {
    
    // 使用一个数组构建一棵指向父节点的树
    // parent[i]表示第一个元素所指向的父节点
    private int[] parent;
    
    public UnionFind_2_QuickUnion(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }
    
    @Override
    public int getSize() {
        return parent.length;
    }
    
    // 查找过程, 查找元素element所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find(int element) {
        if (element < 0 || element >= parent.length) {
            throw new IllegalArgumentException("element is out of bound.");
        }
        
        // 不断去查询自己的父亲节点, 直到到达根节点
        // 根节点的特点: parent[element] == element
        while (element != parent[element]) {
            element = parent[element];
        }
        return element;
    }
    
    // 合并元素element1和元素element2所属的集合
    // O(h)复杂度, h为树的高度
    @Override
    public void union(int element1, int element2) {
        int element1Root = find(element1);
        int element2Root = find(element2);
        if (element1Root == element2Root) {
            return;
        }
        parent[element1Root] = element2Root;
    }
    
    // 查看元素element1和元素element2是否所属一个集合
    // O(h)复杂度, h为树的高度
    @Override
    public boolean isConnected(int element1, int element2) {
        return find(element1) == find(element2);
    }
}
