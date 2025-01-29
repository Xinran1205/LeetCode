import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public void flatten(TreeNode root) {
        recursiveFlatten(root);
    }
    public void recursiveFlatten(TreeNode node) {
        if(node==null){
            return;
        }
        // 先处理最左边
        recursiveFlatten(node.left);
        TreeNode right = node.right;
        node.right = node.left;
        node.left = null;
        // 以上把最左边挂成了一串

        // 处理右边,把右边挂到处理好的左边的最右边
        if(right!=null){
            TreeNode lastRight = node;
            // 这里要用循环找到最右边的节点，因为一轮一轮往上return后，node.right会变化，需要用循环！
            while(lastRight.right!=null){
                lastRight = lastRight.right;
            }
            lastRight.right = right;
            recursiveFlatten(right);
        }
    }
}