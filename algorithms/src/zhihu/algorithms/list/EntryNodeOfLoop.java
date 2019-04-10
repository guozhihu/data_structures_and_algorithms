package zhihu.algorithms.list;

/**
 * Author: zhihu
 * Description: 链表中环的入口结点
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 * Date: Create in 2019/4/10 21:23
 */
public class EntryNodeOfLoop {
    
    public static class ListNode {
        int val;
        ListNode next = null;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    public static ListNode entryNodeOfLoop(ListNode pHead) {
        if (null == pHead || null == pHead.next || null == pHead.next.next) {
            return null;
        }
        
        // 定义两个指针，一个快指针，一个慢指针，快指针每次移动两步，慢指针每次移动一步
        ListNode fast = pHead.next.next;
        ListNode slow = pHead.next;
        
        // 如果快指针和慢指针在移动过程中出现快指针==慢指针，说明该单链表有环（数学归纳法）
        while (fast != slow) {
            if (null == fast.next || null == fast.next.next) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // 找到入环节点
        fast = pHead;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
    
    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        ListNode pHead = new ListNode(1);
        pHead.next = new ListNode(2);
        pHead.next.next = new ListNode(3);
        pHead.next.next.next = new ListNode(4);
        pHead.next.next.next.next = new ListNode(5);
        pHead.next.next.next.next.next = new ListNode(6);
        pHead.next.next.next.next.next.next = new ListNode(7);
        System.out.println(entryNodeOfLoop(pHead));
        
        // 1->2->3->4->5->6->7->4...
        pHead = new ListNode(1);
        pHead.next = new ListNode(2);
        pHead.next.next = new ListNode(3);
        pHead.next.next.next = new ListNode(4);
        pHead.next.next.next.next = new ListNode(5);
        pHead.next.next.next.next.next = new ListNode(6);
        pHead.next.next.next.next.next.next = new ListNode(7);
        pHead.next.next.next.next.next.next = pHead.next.next.next; // 7->4
        System.out.println(entryNodeOfLoop(pHead).val);
    }
}
