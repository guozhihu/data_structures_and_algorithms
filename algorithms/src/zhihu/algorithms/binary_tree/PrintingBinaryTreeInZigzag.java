package zhihu.algorithms.binary_tree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Author: zhihu
 * Description: 按之字形顺序打印二叉树
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二
 * 层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 * Date: Create in 2019/4/9 17:41
 */
public class PrintingBinaryTreeInZigzag {
    
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        
        public TreeNode(int val) {
            this.val = val;
        }
    }
    
    /**
     * 算法思路:
     * 按之字形顺序打印二叉树需要两个栈。
     * 我们在打印某一行结点时，把下一层的子结点保存到相应的栈里。
     *    如果当前打印的是奇数层，则先保存左子结点再保存右子结点到一个栈里；
     *    如果当前打印的是偶数层，则先保存右子结点再保存左子结点到第二个栈里。
     * @param pRoot
     * @return
     */
    public static ArrayList<ArrayList<Integer>> printingBinaryTreeInZigzag(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (null == pRoot) {
            return result;
        }
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        // 保存的是当前层要打印的节点
        Stack<TreeNode> currentStack = new Stack<TreeNode>();
        // 用于保存下一层要打印的节点
        Stack<TreeNode> nextStack = new Stack<TreeNode>();
        
        currentStack.push(pRoot);
        
        boolean flag = true; // true表示当前打印的是奇数层，false表示当前打印的是偶数层
        while (!currentStack.isEmpty()) {
            pRoot = currentStack.pop();
            list.add(pRoot.val);
            if (flag) {
                // 将该层节点从左往右添加进栈，遍历时会将该层节点从右向左打印
                if (null != pRoot.left) {
                    nextStack.push(pRoot.left);
                }
                if (null != pRoot.right) {
                    nextStack.push(pRoot.right);
                }
            } else {
                // 将该层节点从右往左添加进栈，遍历时会将该层节点从左向右打印
                if (null != pRoot.right) {
                    nextStack.push(pRoot.right);
                }
                if (null != pRoot.left) {
                    nextStack.push(pRoot.left);
                }
            }
            
            // 如果currentStack为空，表示已经打印完某一层节点，接下来要打印下一层节点
            if (currentStack.isEmpty()) {
                // 如果当前打印的是奇数层，则下次打印的就是偶数层；如果当前打印的是偶数层，则下次打印的是奇数层
                flag = !flag;
                Stack<TreeNode> tmp = currentStack;
                // 将下一层要打印的节点赋值给当前层
                currentStack = nextStack;
                nextStack = tmp;
                // 保存当前层的节点打印结果
                result.add(list);
                // 创建list保存下一层节点打印的结果
                list = new ArrayList<Integer>();
            }
        }
        return result;
    }
}
