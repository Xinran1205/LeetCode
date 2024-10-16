
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// 方法1. 最孬，创建一个ret，然后一个一个链表遍历，每个链表都与ret进行合并

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ret = null;
        for(int i=0;i<lists.length;i++){
            ret = mergeTwo(ret,lists[i]);
        }
        return ret;
    }

    private ListNode mergeTwo(ListNode ret,ListNode a){
        if(ret==null){
            return a;
        }
        if(a==null){
            return ret;
        }
        ListNode dummyHead = new ListNode();
        ListNode follow = dummyHead;
        ListNode preRet = ret;
        ListNode preA = a;
        while(preRet!=null&&preA!=null){
            if(preRet.val<preA.val){
                follow.next = preRet;
                follow = follow.next;
                preRet = preRet.next;
            }else{
                follow.next = preA;
                follow = follow.next;
                preA = preA.next;
            }
        }
        if(preRet!=null){
            follow.next = preRet;
        }
        if(preA!=null){
            follow.next = preA;
        }

        return dummyHead.next;
    }
}


// 方法2 分治法，分到最小，然后两两合并

class Solution2 {

    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists,0,lists.length-1);
    }

    public ListNode merge(ListNode[] list, int left, int right){
        if(left==right){
            return list[left];
        }
        if(left>right){
            return null;
        }
        int mid = (right-left)/2+left;
        return mergeTwo(merge(list,left,mid),merge(list,mid+1,right));
    }

    public ListNode mergeTwo(ListNode a,ListNode b){
        ListNode dummyHead = new ListNode();
        ListNode follow = dummyHead;
        ListNode af = a;
        ListNode bf = b;
        while(af!=null&&bf!=null){
            if(af.val<bf.val){
                follow.next = af;
                follow = follow.next;
                af = af.next;
            }else{
                follow.next = bf;
                follow = follow.next;
                bf = bf.next;
            }
        }
        if(af!=null){
            follow.next = af;
        }
        if(bf!=null){
            follow.next = bf;
        }
        return dummyHead.next;
    }
}