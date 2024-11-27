// 这道题不是很难，就是细心一些，静心思考很简单

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

class MyLinkedList {
    int size;
    ListNode head;

    public MyLinkedList() {
        int size = 0;
        head = new ListNode(0);
    }

    public int get(int index) {
        ListNode follow = head;
        int i = -1;
        while(follow!=null&&i<index){
            follow = follow.next;
            i++;
            if(i==index&&follow!=null){
                return follow.val;
            }
        }
        return -1;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head.next;
        head.next = newNode;
        size++;
    }

    public void addAtTail(int val) {
        ListNode follow = head;
        ListNode pre = head;
        while(follow!=null){
            pre = follow;
            follow = follow.next;
        }
        ListNode newNode = new ListNode(val);
        pre.next = newNode;
        size++;
    }

    public void addAtIndex(int index, int val) {
        ListNode follow = head;
        ListNode pre = head;
        int i = -1;
        while(follow!=null&&i<index){
            pre = follow;
            follow = follow.next;
            i++;
            if(i==index){
                ListNode newNode = new ListNode(val);
                ListNode keep = pre.next;
                pre.next = newNode;
                newNode.next = keep;
                size++;
                return;
            }
        }
    }

    public void deleteAtIndex(int index) {
        ListNode follow = head;
        ListNode pre = head;
        int i = -1;
        while(follow!=null&&i<index){
            pre = follow;
            follow = follow.next;
            i++;
            if(i==index&&follow!=null){
                pre.next = follow.next;
                size--;
                return;
            }
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */