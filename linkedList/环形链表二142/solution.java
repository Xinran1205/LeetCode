//这个题目返回的是进入环的第一个节点

//要求：不给用哈希表，要用快慢指针

// 看这个图
// 这个图证明了为什么快慢指针相遇的地方到环的起点的距离等于头节点到环的起点的距离
// https://leetcode.cn/problems/linked-list-cycle-ii/solutions/2832831/jian-ji-qing-xi-yan-jin-de-tu-shi-tui-da-nak2

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class solution {
    public ListNode detectCycle(ListNode head) {
        // 先快慢指针让他们相遇
        if(head==null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode ret = head;

        while(fast!=null&&fast.next!=null&&fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast==slow){
                break;
            }
        }
        if (fast==null||fast.next==null||fast.next.next==null){
            return null;
        }

        while(ret!=slow){
            ret = ret.next;
            slow = slow.next;
        }
        return ret;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        solution s = new solution();
        System.out.println(s.detectCycle(head).val);
    }
}

