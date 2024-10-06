// 方法1.树状数组，注意：j = j + (j&-j) 一定要加括号！
// 看这个图：https://blog.csdn.net/TheWayForDream/article/details/118436732 或者bilibili视频
// 树的深度和二进制位数有关！所以复杂度是O(logn)，最大深度是logn+1
//lowbit的解释看图片


//方法2线段树，有链表和数组两种实现方式！

//方法1：
class NumArray {
    int[] nums;
    int[] treeArr;
    int size;

    public NumArray(int[] nums) {
        this.nums = nums;
        size = nums.length;
        treeArr = new int[size+1];
        for(int i=0;i<size;i++){
            int currVal = nums[i];
            int j = i+1;
            while(j<=size){
                treeArr[j] = treeArr[j] + currVal;
                j = j + (j&-j);
            }
        }
    }

    public void update(int index, int val) {
        int valueDif = val - nums[index];
        nums[index] = val;
        int j = index+1;
        while(j<=size){
            treeArr[j] = treeArr[j] + valueDif;
            j = j +(j&-j);
        }
    }

    public int sumRange(int left, int right) {
        //先计算1-right+1
        int i = right+1;
        int rightSum = 0;
        while(i>=1){
            rightSum = rightSum + treeArr[i];
            i = i -(i&-i);
        }

        int j = left;
        int leftSum = 0;
        while(j>=1){
            leftSum = leftSum + treeArr[j];
            j = j - (j&-j);
        }
        return rightSum-leftSum;
    }
}

//方法2：线段树，这个是链表实现！
class NumArray2 {
    class Node{
        int sum;
        int leftIndex;
        int rightIndex;
        Node left;
        Node right;

        public Node(int leftIndex,int rightIndex){
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
        }
    }

    int[] nums;

    Node root;

    public NumArray2(int[] nums) {
        this.nums = nums;
        root = buildTree(0,nums.length-1);
    }

    private Node buildTree(int left,int right){
        if(left>right){
            return null;
        }
        Node node = new Node(left,right);
        if(left==right){
            node.sum = nums[left];
        }else{
            int mid = (right-left)/2+left;
            node.left = buildTree(left,mid);
            node.right = buildTree(mid+1,right);
            node.sum = node.left.sum+node.right.sum;
        }
        return node;
    }
    
    public void update(int index, int val) {
        recursiveUpdate(root,index,val);
    }
    public void recursiveUpdate(Node node, int index,int val){
        if(node.left==node.right){
            node.sum = val;
            return;
        }
        int mid = (node.rightIndex-node.leftIndex)/2+node.leftIndex;
        if(index<=mid){
            recursiveUpdate(node.left,index,val);
        }else{
            recursiveUpdate(node.right,index,val);
        }
        node.sum = node.left.sum+node.right.sum;
    }

    public int sumRange(int left, int right) {
        int sum = recursiveSum(root,left,right);
        return sum;
    }

    public int recursiveSum(Node node,int left,int right){
        if(node==null||node.rightIndex<left||node.leftIndex>right){
            return 0;
        }
        if(node.leftIndex>=left&&node.rightIndex<=right){
            return node.sum;
        }
        int leftSum = recursiveSum(node.left,left,right);
        int rightSum = recursiveSum(node.right,left,right);
        return leftSum+rightSum;
    }
}

// 这个是线段树的数组实现！
class NumArray3 {
    int[] nums;
    int[] segmentTree;

    public NumArray3(int[] nums) {
        //segementTree是一个数组，存的是sum
        this.nums = nums;
        segmentTree = new int[4*nums.length];
        buildTree(0,0,nums.length-1);
    }

    private void buildTree(int index,int left,int right){
        // left-right取值范围是0-nums.length-1
        // index取值范围是0-4*nums.length
        // 两个是不同数组的索引
        if(left==right){
            segmentTree[index] = nums[left];
            return;
        }
        int mid = (right-left)/2+left;
        buildTree(index*2+1,left,mid);
        buildTree(index*2+2,mid+1,right);
        segmentTree[index] = segmentTree[index*2+1]+segmentTree[index*2+2];
    }


    public void update(int index, int val) {
        recursiveUpdate(0,index,val,0,nums.length-1);
    }

    private void recursiveUpdate(int cur, int UpdateIndex,int val,int left,int right){
        if(left==right){
            segmentTree[cur] = val;
            return;
        }
        int mid = (right-left)/2+left;
        if(UpdateIndex<=mid){
            recursiveUpdate(2*cur+1,UpdateIndex,val,left,mid);
        }else{
            recursiveUpdate(2*cur+2,UpdateIndex,val,mid+1,right);
        }
        segmentTree[cur] = segmentTree[2*cur+1]+segmentTree[2*cur+2];
    }

    public int sumRange(int left, int right) {
        int sum = recursiveSum(0,0,nums.length-1,left,right);
        return sum;
    }
    private int recursiveSum(int cur,int leftIndex,int rightIndex,int leftRange,int rightRange){
        if(leftIndex>rightRange||rightIndex<leftRange){
            return 0;
        }
        if(leftIndex>=leftRange&&rightIndex<=rightRange){
            return segmentTree[cur];
        }
        int mid = (rightIndex-leftIndex)/2+leftIndex;
        int leftSum = recursiveSum(2*cur+1,leftIndex,mid,leftRange,rightRange);
        int rightSum = recursiveSum(2*cur+2,mid+1,rightIndex,leftRange,rightRange);
        return leftSum+rightSum;
    }

}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */