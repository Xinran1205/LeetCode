
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 独立完成，一遍过！
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int max = nums[0];
        int indexMax = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>max){
                max = nums[i];
                indexMax = i;
            }
        }
        TreeNode root = new TreeNode(max);
        // 左闭右开
        root.left = constructTree(nums,0,indexMax);
        root.right = constructTree(nums,indexMax+1,nums.length);
        return root;
    }

    public TreeNode constructTree(int[] nums, int begin, int end){
        if (begin==end){
            return null;
        }
        int max = nums[begin];
        int indexMax = begin;
        for(int i=begin;i<end;i++){
            if(nums[i]>max){
                max = nums[i];
                indexMax = i;
            }
        }
        TreeNode node = new TreeNode(max);
        node.left = constructTree(nums,begin,indexMax);
        node.right = constructTree(nums,indexMax+1,end);
        return node;
    }
}