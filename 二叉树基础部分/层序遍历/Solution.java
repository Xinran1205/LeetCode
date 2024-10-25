import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> ret = new LinkedList<>();

        if(root!=null){
            queue.offer(root);
        }
        while(!queue.isEmpty()){
            // 这个size用来记录这一层有多少个节点
            int size = queue.size();
            List<Integer> cur = new LinkedList<>();
            // 遍历每一层
            while(size!=0){
                TreeNode curNode = queue.peek();
                queue.poll();
                cur.add(curNode.val);
                size--;
                // 如果这一层的这个节点有左右子树，就加入到队列中
                if(curNode.left!=null){
                    queue.offer(curNode.left);
                }
                if(curNode.right!=null){
                    queue.offer(curNode.right);
                }
            }
            // 这一层结束，把这一层的节点加入到结果中
            ret.add(cur);
        }
        return ret;
    }
}