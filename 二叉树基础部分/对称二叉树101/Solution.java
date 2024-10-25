import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public boolean isSymmetric(TreeNode root) {
        //这一题要采取后序遍历的方法，左右中，因为我需要从下往上搜集孩子的信息

        // 这也是考察两个二叉树是否可以相互翻转的问题
        return reverse(root.left,root.right);
    }

    public boolean reverse(TreeNode node1,TreeNode node2){
        if(node1==null&&node2!=null){
            return false;
        }
        if(node1!=null&&node2==null){
            return false;
        }
        if(node1==null&&node2==null){
            return true;
        }
        if(node1.val!=node2.val){
            return false;
        }
        //这里其实就是代表了左右中，两个reverse分别代表了左右子树，整个&&的结果代表中节点的信息
        return reverse(node1.left,node2.right)&&reverse(node1.right,node2.left);
    }
}