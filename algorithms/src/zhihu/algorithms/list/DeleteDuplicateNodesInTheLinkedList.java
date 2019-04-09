package zhihu.algorithms.list;

/**
 * Author: zhihu
 * Description: 删除链表中重复的结点
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，
 * 返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 * Date: Create in 2019/4/9 22:56
 */
public class DeleteDuplicateNodesInTheLinkedList {
    
    public static class ListNode {
        int val;
        ListNode next = null;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    /**
     * 算法思路：
     * 解决这个问题的第一步是确定删除的参数。当然这个函数需要输入待删除链表的头结点。头结
     * 点可能与后面的结点重复，也就是说头结点也可能被删除，所以在链表头添加一个结点。
     * <p>
     * 接下来我们从头遍历整个链表。如果当前结点的值与下一个结点的值相同，那么它们就是重复
     * 的结点，都可以被删除。为了保证删除之后的链表仍然是相连的而没有中间断开，我们要把当
     * 前的前一个结点和后面值比当前结点的值要大的结点相连。我们要确保prev要始终与下一个没
     * 有重复的结点连接在一起。
     *
     * @param pHead
     * @return
     */
    public static ListNode deleteDuplication(ListNode pHead) {
        
        // 待删除链表的头结点可能与后面的结点重复，也就是说头结点也可能被删除，
        // 所以在链表头添加一个结点
        ListNode first = new ListNode(-1);
        first.next = pHead;
        
        ListNode p = pHead;
        ListNode last = first;
        while (null != p && null != p.next) {
            if (p.val == p.next.val) {
                int val = p.val;
                while (null != p && p.val == val) {
                    p = p.next;
                }
                last.next = p;
            } else {
                last = p;
                p = p.next;
            }
        }
        return first.next;
    }
}
