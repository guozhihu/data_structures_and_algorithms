package zhihu.algorithms.union_find;

import java.util.TreeSet;

/**
 * Author: zhihu
 * Description: 朋友圈
 * 班上有N名学生。他们中的一些人是朋友，而有些则不是。他们的友谊本质上是传递性的。例如，如果A是B的直接朋友，
 * 而B是C的直接朋友，那么A是C的间接朋友。我们定义的朋友圈是一群直接或间接的朋友。
 * <p>
 * 给定N * N的矩阵M表示班级中学生之间的朋友关系。如果M[i][j] = 1，那么第i和第j个学生是彼此的直接朋友，否则
 * 不是。而且你必须在所有学生中输出朋友圈的总数。
 * <p>
 * 例1：
 * 输入：
 * [[1,1,0]，
 *  [1,1,0]
 *  [0,0,1]
 * 输出：2
 * 说明：第0和第1名学生是直接的朋友，所以他们在朋友圈。
 * 第二个学生本人就在朋友圈里。所以返回2。
 * 例2：
 * 输入：
 * [[1,1,0]，
 *  [1,1,1]，
 *  [0,1,1]
 * 输出：1
 * 说明：第0和第1名学生是直接的朋友，第1和第2名学生是直接的朋友，
 * 所以第0和第2名学生是间接的朋友。所有人都在同一个朋友圈，所以返回1。
 * 注意：
 * N在[1,200]范围内。
 * 所有学生的M[i][i] = 1。
 * 如果M[i][j] = 1，则M[j][i] = 1。
 * Date: Create in 2019/1/28 0:25
 * 难度：Medium
 */
public class FriendCirclesSolution {
    
    private interface IUnionFind {
        int getSize();
        
        boolean isConnected(int element1, int element2);
        
        void unionElements(int p, int q);
    }
    
    private class UnionFind implements IUnionFind {
        
        private int[] rank;
        private int[] parent; // parent[i]表示第i个元素所指向的父节点
        
        // 构造函数
        public UnionFind(int size) {
            
            rank = new int[size];
            parent = new int[size];
            
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
            if (element < 0 || element >= parent.length)
                throw new IllegalArgumentException("element is out of bound.");
            
            // path compression 2, 递归算法
            if (element != parent[element])
                parent[element] = find(parent[element]);
            return parent[element];
        }
        
        // 查看元素element1和元素element2是否所属一个集合
        // O(h)复杂度, h为树的高度
        @Override
        public boolean isConnected(int element1, int element2) {
            return find(element1) == find(element2);
        }
        
        // 合并元素element1和元素element2所属的集合
        // O(h)复杂度, h为树的高度
        @Override
        public void unionElements(int element1, int element2) {
            
            int element1Root = find(element1);
            int element2Root = find(element2);
            
            if (element1Root == element2Root)
                return;
            
            // 根据两个元素所在树的rank不同判断合并方向
            // 将rank低的集合合并到rank高的集合上
            if (rank[element1Root] < rank[element2Root])
                parent[element1Root] = element2Root;
            else if (rank[element2Root] < rank[element1Root])
                parent[element2Root] = element1Root;
            else { // rank[pRoot] == rank[qRoot]
                parent[element1Root] = element2Root;
                rank[element2Root] += 1;
            }
        }
    }
    
    public int findCircleNum(int[][] M) {
        
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < i; j++)
                if (M[i][j] == 1)
                    uf.unionElements(i, j);
        
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++)
            set.add(uf.find(i));
        return set.size();
    }
}
