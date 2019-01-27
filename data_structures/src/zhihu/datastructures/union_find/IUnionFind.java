package zhihu.datastructures.union_find;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/1/14 23:49
 */
public interface IUnionFind {
    // 获得并查集中的元素个数
    public int getSize();
    
    // 合并并查集中元素，即将element1所在集合与element2所在集合合并
    public void union(int element1, int element2);
    
    // 查看并查集中的两个元素是否连接，即是否处在同一个集合中
    public boolean isConnected(int element1, int element2);
}
