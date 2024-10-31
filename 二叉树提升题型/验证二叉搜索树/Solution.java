import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 方法1. 中序遍历树，看遍历的结果是不是单调递增
class Solution {
    // 这个代码的关键是java动态数组可以使用这个arraylist，这个很方便！
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        InorderTraversal(root,list);
        for(int i=1;i<list.size();i++){
            if(list.get(i-1)>=list.get(i)){
                return false;
            }
        }
        return true;
    }
    public void InorderTraversal(TreeNode node, List<Integer> list){
        if(node==null){
            return;
        }
        InorderTraversal(node.left,list);
        list.add(node.val);
        InorderTraversal(node.right,list);
    }
}

// 方法2. 递归法，还是中序遍历
// 这个方法非常巧妙，prev就是前一个节点的值，一定要记住这个是按照中序遍历的顺序来的，可以在纸上画一下这个遍历过程就懂了
class Solution2 {
    private long prev = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 递归左边，我们先走到最左下角
        boolean left = isValidBST(root.left);
        // 处理逻辑
        if(prev<root.val){
            prev = root.val;
        }else{
            return false;
        }
        // 递归右边
        boolean right = isValidBST(root.right);
        return left&&right;
    }
}

// 优化上面代码，用指针保存前一个节点
class Solution3 {
    TreeNode prev;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isValidBST(root.left);
        if(prev!=null&&prev.val>=root.val){
            return false;
        }
        prev = root;
        boolean right = isValidBST(root.right);
        return left&&right;
    }
}