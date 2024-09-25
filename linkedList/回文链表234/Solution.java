import java.util.Stack;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public ListNode() {
    }
}

public class Solution {
    // 方法一，使用栈，一定要考虑到12321这种情况，这个方法不是很好，一个是两次遍历，一个是空间复杂度为O(n)
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack =  new Stack<Integer>();
        ListNode firstIn = head;
        while(firstIn!=null){
            stack.push(firstIn.val);
            firstIn = firstIn.next;
        }
        while(head!=null){
            if (stack.peek().equals(head.val)){
                stack.pop();
            }else{
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        // 先找到一半的位置
        ListNode preNode = new ListNode();
        preNode.next = head;
        ListNode slow = preNode;
        ListNode fast = preNode;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // 然后翻转后半部分
        ListNode begin = slow.next;
        ListNode pre = myReverse(begin);
        // 然后比较
        ListNode keepPre = pre;
        ListNode start = head;
        while(pre!=null&&start!=slow.next){
            if(start.val!=pre.val){
                return false;
            }
            pre = pre.next;
            start = start.next;
        }
        // 再把翻回来，虽然不影响结果，但是不要破坏链表结构！
        pre = myReverse(keepPre);
        return true;
    }
    public ListNode myReverse(ListNode begin){
        ListNode pre = null;
        while(begin!=null){
            ListNode next = begin.next;
            begin.next = pre;
            pre = begin;
            begin = next;
        }
        return pre;
    }
}