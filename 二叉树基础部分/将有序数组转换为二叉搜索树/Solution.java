
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        // 取中间节点为根节点
        // 要设置一个区间，左闭右闭
        TreeNode root = recursive(nums,0,nums.length-1);
        return root;
    }

    TreeNode recursive(int[] nums,int left, int right){
        if(left>right){
            return null;
        }
        int mid = (right-left)/2 + left;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = recursive(nums,left,mid-1);
        node.right = recursive(nums,mid+1,right);
        return node;
    }

}