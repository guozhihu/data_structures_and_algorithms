package zhihu.algorithms.binary_tree;

import java.util.Stack;

/**
 * Author: zhihu
 * Description: 二叉搜索树的第k小节点
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如，
 * （5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4。
 * Date: Create in 2019/4/8 17:28
 */
public class KthMinNode {
    
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
     * 采用中序遍历，遍历的结果必定按照升序排列节点，当遍历到第k个节点时直接返回即可
     * @param pRoot
     * @param k
     * @return
     */
    public static TreeNode KthMinNode(TreeNode pRoot, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (null != pRoot || !stack.isEmpty()) {
            if (null != pRoot) {
                stack.push(pRoot);
                pRoot = pRoot.left;
            } else {
                pRoot = stack.pop();
                if (k-- == 1) {
                    return pRoot;
                }
                pRoot = pRoot.right;
            }
        }
        return null;
    }
}
