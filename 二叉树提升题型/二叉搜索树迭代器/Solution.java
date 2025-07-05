import java.util.*;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class BSTIterator {
    TreeNode cur;
    Deque<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        cur = root;
        stack = new ArrayDeque<>();
    }

    public int next() {
        while(cur!=null){
            stack.push(cur);
            cur = cur.left;
        }
        TreeNode node = stack.pop();
        int val = node.val;
        cur = node.right;
        return val;
    }

    public boolean hasNext() {
        return cur!=null || !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */