
// 725. 分隔链表

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }
}

public class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] ret = new ListNode[k];
        // 先遍历一遍看有多少个
        ListNode sum = head;
        int number = 0;
        while(sum!=null){
            sum = sum.next;
            number++;
        }
        // 把number分成k组，并且余数平均加到前面
        int eachGroupBase = number/k;
        int remainder = number%k;
        int[] numbers = new int[k];
        for (int i =0; i<k; i++){
            numbers[i] = eachGroupBase;
        }
        for (int i=0;i<remainder;i++){
            numbers[i] += 1;
        }
        // 现在numbers数组中，每一位代表每一组的长度

        //循环k次
        for (int i=0;i<k;i++){
            ListNode list = new ListNode();
            ListNode tail = list;
            list.next = head;
            tail.next = head;
            //构造小新链表,loop是每个小新链表的长度
            int loop = numbers[i];
            while (loop>0){
                tail = tail.next;
                head = head.next;
                loop --;
            }
            // 截断小链表
            tail.next = null;
            //每轮循环结束小链表添加到listNodes中
            ret[i] = list.next;
        }
        return ret;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1 , new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7, new ListNode(8, new ListNode(9, new ListNode(10))))))))));
        Solution solution = new Solution();
        ListNode[] parts = solution.splitListToParts(head, 3);
        for (ListNode part : parts) {
            while (part != null) {
                System.out.print(part.val + " ");
                part = part.next;
            }
            System.out.println();
        }
    }
}
