/**
 * @author Xinran
 * @version 1.0
 * @description TODO
 * @date 2024/6/6 16:23
 */

class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) { this.val = val; }
  ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode cur = head;
        int carry =0;
        while (l1!=null || l2!=null){
            int val1;
            if (l1!= null){
                val1 = l1.val;
            }else{
                val1 = 0;
            }
            int val2;
            if (l2!= null){
                val2 = l2.val;
            }else{
                val2 = 0;
            }
            int sum = val1 + val2 + carry;
            int val = sum%10;
            carry = sum/10;

            cur.next = new ListNode(val);
            cur = cur.next;

            if (l1!=null){
                l1 = l1.next;
            }
            if (l2!=null){
                l2 = l2.next;
            }
        }
        if (carry == 1){
            cur.next = new ListNode(1);
        }
        return head.next;
    }
}

// more clean!
class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;

        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;

            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummyHead.next;
    }
}

public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9, new ListNode(9));
        ListNode l2 = new ListNode(9);

        Solution solution = new Solution();
        ListNode result = solution.addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}