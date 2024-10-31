// 中序遍历结果：[9, 3, 15, 20, 7]  左根右
// 后序遍历结果：[9, 15, 7, 20, 3]  左右根

// 所以后序遍历的最后一个元素是根节点
// 1. 在后序中找到中节点
// 2. 在中序中找到中节点的位置，左边是左子树，右边是右子树（完成切割）
// 3. 这样又知道后序怎么切割了，然后再在后序中找到左右子树的中节点，然后在中序中找到左右子树的位置，如此递归

// 六步
// 1. 后序数组为0，空节点
// 2. 后序数组的最后一个元素为节点元素
// 3. 寻找中序数组中节点位置作为切割点
// 4. 切中序数组
// 5. 切后序数组
// 6. 递归

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int nodeVal = postorder[postorder.length-1];
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
        // 把后序数组分成两部分
        // 这里直接分，因为后序数组前面的部分是左子树，大小就是前面算出的中序数组index大小
        int[] leftPostorder = new int[index];
        int[] rightPostorder = new int[postorder.length-index-1];
        for(int i=0;i<index;i++){
            leftPostorder[i] = postorder[i];
        }
        for(int i=index;i<postorder.length-1;i++){
            rightPostorder[i-index] = postorder[i];
        }
        // 递归
        if(leftInorder.length>0){
            node.left = buildTree(leftInorder,leftPostorder);
        }
        if(rightInorder.length>0){
            node.right = buildTree(rightInorder,rightPostorder);
        }
        return node;
    }
}

// 这个性能更好一些，思路完全一样，只是避免了新创建数组以及数组的拷贝！！！通过数组的下标来实现

class Solution2 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(postorder.length == 0 || inorder.length == 0)
            return null;
        return buildHelper(inorder, 0, inorder.length, postorder, 0, postorder.length);

    }
    private TreeNode buildHelper(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postorderStart, int postorderEnd){
        if(postorderStart == postorderEnd)
            return null;
        int rootVal = postorder[postorderEnd - 1];
        TreeNode root = new TreeNode(rootVal);
        int middleIndex;
        for (middleIndex = inorderStart; middleIndex < inorderEnd; middleIndex++){
            if(inorder[middleIndex] == rootVal)
                break;
        }

        int leftInorderStart = inorderStart;
        int leftInorderEnd = middleIndex;
        int rightInorderStart = middleIndex + 1;
        int rightInorderEnd = inorderEnd;


        int leftPostorderStart = postorderStart;
        int leftPostorderEnd = postorderStart + (middleIndex - inorderStart);
        int rightPostorderStart = leftPostorderEnd;
        int rightPostorderEnd = postorderEnd - 1;
        root.left = buildHelper(inorder, leftInorderStart, leftInorderEnd,  postorder, leftPostorderStart, leftPostorderEnd);
        root.right = buildHelper(inorder, rightInorderStart, rightInorderEnd, postorder, rightPostorderStart, rightPostorderEnd);

        return root;
    }
}
