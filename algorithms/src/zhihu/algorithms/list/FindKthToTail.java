package zhihu.algorithms.list;

/**
 * Author: zhihu
 * Description: 链表中倒数第k个结点
 * 输入一个链表，输出该链表中倒数第k个结点。
 * Date: Create in 2019/4/12 14:46
 */
public class FindKthToTail {
    
    public static class ListNode {
        int val;
        ListNode next = null;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    /**
     * 算法思路：
     * 为了实现只遍历链表一次就能找到倒数第k 个结点，我们可以定义两个指针。
     * 第一个指针从链表的头指针开始遍历向前走k-1步，第二个指针保持不动；从
     * 第k步开始，第二个指针也开始从链表的头指针开始遍历。由于两个指针的
     * 距离保持在k-1 ， 当第一个（走在前面的）指针到达链表的尾结点时，第二
     * 个指针（走在后面的）指针正好是倒数第k 个结点。
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode FindKthToTail(ListNode head, int k) {
        if (k < 1 || head == null) {
            return null;
        }
        
        ListNode ahead = head; // ahead指针先走k-1步
        ListNode behind = head; // 当ahead走第k步时，behind指针也开始走
        while (k-- > 1) {
            if (ahead.next != null) {
                ahead = ahead.next;
            } else { // 已经没有节点了，但是还没有到达k-1说明k太大，链表中没有那么多的元素
                return null;
            }
        }
        
        while (ahead.next != null) {
            behind = behind.next;
            ahead = ahead.next;
        }
        return behind;
    }
}
