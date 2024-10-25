import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        // 迭代
        // 模拟一个栈
        List<Integer> ret = new ArrayList<Integer>();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while(!stack.isEmpty()||node!=null){
            while(node!=null){
                ret.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return ret;
    }
}

//递归
class Solution2{
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        preorder(root,ret);
        return ret;
    }
    public void preorder(TreeNode root,List<Integer> ret){
        if(root==null){
            return;
        }
        ret.add(root.val);
        preorder(root.left,ret);
        preorder(root.right,ret);
    }
}

// 中序遍历
class Solution3 {
    // 递归
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        inorder(ret,root);
        return ret;
    }
    public void inorder(List<Integer> ret,TreeNode node){
        if(node==null){
            return;
        }
        inorder(ret,node.left);
        ret.add(node.val);
        inorder(ret,node.right);
        return;
    }
}

class Solution4 {
    // 迭代
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        while(!stack.isEmpty()||root!=null){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            ret.add(root.val);
            root = root.right;
        }
        return ret;
    }
}


// 后序遍历，递归省略，迭代有点值得思考!!!

class Solution5{

    public List<Integer> postorderTraversal(TreeNode root) {
        //左右中
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    // 这个方法非常骚，它的方法就是对于前序遍历（中左右）把改成（中右左），然后翻转列表即为（左右中）
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while(!stack.isEmpty()||node!=null){
            while(node!=null){
                ret.add(node.val);
                stack.push(node);
                node = node.right;
            }
            node = stack.pop();
            node = node.left;
        }
        Collections.reverse(ret);  // 翻转列表
        return ret;
    }
}