// Source: https://leetcode-cn.com/problems/partition-list/

//这个题目做之前要仔细思考，理清题目思路就很简单
//1. 最简单的方法，小的连到大的，这个就是太神了
//2. 原地操作，我觉得很难搞


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode();
        ListNode big = new ListNode();
        ListNode ret = small;
        ListNode keep = big;
        while(head!=null){
            if (head.val<x){
                small.next = head;
                small = small.next;
                head = head.next;
            }else{
                big.next = head;
                big = big.next;
                head = head.next;
            }
        }
        small.next = keep.next;
        big.next = null;
        return ret.next;
    }
}

// 方法2，原地操作，这个方法的难点在于要明确条件，然后如何跟踪。
class Solution2 {
    public ListNode partition(ListNode head, int x) {
        ListNode ret = new ListNode();
        ListNode cur = head;
        ret.next = head;
        head = ret;
        ListNode pre = ret;

        while (cur!=null){
            if (cur.val<x){
                if(head.next == cur){
                    head = head.next;
                    cur = cur.next;
                    pre = pre.next;
                }else{
                    pre.next = cur.next;
                    cur.next = head.next;
                    head.next = cur;

                    head = head.next;
                    cur = pre.next;
                }
            }else{
                cur = cur.next;
                pre = pre.next;
            }
        }
        return ret.next;
    }
}

public class fenge {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
        Solution s = new Solution();
        Solution2 s2 = new Solution2();
    }
}