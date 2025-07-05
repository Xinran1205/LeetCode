import java.util.*;
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null){
            return null;
        }
        //「首尾相连」变「环」再断开
        ListNode tail = head;
        int n = 1;
        while(tail.next!=null){
            n++;
            tail = tail.next;
        }
        tail.next = head;
        //寻找尾断点
        //找到新的断点：从 head 出发，走n - (k % n) - 1步到新的尾节点 newTail。
        //右移k，等价于把尾部的 k 个节点「切」下来放到头部。
        ListNode newTail = head;
        int number = n-(k%n)-1;
        while(number!=0){
            newTail = newTail.next;
            number--;
        }
        //2.尾断点的下一个就是头
        ListNode newHead = newTail.next;
        //断开，返回头
        newTail.next = null;
        return newHead;
    }
}