package zhihu.algorithms.binary_tree;

/**
 * Author: zhihu
 * Description: 在二叉树中找到一个节点的后继节点
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 * Date: Create in 2019/3/4 22:54
 */
public class SuccessorNode {
    
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;
        
        public Node(int data) {
            this.value = data;
        }
    }
    
    /**
     * 算法思路：如果当前节点有右子树，则当前节点的后继节点即为该右子树的最左节点；如果当前节点没有右子树，则向上找父节点node.parent，
     * 如果node.parent的父节点node.parent.parent的左节点为node.parent，则node.parent节点即为当前节点的后继节点。如果不断向上找到的父节点为空，
     * 说明当前的node节点为整个树的最右节点，则不存在该node节点的后继节点。
     *
     * @param node
     * @return
     */
    public static Node getSuccessorNode(Node node) {
        if (null == node) {
            return node;
        }
        // 当前节点有右子树
        if (null != node.right) { // 右子树不为空
            return getMostLeft(node.right);
        } else {
            Node parent = node.parent;
            while (null != parent && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }
    
    // 获得以该节点为根的树的最左节点
    private static Node getMostLeft(Node node) {
        while (null != node.left) {
            node = node.left;
        }
        return node;
    }
    
    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;
        
        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }
}
