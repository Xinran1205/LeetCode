class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    // 把插入的值看成叶子节点
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 这里处理root为空的情况
        if(root==null){
            return new TreeNode(val);
        }
        insert(root,val);
        return root;
    }
    public void insert(TreeNode node, int val) {
        if(node.val>val){
            if(node.left==null){
                node.left = new TreeNode(val);
            }else{
                insert(node.left,val);
            }
        }else{
            if(node.right==null){
                node.right = new TreeNode(val);
            }else{
                insert(node.right,val);
            }
        }
    }
}