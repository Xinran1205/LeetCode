class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class Solution {
    public int sumNumbers(TreeNode root) {
        return dfs(root,0);
    }
    public int dfs(TreeNode node,int currentSum){
        if(node==null){
            return 0;
        }
        if(node.left==null&&node.right==null){
            return currentSum*10+node.val;
        }
        int newSum = currentSum*10+node.val;
        return dfs(node.left,newSum)+dfs(node.right,newSum);
    }
}