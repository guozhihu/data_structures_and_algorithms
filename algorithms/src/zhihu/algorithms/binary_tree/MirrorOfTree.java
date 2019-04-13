package zhihu.algorithms.binary_tree;

import java.util.Stack;

/*
 * Author: zhihu
 * Description: 二叉树的镜像
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 输入描述:
 * 二叉树的镜像定义：源二叉树
 *     	    8
 *     	   /  \
 *     	  6   10
 *     	 / \  / \
 *     	5  7 9 11
 *     	镜像二叉树
 *     	    8
 *     	   /  \
 *     	  10   6
 *     	 / \  / \
 *     	11 9 7  5
 * Date: Create in 2019/4/13 15:23
 */
public class MirrorOfTree {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        
        public TreeNode(int val) {
            this.val = val;
            
        }
    }
    
    /**
     * 算法思路
     * 先前序遍历这棵树的每个结点，如果遍历到的结点有子结点，就交换它的两个子结点。
     * 当交换完所有非叶子结点的左右子结点之后，就得到了树的镜像。
     *
     * @param root
     */
    public static void mirror(TreeNode root) {
        if (null != root) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            mirror(root.left);
            mirror(root.right);
        }
    }
    
    public static void mirrorUnRecur(TreeNode root) {
        if (null != root) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                TreeNode tmp = root.left;
                root.left = root.right;
                root.right = tmp;
                if (null != root.right) {
                    stack.push(root.right);
                }
                if (null != root.left) {
                    stack.push(root.left);
                }
            }
        }
    }
}
