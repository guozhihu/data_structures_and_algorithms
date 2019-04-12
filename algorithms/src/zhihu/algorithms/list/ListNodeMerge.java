package zhihu.algorithms.list;

/**
 * Author: zhihu
 * Description: 合并两个排序的链表
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * Date: Create in 2019/4/12 15:39
 */
public class ListNodeMerge {
    
    public static class ListNode {
        int val;
        ListNode next = null;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    public static ListNode Merge(ListNode list1, ListNode list2) {
        // 如果list1为空，则直接返回list2
        if (null == list1) {
            return list2;
        }
        // 如果list2为空，则直接返回list1
        if (null == list2) {
            return list1;
        }
        
        ListNode cur = new ListNode(-1);
        ListNode list = cur;
        while (null != list1 && null != list2) {
            if (list1.val > list2.val) {
                cur.next = list2;
                list2 = list2.next;
            } else {
                cur.next = list1;
                list1 = list1.next;
            }
            cur = cur.next;
        }
        // list1和list2必定有一个为空，一个不为空
        cur.next = null == list1 ? list2 : list1;
        return list.next;
    }
}
