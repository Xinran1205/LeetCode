
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// 这一题很容易想到双指针，但是细节方面还是要注意！
class Solution {
    // 双指针跑
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode ret = new ListNode();
        ListNode firstNode = head;
        int j = 1;
        while(j<=n){
            firstNode = firstNode.next;
            j++;
        }
        ListNode slowNode = ret;
        slowNode.next = head;
        while(firstNode!=null){
            slowNode = slowNode.next;
            firstNode = firstNode.next;
        }
        slowNode.next = slowNode.next.next;
        return ret.next;
    }
}