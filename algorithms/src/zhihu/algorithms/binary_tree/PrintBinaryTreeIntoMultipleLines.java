package zhihu.algorithms.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhihu
 * Description: 把二叉树打印成多行
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 * Date: Create in 2019/4/9 16:08
 */
public class PrintBinaryTreeIntoMultipleLines {
    
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        
        public TreeNode(int val) {
            this.val = val;
        }
    }
    
    /**
     * 算法思路：
     * 利用层次遍历的算法，设置变量current表示当前层还没有打印的节点数，设置变量next表示下一层结点的数目。
     *
     * @param pRoot
     * @return
     */
    public static ArrayList<ArrayList<Integer>> printBinaryTreeIntoMultipleLines(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> listArr = new ArrayList<ArrayList<Integer>>();
        if (null == pRoot) {
            return listArr;
        }
        // 用于记录当前层的节点个数
        int current = 1;
        // 用于记录下一层的节点个数；
        int next = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            pRoot = queue.poll();
            current--;
            list.add(pRoot.val);
            if (null != pRoot.left) {
                queue.offer(pRoot.left);
                next++;
            }
            if (null != pRoot.right) {
                queue.offer(pRoot.right);
                next++;
            }
            if (current == 0) {
                listArr.add(list);
                current = next;
                next = 0;
                list = new ArrayList<Integer>();
            }
        }
        return listArr;
    }
}
