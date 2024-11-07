
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    TreeNode pre = null;
    public TreeNode convertBST(TreeNode root) {
        // 按照右中左遍历
        recursive(root);
        return root;
    }
    public void recursive(TreeNode node){
        if(node==null){
            return;
        }
        recursive(node.right);
        if(pre!=null){
            node.val += pre.val;
        }
        pre = node;
        recursive(node.left);
    }
}