import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    int ret = 0;
    public int minCameraCover(TreeNode root) {
        // 优先叶子节点之上的节点，在叶子节点放会浪费

        // 后序遍历，并且每隔两个节点放个摄像头

        // 节点有3个状态，无覆盖0 有摄像头1 有覆盖2   不需要无摄像头的状态，无摄像头不是1就是2
        // 空节点归于是有覆盖的状态

        // 对于非叶子节点，只有这三种情况
        // 1.左右孩子都有覆盖，父节点为状态0
        // 2.左右至少有一个有无覆盖，父节点为摄像头
        // 3.左右至少有一个是摄像头，父节点为有覆盖

        //4. 根节点得单独判断，如果根结点无覆盖，得加一个摄像头

        if(root == null){
            return 0;
        }
        int rootState = recursive(root);
        if(rootState == 0){
            ret++;
        }
        return ret;
    }

    public int recursive(TreeNode node){
        if(node == null){
            return 2;
        }
        int left = recursive(node.left);
        int right = recursive(node.right);
        if(left == 0 || right == 0){
            ret++;
            return 1;
        }
        if(left == 1 || right == 1){
            return 2;
        }
        return 0;
    }
}