package zhihu.algorithms.list;

/**
 * Author: zhihu
 * Description: 反转链表
 * 输入一个链表，反转链表后，输出新链表的表头。
 * Date: Create in 2019/4/12 15:08
 */
public class ReverseList {
    
    public static class ListNode {
        int val;
        ListNode next = null;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    /**
     * 算法思路：
     * 将指向下一个节点的指针指向上一个节点。
     * @param head
     * @return
     */
    public static ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        ListNode next = null;
        while (null != cur) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = null;
        return pre;
    }
}
