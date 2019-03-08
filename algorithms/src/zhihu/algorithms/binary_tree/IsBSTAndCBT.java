package zhihu.algorithms.binary_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Author: zhihu
 * Description: 判断一棵树是否是搜索二叉树、判断一棵树是否是完全二叉树
 * Date: Create in 2019/3/6 22:25
 */
public class IsBSTAndCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        
        public Node(int data) {
            this.value = data;
        }
    }
    
    // 判断一棵树是否是搜索二叉树
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        boolean res = true;
        Node pre = null;
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            if (pre != null && pre.value > cur1.value) {
                res = false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }
        return res;
    }
    
    // 判断一棵树是否是搜索二叉树，采用中序遍历的方式
    // 如果当前二叉树为搜索二叉树，则中序遍历后，元素之间是排完序的。
    public static boolean isBSTByInOrder(Node head){
        if (null != head) {
            Stack<Node> stack = new Stack<Node>();
            int pre = Integer.MIN_VALUE;
            while (!stack.isEmpty() || null != head) {
                if (null != head) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    if (head.value < pre) {
                        return false;
                    }
                    pre = head.value;
                    head = head.right;
                }
            }
        }
        return true;
    }
    
    // 判断一棵树是否是完全二叉树
    // 判断方法：
    // 1.如果一个节点有右孩子而没有左孩子，这棵树绝对不是完全二叉树
    // 2.或者如下这种情况，虽然没有出现上面这种情况，但出现下面这种情况，所以
    // 在层序遍历中，如果遍历到②节点这种情况（即当前节点的左节点不为空而右节点
    // 为空或者左节点为空且右节点也为空），则从②节点开始，后面遍历到的节点都应
    // 该是叶节点，而不应该存在③这种非叶子节点
    //              ①             ①
    //             / \           / \
    //            ②   ③   或者   ②  ③
    //           /   /             /
    //          ④   ⑤             ⑤
    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<Node>();
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((leaf && (l != null || r != null))  // 出现第2种情况
                || (l == null && r != null)) {      // 出现第1种情况
                return false;
            }
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            } else { // 如果右节点为空，则后面的节点都应该是叶子节点，而不能存在非叶子节点
                leaf = true;
            }
        }
        return true;
    }
    
    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }
    
    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }
    
    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }
    
    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        
        printTree(head);
        System.out.println(isBST(head));
        System.out.println(isBSTByInOrder(head));
        System.out.println(isCBT(head));
        
    }
}
