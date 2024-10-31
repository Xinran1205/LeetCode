
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int nodeVal = preorder[0];
        TreeNode node = new TreeNode(nodeVal);
        // 在中序数组中找到中节点的位置
        int index = 0;
        for(;index<inorder.length;index++){
            if(inorder[index]==nodeVal){
                break;
            }
        }
        // 此时index的值为中节点在中序数组中的位置
        // 把中序数组分成两部分
        int[] leftInorder = new int[index];
        int[] rightInorder = new int[inorder.length-index-1];
        for(int i=0;i<index;i++){
            leftInorder[i] = inorder[i];
        }
        for(int i=index+1;i<inorder.length;i++){
            rightInorder[i-index-1] = inorder[i];
        }
        // 把前序数组分成两部分
        int[] leftPreorder = new int[index];
        int[] rightPreorder = new int[preorder.length-index-1];
        for(int i=0;i<index;i++){
            leftPreorder[i] = preorder[i+1];
        }
        for(int i=index;i<preorder.length-1;i++){
            rightPreorder[i-index] = preorder[i+1];
        }
        // 递归
        if(leftInorder.length>0){
            node.left = buildTree(leftPreorder,leftInorder);
        }
        if(rightInorder.length>0){
            node.right = buildTree(rightPreorder,rightInorder);
        }
        return node;
    }
}


class Solution2 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree2(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTree2(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if(preStart>=preEnd){
            return null;
        }
        TreeNode node = new TreeNode(preorder[preStart]);
        int index = inStart;
        for(;index<inEnd;index++){
            if(inorder[index]==preorder[preStart]){
                break;
            }
        }
        node.left = buildTree2(preorder,preStart+1,preStart+index-inStart+1,inorder,inStart,index);
        node.right = buildTree2(preorder,preStart+index-inStart+1,preEnd,inorder,index+1,inEnd);
        return node;
    }
}