package zhihu.algorithms.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhihu
 * Description: 树的子结构
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * Date: Create in 2019/4/12 18:54
 */
public class HasSubtree {
    
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
     * 要查找树A是否存在和树B结构一样的子树，我们可以分成两步：
     * 第一步在树A中找到和树B的根节点的值一样的节点R,
     * 第二步再判断树A中以R为根节点的子树是不是包含和树B一样的结构。
     *
     * @param root1
     * @param root2
     * @return
     */
    public static boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null) return false;
        if (root1 == null && root2 != null) return false;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root1);
        while (!queue.isEmpty()) {
            root1 = queue.poll();
            if (root1.val == root2.val) {
                if (isSubTree(root1, root2)) {
                    return true;
                }
            }
            if (null != root1.left) {
                queue.add(root1.left);
            }
            if (null != root1.right) {
                queue.add(root1.right);
            }
            System.out.println("Hello World !");
        }
        return false;
    }
    
    private static boolean isSubTree(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null && root2 != null) return false;
        if (root1.val == root2.val) {
            return isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right);
        } else {
            return false;
        }
    }
    
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(8);
        root1.left = new TreeNode(8);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(9);
        root1.left.right = new TreeNode(2);
        
        TreeNode root2 = new TreeNode(8);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(2);
        
        System.out.println(HasSubtree(root1, root2));
    }
}
