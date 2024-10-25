
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 这里理解一个概念
// 深度是任意节点到根节点的路径长度（距离）（前序遍历） 从上往下
// 高度是任意节点到叶子节点的路径长度（距离）（后序遍历） 从下往上


// 这一题他叫最大深度，但是实际上求的是根节点的高度，所以用的是后序遍历

class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
}