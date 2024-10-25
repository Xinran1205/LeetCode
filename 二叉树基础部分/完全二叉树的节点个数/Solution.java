
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 这个方法就把当成普通二叉树用后序遍历
class Solution {
    public int countNodes(TreeNode root) {
        if(root==null){
            return 0;
        }
        return(countNodes(root.left)+countNodes(root.right)+1);
    }
}

// 这个方法很好！使用完全二叉树的特性！
// 判断子树是否是满二叉树，如果是满二叉树，可以直接用公式计算2^h-1
// 如何判断，只用判断最左边的左子树的高度是否等于最右边的右子树的高度

class Solution2 {
    public int countNodes(TreeNode root) {
        if(root==null){
            return 0;
        }
        int leftDepth = 0;
        int rightDepth = 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        while(left!=null){
            left = left.left;
            leftDepth++;
        }
        while(right!=null){
            right = right.right;
            rightDepth++;
        }
        if(leftDepth==rightDepth){
            return (2<<leftDepth)-1;
        }
        return(countNodes(root.left)+countNodes(root.right)+1);
    }
}