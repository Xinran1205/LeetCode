/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
import java.util.*;
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // 哨兵节点，方便处理头部就重复的情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // pre 始终指向「已经处理完且确定要保留」区间的最后一个节点
        ListNode pre = dummy;
        // cur 用来遍历
        ListNode cur = head;

        while (cur != null) {
            // 如果后面有重复节点，就跳过整段相同值
            if (cur.next != null && cur.val == cur.next.val) {
                int dupVal = cur.val;
                // 一直走到最后一个值不再等于 dupVal 的节点
                while (cur != null && cur.val == dupVal) {
                    cur = cur.next;
                }
                // 把 pre.next 直接指向 cur，跳过所有 dupVal 节点
                pre.next = cur;
            } else {
                // cur 这个值没有重复，保留它，pre 向前走一步
                pre = cur;
                cur = cur.next;
            }
        }

        // 返回真实头
        return dummy.next;
    }
}