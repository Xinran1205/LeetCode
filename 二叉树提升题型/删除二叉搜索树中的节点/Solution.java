
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    // 五种情况
    // 1.没找到要删除的节点
    // 2.节点是叶子节点（左右都为空）
    // 3.要删的节点左不空，右为空
    // 4.要删的节点左为空，右不空
    // 5.左右都不为空 (可以替换成左孩子也可以右孩子，建议右孩子)
    // 5.1 替换逻辑，那得把左子树接到右子树中最左下角的节点下面，

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null){
            return null;
        }
        //如果找到要删除的节点
        if(root.val == key){
            if(root.left==null&&root.right==null){
                return null;
            }else if(root.left!=null&&root.right==null){
                return root.left;
            }else if(root.left==null&&root.right!=null){
                return root.right;
            }else{
                TreeNode cur = root.right;
                while(cur.left!=null){
                    cur = cur.left;
                }
                cur.left = root.left;
                return root.right;
            }
        }
        // 不相等他就不会处理，所以不用担心修改其他节点的结构
        if(root.val>key){
            root.left = deleteNode(root.left,key);
        }else{
            root.right = deleteNode(root.right,key);
        }
        // 下面这个写法也可以，上面是优化写法
        // root.left = deleteNode(root.left,key);
        // root.right = deleteNode(root.right,key);
        return root;
    }
}