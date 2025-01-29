import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 方法1，bfs
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if(root==null){
            return ret;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            // size是这一层的节点个数
            while(size!=1){
                TreeNode cur = queue.poll();
                if(cur.left!=null){
                    queue.add(cur.left);
                }
                if(cur.right!=null){
                    queue.add(cur.right);
                }
                size--;
            }
            // 此时这一层只剩一个节点，这个节点是最右边的节点
            TreeNode cur = queue.poll();
            ret.add(cur.val);
            if(cur.left!=null){
                queue.add(cur.left);
            }
            if(cur.right!=null){
                queue.add(cur.right);
            }
        }
        return ret;
    }
}


// 方法2，dfs
class Solution2 {
    List<Integer> ret = new ArrayList<>();
    int maxDepth;
    public List<Integer> rightSideView(TreeNode root) {
        int k = 1;
        maxDepth = 0;
        dfs(root,k);
        return ret;
    }
    public void dfs(TreeNode node,int depth){
        if(node==null){
            return;
        }
        if(depth>maxDepth){
            ret.add(node.val);
            maxDepth = depth;
        }
        // 这里一定要先右！
        dfs(node.right,depth+1);
        dfs(node.left,depth+1);
    }
}