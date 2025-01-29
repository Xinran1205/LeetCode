import java.util.*;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 最孬的方法，双重递归，时间复杂度O(n^2)
class Solution {
    int ret = 0;
    public int pathSum(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        // 递归计算以当前节点为根节点的路径和
        rootSum(root, targetSum);
        // 递归计算左右子树
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return ret;
    }

    public void rootSum(TreeNode root, long targetSum) {
        if (root == null) {
            return;
        }
        targetSum = targetSum - root.val;
        if (targetSum==0) {
            ret++;
        }
        rootSum(root.left, targetSum);
        rootSum(root.right, targetSum);
    }
}


// 最好的方法！前缀和+递归，有回溯的思想！
class Solution2 {
    // 全局计数器
    private int count = 0;

    public int pathSum(TreeNode root, int targetSum) {
        // HashMap 存储前缀和及其出现次数
        Map<Long, Integer> prefixSums = new HashMap<>();
        // 初始化前缀和为0出现一次
        prefixSums.put(0L, 1);
        // 开始递归遍历
        dfs(root, 0L, targetSum, prefixSums);
        return count;
    }

    private void dfs(TreeNode node, long currentSum, int targetSum, Map<Long, Integer> prefixSums) {
        if (node == null) {
            return;
        }

        // 更新当前前缀和
        currentSum += node.val;

        // 当我们想要找「以当前节点为路径末端」且路径和为 targetSum 的路径时，只要去查看：
        // 在之前走过的前缀和中，是否存在  currentSum - targetSum。如果存在，就说明：
        // 当前路径和 currentSum 减去某个“前缀和” prefix（即 currentSum - targetSum）等于 targetSum，
        // 也就意味着从那个“前缀”位置的下一个节点，一直到当前节点的这条路径，和是 targetSum。

        // 寻找是否存在needPrefixSum 这个前缀和。
        long needPrefixSum = currentSum - targetSum;
        if (prefixSums.containsKey(needPrefixSum)) {
            count += prefixSums.get(needPrefixSum);
        }

        // 将当前前缀和加入 HashMap
        prefixSums.put(currentSum, prefixSums.getOrDefault(currentSum, 0) + 1);

        // 递归遍历左子树和右子树
        dfs(node.left, currentSum, targetSum, prefixSums);
        dfs(node.right, currentSum, targetSum, prefixSums);

        // 回溯：移除当前前缀和的计数
        prefixSums.put(currentSum, prefixSums.get(currentSum) - 1);
    }
}
