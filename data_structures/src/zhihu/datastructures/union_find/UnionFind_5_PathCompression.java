package zhihu.datastructures.union_find;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/1/27 23:30
 */
public class UnionFind_5_PathCompression implements IUnionFind {
    
    // rank[i]表示以i为根的集合所表示的树的层数
    // 在后续的代码中, 我们并不会维护rank的语意, 也就是rank的值在路径压缩的过程中, 有可能不在是树的层数值
    // 这也是我们的rank不叫height或者depth的原因, 他只是作为比较的一个标准
    private int[] rank;     // rank[i]表示以i为根的集合所表示的树的层数
    private int[] parent;   // parent[i]表示第i个元素所指向的父节点
    
    public UnionFind_5_PathCompression(int size) {
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
    private int find(int element) {
        if (element < 0 || element >= parent.length) {
            throw new IllegalArgumentException("element is out of bound.");
        }
        
        // 路径压缩
        while (element != parent[element]) {
            parent[element] = parent[parent[element]];
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
        return find(element1) == find(element2);
    }
}
