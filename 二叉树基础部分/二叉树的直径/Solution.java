
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 这道题要用求最大深度的思路一样！！！

class Solution {
    int ret;
    public int diameterOfBinaryTree(TreeNode root) {
        // 求每个节点（经过这个节点）的最大直径
        // 实际就是求他的左右最大深度之和。
        ret = 1;
        maxDepth(root);
        return ret-1;
    }
    public int maxDepth(TreeNode node) {
        if(node==null){
            return 0;
        }
        int L = maxDepth(node.left);
        int R = maxDepth(node.right);
        // 这是唯一一行和求最大深度不同的代码
        ret = Math.max(ret,L+R+1);
        return Math.max(L,R)+1;
    }
}