class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


// 这道题使用后序遍历！
// 一个巧妙的是，通过返回值来判断是否找到了p或者q，并且这不会修改原来的树结构
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        // 处理最下面的逻辑
        if(node==null){
            return null;
        }
        if(node==p||node==q){
            return node;
        }
        // 处理左右子树逻辑
        TreeNode left = lowestCommonAncestor(node.left,p,q);
        TreeNode right = lowestCommonAncestor(node.right,p,q);

        // 处理中逻辑
        if(left!=null&&right!=null){
            return node;
        }
        if(left==null&&right!=null){
            return right;
        }
        if(right==null&&left!=null){
            return left;
        }
        return null;
    }
}

// 如果是二叉搜索树呢？
// 1.递归法
class Solution2{
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return null;
        }
        if(root.val>p.val&&root.val>q.val){
            TreeNode left =  lowestCommonAncestor(root.left,p,q);
            if(left!=null){
                return left;
            }
        }
        if(root.val<p.val&&root.val<q.val){
            TreeNode right = lowestCommonAncestor(root.right,p,q);
            if(right!=null){
                return right;
            }
        }
        // 这个情况就是p和q分别在root的两边
        // 也就是root就是最近公共祖先
        return root;
    }
}

// 2.迭代法
class Solution3{
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root!=null){
            if(root.val>p.val&&root.val>q.val){
                root = root.left;
            }else if(root.val<p.val&&root.val<q.val){
                root = root.right;
            }else{
                return root;
            }
        }
        return null;
    }
}