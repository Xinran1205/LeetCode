
// 反转链表
// 两种方法 1.先找到left，再找到right，反转中间部分
//2.穿针引线，每次把要反转的部分往前加

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode findLeft = head;
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode ret = pre;
        int i = 1;
        while (findLeft != null && i != left) {
            pre = pre.next;
            findLeft = findLeft.next;
            i++;
        }
        if (findLeft == null) {
            return head;
        }
        ListNode findRight = findLeft;
        int j = i;
        while (findRight != null && j != right) {
            findRight = findRight.next;
            j++;
        }
        if (findRight == null) {
            return head;
        }
        pre.next = findRight;
        ListNode leftP = findRight.next;

        while (findLeft != findRight) {
            ListNode keep = findLeft.next;
            findLeft.next = leftP;
            leftP = findLeft;
            findLeft = keep;
        }
        findRight.next = leftP;

        return ret.next;
    }

    // 方法2
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode ret = new ListNode();
        ret.next = head;
        // 这一行很关键！因为就是头也会变得，所以随着pre变
        ListNode pre = ret;
        // 先把head移动到left,pre永远是head前一个
        int i = 1;
        while(i!=left){
            head = head.next;
            pre = pre.next;
            i++;
        }
        if (head.next == null){
            return ret.next;
        }
        // 此时head在left节点上
        while (i<right){
            ListNode nextHead = head.next.next;
            ListNode preNext = pre.next;
            pre.next = head.next;
            head.next.next = preNext;
            head.next = nextHead;
            i++;
        }
        return ret.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        for (int i = 2; i <= 5; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        solution s = new solution();
        ListNode ret = s.reverseBetween(head, 2, 4);
        while (ret != null) {
            System.out.println(ret.val);
            ret = ret.next;
        }

        // 测试2
        ListNode newTest = new ListNode(1);
        ListNode cur2 = newTest;
        for (int i = 2; i <= 5; i++) {
            cur2.next = new ListNode(i);
            cur2 = cur2.next;
        }
        ListNode ret2 = s.reverseBetween2(newTest, 2, 4);
        while (ret2 != null) {
            System.out.println(ret2.val);
            ret2 = ret2.next;
        }

    }
}
