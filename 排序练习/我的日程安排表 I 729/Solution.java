class MyCalendar {
    class Node{
        int leftIndex;
        int rightIndex;
        boolean isBooked;

        Node left;
        Node right;
        public Node(int leftIndex,int rightIndex){
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
            this.isBooked = false;
        }
    }
    Node root;


    public MyCalendar() {
        root = new Node(0,1000000000);
    }

    public boolean book(int start, int end) {
        if(isBooked(root,start,end)){
            return false;
        }else{
            //插入
            update(root,start,end);
        }
        return true;
    }

    private void update(Node node,int start,int end){
        //节点无关，直接return
        if(node.leftIndex>=end||node.rightIndex<=start){
            return;
        }

        //节点完全在区间内，设置成isbooked，并且子全部null，return；
        if(node.leftIndex>=start&&node.rightIndex<=end){
            node.isBooked = true;
            node.left = null;
            node.right = null;
            return;
        }
        // 区间有部分在节点内。
        // 需要开辟子节点
        if(node.left==null){
            int mid = (node.rightIndex-node.leftIndex)/2+node.leftIndex;
            node.left = new Node(node.leftIndex,mid);
            node.right = new Node(mid,node.rightIndex);
        }
        //递归子节点
        update(node.left,start,end);
        update(node.right,start,end);
        node.isBooked = node.left.isBooked&&node.right.isBooked;
    }

    //这个区间内有节点
    private boolean isBooked(Node node,int start,int end){
        // 如果节点为空，return false;
        if(node==null){
            return false;
        }
        if(node.isBooked){
            return true;
        }
        //
        int mid = node.leftIndex+(node.rightIndex-node.leftIndex)/2;
        if(end<=mid){
            return isBooked(node.left,start,end);
        }else if(start>=mid){
            return isBooked(node.right,start,end);
        }else{
            //这个大时间是否可以定，如果它左右（内部）有一个被订了，那这个大时间也被订了
            return isBooked(node.left,start,mid)||isBooked(node.right,mid,end);
        }
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */