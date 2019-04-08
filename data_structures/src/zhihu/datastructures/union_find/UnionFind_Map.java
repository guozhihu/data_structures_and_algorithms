package zhihu.datastructures.union_find;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Author: zhihu
 * Description: 使用map结构实现
 * Date: Create in 2019/3/13 0:35
 */
public class UnionFind_Map {
    public static class Node {
        // whatever you like
    }
    
    public static class UnionFindSet {
        public HashMap<Node, Node> fatherMap;
        public HashMap<Node, Integer> sizeMap;
        
        public UnionFindSet() {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }
        
        // 将传进来的元素创建成集合
        public void makeSets(List<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }
        
        // 递归版
        private Node findHead(Node node) {
            Node father = fatherMap.get(node);
            if (father != node) {
                father = findHead(father);
            }
            fatherMap.put(node, father);
            return father;
        }
        
        // 非递归版
        private Node findHeadUnRecur(Node node) {
            Stack<Node> stack = new Stack<Node>();
            Node cur = node;
            Node parent = fatherMap.get(cur);
            while (cur != parent) {
                stack.push(cur);
                cur = parent;
                parent = fatherMap.get(cur);
            }

            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), parent);
            }
            return parent;
        }
        
        // 判断两个元素是否属于同一个集合
        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }
        
        // 合并集合
        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aHead = findHead(a);
            Node bHead = findHead(b);
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                }
            }
        }
        
    }
    
    public static void main(String[] args) {
    
    }
}
