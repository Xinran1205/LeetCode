
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    // 这个题目的注意点是不要遍历到叶子节点，这样无法判断他是不是左子节点
    public int sumOfLeftLeaves(TreeNode root) {
        if(root==null){
            return 0;
        }
        int sum = sumOfLeftLeaves(root.left)+sumOfLeftLeaves(root.right);
        // 如何找到左子节点呢，只能从上一个就判断
        // 如果这个节点是子节点
        if(root.left!=null&&root.left.left==null&&root.left.right==null){
            sum = sum + root.left.val;
        }

        return sum;
    }
}