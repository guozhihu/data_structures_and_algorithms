package zhihu.datastructures.tree.binary_tree.binary_search_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Author: zhihu
 * Description: 二分搜索树
 * Date: Create in 2019/2/18 11:09
 */
public class BST<E extends Comparable<E>> {
    
    private class Node {
        public E e;
        public Node left, right;
        
        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }
    
    private Node root;
    private int size;
    
    public BST() {
        root = null;
        size = 0;
    }
    
    // 获取二叉树的节点个数
    public int size() {
        return size;
    }
    
    // 判断该二叉树是否为空
    public boolean isEmpty() {
        return size == 0;
    }
    
    // 向二分搜索树中添加新的元素e
    public void add(E e) {
        root = add(root, e);
    }
    
    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        
        if (e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);
        
        return node;
    }
    
    // 看二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }
    
    // 看以node为根的二分搜索树中是否包含元素e, 递归算法
    private boolean contains(Node node, E e) {
        if (node == null)
            return false;
        
        if (e.compareTo(node.e) == 0)
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else // e.compareTo(node.e) > 0
            return contains(node.right, e);
    }
    
    /**
     * 二叉树的前序、中序、后序遍历属于深度优先遍历，层序遍历属于广度优先遍历
     */
    // 二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }
    
    // 前序遍历以node为根的二分搜索树, 递归算法
    private void preOrder(Node node) {
        if (node == null)
            return;
        
        System.out.print(node.e + " ");
        preOrder(node.left);
        preOrder(node.right);
    }
    
    // 二分搜索树的非递归前序遍历
    public void preOrderNR() {
        if (null != root) {
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node cur = stack.pop();
                System.out.print(cur.e + " ");
                if (null != cur.right)
                    stack.push(cur.right);
                if (null != cur.left)
                    stack.push(cur.left);
            }
        }
    }
    
    // 二分搜索树的中序遍历
    public void inOrder() {
        inOrder(root);
    }
    
    // 中序遍历以node为根的二分搜索树, 递归算法
    private void inOrder(Node node) {
        if (node == null)
            return;
        
        inOrder(node.left);
        System.out.print(node.e + " ");
        inOrder(node.right);
    }
    
    // 二分搜索树的非递归中序遍历
    public void inOrderNR() {
        if (null != root) {
            Stack<Node> stack = new Stack<>();
            Node cur = root;
            while (null != cur || !stack.isEmpty()) {
                if (null != cur) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    cur = stack.pop();
                    System.out.print(cur.e + " ");
                    cur = cur.right;
                }
            }
        }
    }
    
    // 二分搜索树的后序遍历
    public void postOrder() {
        postOrder(root);
    }
    
    // 后序遍历以node为根的二分搜索树, 递归算法
    private void postOrder(Node node) {
        
        if (node == null)
            return;
        
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e + " ");
    }
    
    // 二分搜索树的非递归后序遍历
    public void postOrderNR() {
        if (null != root) {
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(root);
            while (!s1.isEmpty()) {
                Node cur = s1.pop();
                s2.push(cur);
                if (null != cur.left) {
                    s1.push(cur.left);
                }
                if (null != cur.right) {
                    s1.push(cur.right);
                }
            }
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().e + " ");
            }
        }
    }
    
    // 二分搜索树的层序遍历
    public void levelOrder() {
        
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.print(cur.e + " ");
            
            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
    }
    
    // 寻找二分搜索树的最小元素
    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");
        
        return minimum(root).e;
    }
    
    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }
    
    // 寻找二分搜索树的最大元素
    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        
        return maximum(root).e;
    }
    
    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node maximum(Node node) {
        if (node.right == null)
            return node;
        
        return maximum(node.right);
    }
    
    // 从二分搜索树中删除最小值所在节点, 返回最小值
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }
    
    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        
        node.left = removeMin(node.left);
        return node;
    }
    
    // 从二分搜索树中删除最大值所在节点
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }
    
    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {
        
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        
        node.right = removeMax(node.right);
        return node;
    }
    
    // 从二分搜索树中删除元素为e的节点
    public void remove(E e) {
        root = remove(root, e);
    }
    
    // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {
        
        if (node == null)
            return null;
        
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {   // e.compareTo(node.e) == 0
            
            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            
            // 待删除节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            
            // 待删除节点左右子树均不为空的情况
            
            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            
            node.left = node.right = null;
            
            return successor;
        }
    }
    
    // 打印树
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }
    
    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }
    
    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }
}
