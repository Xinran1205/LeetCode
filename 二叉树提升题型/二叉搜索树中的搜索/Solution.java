
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 递归法
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null||val==root.val){
            return root;
        }
        if(val>root.val){
            return searchBST(root.right,val);
        }
        if(val<root.val){
            return searchBST(root.left,val);
        }
        return null;
    }
}

// 迭代法
class Solution2 {
    public TreeNode searchBST(TreeNode root, int val) {
        while(root!=null){
            if(val>root.val){
                root=root.right;
            }else if(val<root.val){
                root=root.left;
            }else{
                return root;
            }
        }
        return null;
    }
}