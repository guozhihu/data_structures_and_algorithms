package zhihu.algorithms.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhihu
 * Description: 对称的二叉树
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 * Date: Create in 2019/4/9 21:20
 */
public class SymmetricBinaryTree {
    
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        
        public TreeNode(int val) {
            this.val = val;
        }
    }
    
    /**
     * 算法思路1：
     * 复制要进行判断的这棵树，即复制pRoot为根节点的树，即设置pRootSelf = pRoot;
     * 利用层序遍历的方式，pRoot按照每一层从左到右的顺序打印，pRootSelf按照每层从右到左的顺序打印
     * 打印过程中，如果pRoot与pRootSelf不对称，即发生下列几种情况，就返回false
     * 1.pRoot的左节点与pRootSelf的右节点不同时为空或不同时为不空
     * 2.pRoot的右节点与pRootSelf的左节点不同时为空或不同时为不空
     * 3.pRoot的值val与pRootSelf的值val不相等
     *
     * @param pRoot
     * @return
     */
    public static boolean isSymmetrical(TreeNode pRoot) {
        if (null == pRoot) {
            return true;
        }
        // 该二叉树的镜像
        TreeNode pRootSelf = pRoot;
        
        // 创建一个pRoot树队列按照每层从左往右的顺序保存pRoot树的节点
        Queue<TreeNode> pRootQueue = new LinkedList<TreeNode>();
        // 创建一个pRootSelf树队列按照每层从右往左的顺序保存pRootSelf树的节点
        Queue<TreeNode> pRootSelfQueue = new LinkedList<TreeNode>();
        
        pRootQueue.add(pRoot);
        pRootSelfQueue.add(pRootSelf);
        
        while (!pRootQueue.isEmpty()) {
            pRoot = pRootQueue.poll();
            pRootSelf = pRootSelfQueue.poll();
            
            // 如果当前pRoot和pRootSelf不对称，返回false
            if (!IsSymmetry(pRoot, pRootSelf) || (pRoot.val != pRootSelf.val)) {
                return false;
            }
            
            if (null != pRoot.left) {
                pRootQueue.add(pRoot.left);
                pRootSelfQueue.add(pRootSelf.right);
            }
            if (null != pRoot.right) {
                pRootQueue.add(pRoot.right);
                pRootSelfQueue.add(pRootSelf.left);
            }
        }
        return true;
    }
    
    private static boolean IsSymmetry(TreeNode pRoot, TreeNode pRootSelf) {
        boolean pRootLeft = pRoot.left == null;
        boolean pRootRight = pRoot.right == null;
        boolean pRootSelfLeft = pRootSelf.left == null;
        boolean pRootSelfRight = pRootSelf.right == null;
        return (pRootLeft == pRootSelfRight) && (pRootRight == pRootSelfLeft);
    }
    
    /**
     * 算法思路2:
     * 通过比较二叉树的前序遍历序列和对称前序遍历序列来判断二叉树是不是对称的。如果两个序列一样，那么二叉树就是对称的。
     * 二叉树前序遍历按正常的前序遍历遍历，即先遍历左树再遍历右树
     * 二叉树对称前序遍历按照先遍历右树再遍历左树
     * @param pRoot
     * @return
     */
    public static boolean isSymmetrical_Pre(TreeNode pRoot) {
        return isSymmetrical(pRoot, pRoot);
    }
    
    private static boolean isSymmetrical(TreeNode left, TreeNode right) {
        boolean leftNull = left == null;
        boolean rightNull = right == null;
        if (leftNull && rightNull) {
            return true;
        }
        if (leftNull != rightNull || left.val != right.val) {
            return false;
        }
        return isSymmetrical(left.left, right.right) && isSymmetrical(left.right, right.left);
    }
}
