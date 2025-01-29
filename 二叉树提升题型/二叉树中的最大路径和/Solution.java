
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
// 确实题目本身难度不大
// 思路是计算每个节点的max（意思是以该节点为根节点的最大路径和）
// 在递归的过程中同时可以计算出全局最大路径和，路径和的计算是左子树的max+右子树的max+当前节点的值
class Solution {
    int ret = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxi(root);
        return ret;
    }
    public int maxi(TreeNode node){
        if(node==null){
            return 0;
        }
        int leftMax = Math.max(maxi(node.left),0);
        int rightMax = Math.max(maxi(node.right),0);

        int val = leftMax+rightMax+node.val;
        if(val>ret){
            ret = val;
        }
        return node.val+Math.max(leftMax,rightMax);
    }
}