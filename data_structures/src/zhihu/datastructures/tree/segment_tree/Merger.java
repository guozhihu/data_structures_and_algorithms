package zhihu.datastructures.tree.segment_tree;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/2/24 14:33
 */
public interface Merger<E> {
    E merge(E a, E b);
}
