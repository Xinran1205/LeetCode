// 01背包问题
// 有N种物品，每种物品只有一个

// 完全背包问题
// 有N种物品，每种物品有无限个

// 多重背包问题（不常见）
// 有N种物品，每种物品有有限个（各不相同）



// 纯粹的01背包问题
// 物品  重量  价值
// 0     1    15
// 1     3    20
// 2     4    30
// 背包容量为4
// 问：最多能装多少价值的物品

// 方法1.暴力，回溯
public class Solution {
    public static void main(String[] args) {
        int[] w = {1, 3, 4};
        int[] v = {15, 20, 30};
        int n = 3;
        int c = 4;
        // 参数：物品重量，物品价值，物品数量，背包容量，当前物品索引，当前价值
        System.out.println(backtrack(w, v, n, c, 0, 0));
    }

    // 对于每个物品，都有两种选择：选择包含当前物品，选择不包含当前物品
    // 时间复杂度：O(2^n)
    public static int backtrack(int[] w, int[] v, int n, int c, int index, int sum) {
        if (index == n || c == 0) {
            return sum;
        }
        int res1 = 0;
        // 选择包含当前物品
        if (c >= w[index]) {
            res1 = backtrack(w, v, n, c - w[index], index + 1, sum + v[index]);
        }
        // 选择不包含当前物品
        int res2 = backtrack(w, v, n, c, index + 1, sum);
        return Math.max(res1, res2);
    }
}

// 方法2.动态规划，二维dp数组（后续可以优化为一维dp数组）
class Solution2 {
    public static void main(String[] args) {
        int[] w = {1, 3, 4};
        int[] v = {15, 20, 30};
        int n = 3;
        int c = 4;
        System.out.println(dp(w, v, n, c));
    }

    // 时间复杂度：O(n * c)
    // dp[i][j] 表示当有前 i 个物品可选(没有全选，只是可选），且背包容量为 j 时的最大价值
    // 如果不选择第 i 个物品：dp[i][j] = dp[i-1][j]
    // 如果选择第 i 个物品：dp[i][j] = dp[i-1][j-w[i]] + v[i]

    public static int dp(int[] weight,int[] value,int numberOfThings, int capacity){
        int[][] dp = new int[numberOfThings][capacity+1];
        // 初始化第一行
        for(int i=0;i<capacity+1;i++){
            if(weight[0]<=i){
                dp[0][i] = value[0];
            }
        }
        // 第一列其他语言需要初始化，Java不需要，全为0
        // 从第二行开始，行索引是物品
        for(int i=1;i<numberOfThings;i++){
            // 列索引是背包容量，遍历这一行
            // 从0或1开始都可以
            for(int j=1;j<=capacity;j++){
                // 不选择当前物品，初始化为上一行的值
                dp[i][j] = dp[i-1][j];
                // 尝试选择当前物品 （根据max选择最大值）
                if(weight[i]<=j){
                    // 比较不选择当前物品（dp[i][j]）和选择当前物品的价值
                    // dp[i - 1][j - w[i]] 可以理解为预留出当前物品的重量
                    // 因为v[i] + dp[i - 1][j - w[i]] 表示选择当前物品的价值
                    dp[i][j] = Math.max(dp[i][j],value[i]+dp[i-1][j-weight[i]]);
                }
            }
        }
        return dp[numberOfThings-1][capacity];
    }
}

//方法3.动态规划，一维dp数组，滚动数组
class Solution3{
    public static void main(String[] args) {
        int[] w = {1, 3, 4};
        int[] v = {15, 20, 30};
        int n = 3;
        int c = 4;
        System.out.println(dp(w, v, n, c));
    }

    // 时间复杂度：O(n * c)
    // dp[j] 表示背包容量为 j 时，可以获得的最大价值。
    // 如果不选择第 i 个物品：dp[j] = dp[j]
    // 如果选择第 i 个物品：dp[j] = dp[j-w[i]] + v[i]
    public static int dp(int[] weight,int[] value,int numberOfThings, int capacity){
        int[] dp = new int[capacity+1];
        // 不需要初始化，默认为0
        // 索引是物品
        for(int i=0;i<numberOfThings;i++){
            // 一定要倒序
            // 思考一下如果是正序，dp[j] = dp[j] + value[i]，这样就会重复选择物品
            // 举例：比如背包容量为2时，dp[2] = dp[1] + value[0] = 30 (选择了两次第0个物品)
            // 详细解释：在更新 dp[j] 时，我们需要确保使用的是不包含当前物品的旧值。倒序遍历可以保证在计算 dp[j] 时，
            // dp[j-w[i]] 仍然是不包含当前物品 i 的状态。
            // 如果是正序遍历，一旦 dp[j] 被更新，较大的 j 值在后续迭代中可能会错误地重复使用同一物品，
            // 因为它们会使用已经考虑了当前物品的 dp[j-w[i]] 值。

            // 在二维数组中，两层数据是隔离开的，所以正序和倒序都可以，并且先遍历背包再遍历物品也可以
            // 但是一维只能倒序并且必须先遍历物品再遍历背包
            for(int j=capacity;j>=1;j--){
                // 不放物品i就是dp[j]
                // 尝试选择当前物品 （根据max选择最大值）
                if(weight[i]<=j){
                    dp[j] = Math.max(dp[j],value[i]+dp[j-weight[i]]);
                }
            }
        }
        return dp[capacity];
    }
}