import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public int getMinimumDifference(TreeNode root) {
        // 求树中任意两不同节点值之间的最小差值 。
        // 可以转换成意思：中序遍历后(有序的），相邻节点的最小差值（这个思考一下即可理解）
        List<Integer> list = new ArrayList<Integer>();
        inorderTraversal(list,root);
        int min = Integer.MAX_VALUE;
        for(int i=1;i<list.size();i++){
            min = Math.min(min,list.get(i)-list.get(i-1));
        }
        return min;
    }
    public void inorderTraversal(List<Integer> list, TreeNode node){
        if(node==null){
            return;
        }
        inorderTraversal(list,node.left);
        list.add(node.val);
        inorderTraversal(list,node.right);
    }
}


// 优化！！！使用双指针
class Solution2 {
    int min = Integer.MAX_VALUE;
    TreeNode prev = null;
    public int getMinimumDifference(TreeNode root) {
        inorderTraversal(root);
        return min;
    }
    public void inorderTraversal(TreeNode node){
        if(node==null){
            return;
        }
        inorderTraversal(node.left);
        if(prev!=null&&(node.val-prev.val)<min){
            min = node.val-prev.val;
        }
        prev = node;
        inorderTraversal(node.right);
    }
}