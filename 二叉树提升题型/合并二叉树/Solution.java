
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 这个题目这里还挺关键的，不能重新new一个节点，不然后序的节点递归不到了
        if(root1==null&&root2!=null){
            return root2;
        }
        if(root2==null&&root1!=null){
            return root1;
        }
        if(root2==null&&root1==null){
            return null;
        }
        TreeNode node = new TreeNode(root1.val+root2.val);
        node.left = mergeTrees(root1.left,root2.left);
        node.right = mergeTrees(root1.right,root2.right);
        return node;
    }
}