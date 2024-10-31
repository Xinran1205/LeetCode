import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    TreeNode pre = null;
    int count = 0;
    int maxCount = 0;
    List<Integer> res = new ArrayList<>();
    //思路1，中序遍历两遍，第一遍找到最大的频率，第二遍找到频率等于最大频率的节点
    public int[] findMode(TreeNode root) {
        // 两遍中序遍历
        inOrder(root);
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        if(pre == null){
            count = 1;
        } else{
            if (pre.val == root.val) {
                // 频率加1
                count++;
            } else {
                // 频率重置为1
                count = 1;
            }
        }
        pre = root;
        // 这个地方非常秀
        if(count == maxCount){
            res.add(root.val);
        } else if(count > maxCount){
            res.clear();
            res.add(root.val);
            maxCount = count;
        }
        inOrder(root.right);
    }

}