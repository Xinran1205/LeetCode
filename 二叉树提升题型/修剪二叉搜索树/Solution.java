
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 这一题非常有代表性！！！
// 沿用上一题删除二叉搜索树中的节点的思路
// 但是遍历顺序需要改，要改成后序遍历！！！上一题其实也可以后序遍历（不影响）
// 这一题如果用前序它只能删1-2个，下面就考虑不到了
class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root==null){
            return null;
        }
        root.left = trimBST(root.left,low,high);
        root.right = trimBST(root.right,low,high);
        //如果找到要删除的节点
        if(root.val < low||root.val>high){
            if(root.left==null&&root.right==null){
                return null;
            }else if(root.left!=null&&root.right==null){
                return root.left;
            }else if(root.left==null&&root.right!=null){
                return root.right;
            }else{
                TreeNode cur = root.right;
                while(cur.left!=null){
                    cur = cur.left;
                }
                cur.left = root.left;
                return root.right;
            }
        }
        return root;
    }
}