
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null){
            return false;
        }
        return recursiveTrack(root, root.val, targetSum);
    }

    public boolean recursiveTrack(TreeNode node, int sum, int targetSum){
        if(node.left==null&&node.right==null&&sum==targetSum){
            return true;
        }
        if(node.left==null&&node.right==null&&sum!=targetSum){
            return false;
        }
        if(node.left!=null){
            sum = sum + node.left.val;
            if(recursiveTrack(node.left, sum, targetSum)==true){
                return true;
            }
            sum = sum - node.left.val;
        }
        if(node.right!=null){
            sum = sum + node.right.val;
            if(recursiveTrack(node.right, sum, targetSum)==true){
                return true;
            }
            sum = sum - node.right.val;
        }
        return false;
    }
}