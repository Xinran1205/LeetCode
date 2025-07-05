import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class Solution {
    List<List<Integer>> ret;
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ret = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        if(root==null){
            return ret;
        }
        queue.add(root);
        boolean leftToRight = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> path = new ArrayList<>();
            while(size>0){
                TreeNode node = queue.pop();
                path.add(node.val);
                if(node.right!=null){
                    queue.add(node.right);
                }
                if(node.left!=null){
                    queue.add(node.left);
                }
                size--;
            }
            if(leftToRight){
                Collections.reverse(path);
            }
            leftToRight = !leftToRight;
            ret.add(path);
        }
        return ret;
    }
}