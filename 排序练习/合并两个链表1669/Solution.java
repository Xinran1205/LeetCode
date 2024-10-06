// 非常简单，秒了
// 下次要注意的，名字好好起

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        int x =1 ;
        ListNode ret = list1;
        ListNode follow = list1;
        while(x!=a){
            follow = follow.next;
            x++;
        }
        ListNode preHead = follow;
        while(x!=b+1){
            follow = follow.next;
            x++;
        }
        ListNode preBot = follow.next;
        follow.next = null;
        preHead.next = list2;
        while(list2.next!=null){
            list2 = list2.next;
        }
        list2.next = preBot;
        return ret;
    }
}