import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 方法1. 层序遍历（BFS），有一点绕
class Solution {
    // 这个题目直接理解成最后一行最左侧的值（第一个值）它是右节点也无所谓
    public int findBottomLeftValue(TreeNode root) {
        int ret = 0;
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        if(root!=null){
            queue.offer(root);
        }
        while(!queue.isEmpty()){
            TreeNode p = queue.poll();
            // 必须要先放右再放左，这样才能保证poll取的是右，从右向左遍历
            // 所以最后会是最左边的元素
            if (p.right != null) {
                queue.offer(p.right);
            }
            if (p.left != null) {
                queue.offer(p.left);
            }
            ret = p.val;
        }
        return ret;
    }
}

//方法2. 递归
class Solution2 {
    int result = 0;
    int Max_Depth = 0;

    public int findBottomLeftValue(TreeNode root) {
        recursive(root,1);
        return result;
    }

    public void recursive(TreeNode node,int depth){
        if(node.left==null&&node.right==null){
            if(depth>Max_Depth){
                Max_Depth = depth;
                result = node.val;
            }
            return;
        }
        if(node.left!=null){
            depth++;
            recursive(node.left,depth);
            depth--;
        }
        if(node.right!=null){
            depth++;
            recursive(node.right,depth);
            // 这一行确实删了都可以，因为是局部变量
            depth--;
        }
        return;
    }
}