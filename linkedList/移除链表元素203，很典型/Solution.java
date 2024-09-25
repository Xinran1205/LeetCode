// 这个题目很好的阐述了dummyNode的使用

class Solution {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
    }
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode pre = dummyNode;
        while(head!=null){
            if(head.val==val){
                pre.next = head.next;
            }else{
                pre = pre.next;
            }
            head = head.next;
        }
        return dummyNode.next;
    }

    //方法2，递归，这个递归解法很神、
    // 可以这么理解，就是我一直先跳到最后一个节点
    // 然后每次看这个节点的下一个是什么
    public ListNode removeElements2(ListNode head, int val) {
        if(head==null){
            return null;
        }
        head.next = removeElements(head.next,val);
        if (head.val==val){
            return head.next;
        }else{
            return head;
        }
    }
}
