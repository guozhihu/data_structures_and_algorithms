package zhihu.datastructures.tree.trie;
import java.util.TreeMap;

/**
 * Author: zhihu
 * Description:字典树(前缀树)
 * Date: Create in 2019/1/10 21:06
 */
public class Trie {
    
    private class Node {
        public boolean isWordEnd; // 标记是否是某个单词的结尾字符
        public TreeMap<Character, Node> next; // 记录该节点的字符以及该节点的所有子节点
        
        public Node(boolean isWordEnd) {
            this.isWordEnd = isWordEnd;
            next = new TreeMap<Character, Node>();
        }
        
        public Node() {
            this(false);
        }
    }
    
    private Node root; // 定义根节点
    private int size; // 记录Trie中记录的单词数量
    
    public int getSize() {
        return this.size;
    }
    
    public Trie() {
        root = new Node(); // 字典树的根节点为空字符
        size = 0;
    }
    
    // 添加某个单词
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (null == cur.next.get(c)) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWordEnd) {
            size++; // 单词数量加1
            cur.isWordEnd = true;
        }
    }
    
    // 查询单词word是否在Trie中
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (null == cur.next.get(c)) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWordEnd;
    }
    
    // 查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (null == cur.next.get(prefix.charAt(i))) {
                return false;
            }
            cur = cur.next.get(prefix.charAt(i));
        }
        return true;
    }
    
    // 在Trie中删除某个单词
    public void delete(String word) {
        if (0 == root.next.size()) {
            throw new RuntimeException("当前目录为空");
        }
        Node cur = root; // 当前节点
        Node deleteStart = null; // 记录从哪个节点下开始删除
        char deleteCharKey = ' ';
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (null == cur.next.get(c)) {
                throw new RuntimeException("不存在该单词");
            }
            // 如果遍历过程中的中间节点有分叉或者是另外一个单词的结尾，则不能删，得记录下来
            deleteStart = (cur.next.size() > 1) || cur.isWordEnd ? cur : deleteStart;
            deleteCharKey = (null != deleteStart) && (cur.next.size() > 1) ? c : deleteCharKey;
            cur = cur.next.get(c);
        }
        if (!cur.isWordEnd) {
            throw new RuntimeException("不存在该单词");
        } else {
            if (cur.next.size() == 0) {
                if (deleteStart.next.size() > 1) {
                    deleteStart.next.remove(deleteCharKey);
                } else {
                    deleteStart.next.clear();
                }
            } else {
                cur.isWordEnd = false;
            }
        }
        size--;
    }
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("bcd");
        trie.add("bce");
        trie.add("bf");
        trie.add("ghij");
        trie.add("klnoq");
        trie.add("klnoqrs");
        trie.add("klnp");
        trie.add("km");
        trie.add("m");
        trie.add("mn");
        
        trie.delete("bcd");
        System.out.println("test1 " + trie.contains("bce")); // true
        System.out.println("test2 " + trie.contains("bcd")); // false
        System.out.println("test3 " + trie.isPrefix("bcd")); // false
        System.out.println(trie.size);
        
        trie.delete("ghij");
        System.out.println("test4 " + trie.isPrefix("gh")); // false
        
        trie.delete("klnp");
        System.out.println("test5 " + trie.isPrefix("klnp")); // false
        System.out.println("test6 " + trie.isPrefix("kln")); // true
        System.out.println("test7 " + trie.contains("klnoqrs")); // true
        System.out.println("test8 " + trie.contains("klnp")); // false
        
        trie.delete("mn");
        System.out.println("test9 " + trie.contains("mn")); // false
        System.out.println("test10 " + trie.isPrefix("mn")); // false
        System.out.println("test11 " + trie.contains("m")); // true
        System.out.println(trie.size);
    }
}
