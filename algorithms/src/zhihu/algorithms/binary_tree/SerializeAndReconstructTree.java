package zhihu.algorithms.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: zhihu
 * Description: 实现两个函数，分别用来序列化和反序列化二叉树
 * 下面的代码中"#"表示空节点，"!"表示节点之间的间隔符，将两个元素分开
 * Date: Create in 2019/3/5 15:27
 */
public class SerializeAndReconstructTree {
    
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        
        public Node(int data) {
            this.value = data;
        }
    }
    
    //////////////////////////采用前序方式序列化和反序列化二叉树////////////////////////
    // 采用前序遍历的方式序列化二叉树
    public static String serialByPre(Node head) {
        if (null == head) {
            return "#!";
        }
        String str = head.value + "!";
        str += serialByPre(head.left);
        str += serialByPre(head.right);
        return str;
    }
    
    // 前序遍历的方式序列化的二叉树的反序列方法
    public static Node reconByPreString(String preStr) {
        String[] values = preStr.split("!");
        Queue<String> queue = new LinkedList<String>();
        for (String value : values) {
            queue.offer(value);
        }
        return reconPreOrder(queue);
    }
    
    private static Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node node = new Node(Integer.parseInt(value));
        node.left = reconPreOrder(queue);
        node.right = reconPreOrder(queue);
        return node;
    }
    //////////////////////////采用前序方式序列化和反序列化二叉树////////////////////////
    
    
    //////////////////////////采用层序方式序列化和反序列化二叉树////////////////////////
    // 采用层序遍历的方式序列化二叉树
    public static String serialByLevel(Node head) {
        if (null == head) {
            return "#!";
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(head);
        StringBuilder builder = new StringBuilder();
        builder.append(head.value).append("!");
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (null != head.left) {
                builder.append(head.left.value).append("!");
                queue.offer(head.left);
            } else {
                builder.append("#!");
            }
            if (null != head.right) {
                builder.append(head.right.value).append("!");
                queue.offer(head.right);
            } else {
                builder.append("#!");
            }
        }
        return builder.toString();
    }
    
    // 层序遍历的方式序列化的二叉树的反序列方法
    public static Node reconByLevelString(String levelStr) {
        String[] values = levelStr.split("!");
        int index = 0;
        Node head = generateNodeByString(values[index++]);
        Queue<Node> queue = new LinkedList<Node>();
        if (head != null) {
            queue.offer(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right = generateNodeByString(values[index++]);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;
    }
    
    private static Node generateNodeByString(String value) {
        return value.equals("#") ? null : new Node(Integer.parseInt(value));
    }
    //////////////////////////采用层序方式序列化和反序列化二叉树////////////////////////
    
    
    ///////////////////////////////测试//////////////////////////////////////////////
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
        Node head = null;
        printTree(head);
        
        String pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);
        
        String level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);
        
        System.out.println("====================================");
        
        head = new Node(1);
        printTree(head);
        
        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);
        
        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);
        
        System.out.println("====================================");
        
        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.right = new Node(5);
        printTree(head);
        
        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);
        
        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);
        
        System.out.println("====================================");
        
        head = new Node(100);
        head.left = new Node(21);
        head.left.left = new Node(37);
        head.right = new Node(-42);
        head.right.left = new Node(0);
        head.right.right = new Node(666);
        printTree(head);
        
        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);
        
        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);
        
        System.out.println("====================================");
        
    }
}
