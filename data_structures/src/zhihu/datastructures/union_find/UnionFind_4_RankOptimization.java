package zhihu.datastructures.union_find;

/**
 * Author: zhihu
 * Description: 并查集的简单实现-基于对应元素所在根的集合的树的层数进行优化,实现了Quick Find和Quick Union
 * Quick Find的时间复杂度为O(h),h为树的高度
 * Quick Union的时间复杂度为O(h),h为树的高度
 * Date: Create in 2019/1/27 22:32
 */
public class UnionFind_4_RankOptimization implements IUnionFind {
    
    private int[] rank;     // rank[i]表示以i为根的集合所表示的树的层数
    private int[] parent;   // parent[i]表示第i个元素所指向的父节点
    
    // 构造函数
    public UnionFind_4_RankOptimization(int size) {
        parent = new int[size];
        rank = new int[size];
        
        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    
    @Override
    public int getSize() {
        return parent.length;
    }
    
    // 查找过程, 查找元素element所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find(int element){
        if(element < 0 || element >= parent.length)
            throw new IllegalArgumentException("element is out of bound.");
        
        // 不断去查询自己的父亲节点, 直到到达根节点
        // 根节点的特点: parent[element] == element
        while(element != parent[element])
            element = parent[element];
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
    
        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank高的集合合并到rank低的集合上
        if (rank[element1Root] < rank[element2Root]) {
            parent[element1Root] = element2Root;
        } else if (rank[element2Root] < rank[element1Root]) {
            parent[element2Root] = element1Root;
        } else {
            parent[element1Root] = element2Root;
            rank[element2Root] += 1;
        }
    }
    
    // 查看元素element1和元素element2是否所属一个集合
    // O(h)复杂度, h为树的高度
    @Override
    public boolean isConnected(int element1, int element2) {
        return find(element1) == element2;
    }
}
