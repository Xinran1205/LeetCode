// dp[i] 到达i位置最小花费为dp[i]  （到达i位置可以从i-1位置或者i-2位置）并且到达i不需要i位置的花费
// dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2])

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // 这里其实我们返回的顶楼是比cost数组多一个位置的
        int[] dp = new int[cost.length+1];
        // 初始化为0
        dp[0] = 0;
        dp[1] = 0;
        for(int i=2;i<=cost.length;i++){
            dp[i] = Math.min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2]);
        }
        return dp[cost.length];
    }
}
