package zhihu.algorithms.binary_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Author: zhihu
 * Description: 实现二叉树的先序、中序、后序、层序遍历，包括递归方式和非递归方式
 * 二叉树的前序、中序、后序遍历属于深度优先遍历，层序遍历属于广度优先遍历
 * Date: Create in 2019/2/21 17:37
 */
public class TreeTraversal {
    
    // 二叉树节点
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        
        public Node(int value) {
            this.value = value;
        }
    }
    
    // 前序遍历---递归方式
    public static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }
    
    // 中序遍历---递归方式
    public static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }
    
    // 后序遍历---递归方式
    public static void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }
    
    // 前序遍历---非递归方式
    public static void preOrderUnRecur(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value + " ");
                if (null != head.right) {
                    stack.push(head.right);
                }
                if (null != head.left) {
                    stack.push(head.left);
                }
            }
        }
    }
    
    // 中序遍历---非递归方式
    public static void inOrderUnRecur(Node head) {
        if (null != head) {
            Stack<Node> stack = new Stack<Node>();
            while (null != head || !stack.isEmpty()) {
                if (null != head) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
    }
    
    // 后序遍历---非递归方式
    public static void posOrderUnRecur(Node head) {
        if (null != head) {
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);
                if (null != head.left) {
                    s1.push(head.left);
                }
                if (null != head.right) {
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
    }
    
    /**
     * 层序遍历的顺序为先打印根节点，再打印左子树，之后再打印右子树
     * 将头节点放入队列中，再从队列中获取该节点，并将该节点从队列中移除，然后将该节点的左孩子存入队列，再将右孩子存入队列
     * @param head
     */
    // 层序遍历
    public static void levelOrder(Node head) {
        if (null != head) {
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(head);
            while (!queue.isEmpty()) {
                head = queue.remove();
                System.out.print(head.value + " ");
                if (null != head.left) {
                    queue.add(head.left);
                }
                if (null != head.right) {
                    queue.add(head.right);
                }
            }
        }
    }

}
