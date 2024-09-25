import java.util.*;

public class Solution {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        ListNode(){}
    }

    public ListNode findKList(ListNode head, int k){
        ListNode fast = head;
        ListNode slow = head;
        int i=1;
        while (fast!=null&&i<k){
            fast = fast.next;
            i++;
        }
        if(fast==null){
            return null;
        }
        while(fast!=null){
            if(fast.next==null){
                return slow;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return null;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode();
        head.val = 1;
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next.next.next.next.next = new ListNode(10);

        ListNode res = s.findKList(head, 3);
        System.out.println(res.val);
    }
}
