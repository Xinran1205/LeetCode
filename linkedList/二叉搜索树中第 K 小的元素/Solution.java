import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 方法1，中序遍历，然后取第k-1个元素
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        traversal(list,root);
        return list.get(k-1);
    }
    public void traversal(List<Integer> list,TreeNode node){
        if(node==null){
            return;
        }
        traversal(list,node.left);
        list.add(node.val);
        traversal(list,node.right);
    }
}

//方法2，和上面几乎完全一样，但是空间复杂度更低，并且时间复杂度也要低一些
class Solution2 {
    int result = 0;
    private int count;   // 用于计数
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        traversal(root);
        return result;
    }
    public void traversal(TreeNode node){
        if(node==null){
            return;
        }
        traversal(node.left);
        count--;
        if(count==0){
            result = node.val;
            return;
        }
        traversal(node.right);
    }
}