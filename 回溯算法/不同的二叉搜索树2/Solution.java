import java.util.*;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 这一题还挺难思考的！
// 做完不同的二叉搜索树1可以知道，等于左边的所有可能的树乘以右边的所有可能的树

class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return generate(1, n);
    }
    // 遍历所有可能的根节点
    public List<TreeNode> generate(int start,int end){
        List<TreeNode> ret = new ArrayList<>();
        if(start>end){
            ret.add(null);
            return ret;
        }
        for(int i=start;i<=end;i++){
            List<TreeNode> leftTree = generate(start,i-1);
            List<TreeNode> rightTree = generate(i+1,end);

            for(TreeNode a:leftTree){
                for(TreeNode b:rightTree){
                    TreeNode node = new TreeNode(i);
                    node.left = a;
                    node.right = b;
                    ret.add(node);
                }
            }
        }
        return ret;
    }
}