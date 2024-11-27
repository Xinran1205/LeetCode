
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode swapPairs(ListNode head) {
        // if(head == null||head.next==null){
        //     return head;
        // }
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode ret = pre;
        while(head!=null&&head.next!=null){
            ListNode next = head.next;
            ListNode nextNext = head.next.next;
            head.next = nextNext;
            next.next = head;
            pre.next = next;
            pre = head;
            head = nextNext;
        }
        return ret.next;
    }
}