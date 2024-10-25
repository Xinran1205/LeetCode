
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public boolean isBalanced(TreeNode root) {
        int ret = BalancedTree(root);
        if(ret==-1){
            return false;
        }else{
            return true;
        }
    }

    //任何一个节点，左右子树不满足，那整个也不满足，返回-1即可
    public int BalancedTree(TreeNode node){
        if(node==null){
            return 0;
        }
        int leftHeight = BalancedTree(node.left);
        if(leftHeight==-1){
            return -1;
        }
        int rightHeight = BalancedTree(node.right);
        if(rightHeight==-1){
            return -1;
        }
        if(Math.abs(leftHeight-rightHeight)>1){
            return -1;
        }
        // 这里记得取最大值
        return 1 + Math.max(leftHeight,rightHeight);
    }
}