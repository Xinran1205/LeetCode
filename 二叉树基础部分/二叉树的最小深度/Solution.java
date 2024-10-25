

// 注意，这道题求的最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
// 注意是叶子节点，不是空节点！空节点直接把上一题求最大深度的代码改成min就可以了。

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        if(root.left==null&&root.right!=null){
            return 1 + minDepth(root.right);
        }else if(root.right==null&&root.left!=null){
            return 1 + minDepth(root.left);
        }else{
            // 左右都不为空
            return 1 + Math.min(minDepth(root.right),minDepth(root.left));
        }
    }
}
