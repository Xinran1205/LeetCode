import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    List<Double> ret = new ArrayList<>();
    public List<Double> averageOfLevels(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        while(!deque.isEmpty()){
            double size = deque.size();
            double sum = 0;
            double number = size;
            while(size!=0){
                TreeNode cur = deque.poll();
                sum+=cur.val;
                if(cur.left!=null){
                    deque.add(cur.left);
                }
                if(cur.right!=null){
                    deque.add(cur.right);
                }
                size--;
            }
            ret.add(sum/number);
        }
        return ret;
    }
}