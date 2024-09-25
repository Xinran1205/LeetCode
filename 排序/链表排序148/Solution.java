// Source: https://leetcode-cn.com/problems/sort-list/

// 对于链表，采用归并排序！
// 目前只写了自顶向下，自底向上不会写

class Solution {
    // 定义链表
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 这个排序不好，这个是自顶向下的排序，空间复杂度是O(logn)，栈消耗。空间复杂度主要取决于递归调用的栈空间。
    public ListNode sortList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        //找到中心点
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // 此时slow是分开，从head到slow是第一段，slow.next后是第二段
        ListNode second = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(second);
        // 合并两个链表
        ListNode ret = new ListNode();
        ListNode follow = ret;
        while (left!=null&&right!=null){
            if (left.val>right.val){
                follow.next = right;
                right = right.next;
            }else{
                follow.next = left;
                left = left.next;
            }
            follow = follow.next;
        }
        if (left!=null){
            follow.next = left;
        }else if (right!=null){
            follow.next = right;
        }
        return ret.next;
    }
}