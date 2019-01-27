package zhihu.datastructures.union_find;

/**
 * Author: zhihu
 * Description: 并查集的简单实现-实现了Quick Find,但Union却很慢
 * Quick Find的时间复杂度为O(1)
 * Quick Find下的Union的时间复杂度为O(n)
 * Date: Create in 2019/1/14 23:42
 */
public class UnionFind_1_QuickFind implements IUnionFind {
    
    private int[] id;
    
    public UnionFind_1_QuickFind(int size) {
        id = new int[size];
        // 等同于创建了size个集合
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }
    
    // 查找元素element所对应的集合编号
    // O(1)复杂度
    private int find(int element) {
        if (element < 0 || element >= id.length) {
            throw new IllegalArgumentException("element is out of bound.");
        }
        return id[element];
    }
    
    @Override
    public int getSize() {
        return id.length;
    }
    
    // 合并元素element1和元素element2所属的集合
    // O(n) 复杂度
    @Override
    public void union(int element1, int element2) {
        int element1ID = find(element1);
        int element2ID = find(element2);
        if (element1ID == element2ID) {
            return;
        }
        
        // 合并过程需要遍历一遍所有元素，将两个元素的所属集合编号合并
        for (int i = 0; i < id.length; i++) {
            if (id[i] == element1ID) {
                id[i] = element2ID;
            }
        }
    }
    
    // 查看元素element1和元素element2是否所属一个集合
    // O(1)复杂度
    @Override
    public boolean isConnected(int element1, int element2) {
        return find(element1) == find(element2);
    }
}
