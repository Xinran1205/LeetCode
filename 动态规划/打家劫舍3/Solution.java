// 这一题不难思考，主要是后序遍历！然后要理解对于这种链表（树形结构）如何用动态规划操作！
// 靠每一层的返回值来进行操作！

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public int rob(TreeNode root) {
        int[] arr = recursive(root);
        return Math.max(arr[0],arr[1]);
    }
    public int[] recursive(TreeNode node){
        if(node==null){
            return new int[]{0,0};
        }
        int[] leftArr = recursive(node.left);
        int[] rightArr = recursive(node.right);

        int curNotRob = Math.max(leftArr[0],leftArr[1])+Math.max(rightArr[0],rightArr[1]);
        int curRob = node.val+leftArr[0]+rightArr[0];

        return new int[]{curNotRob,curRob};
    }
}