// 爬楼梯
// 1. Dp数组含义：dp[i]表示爬到第i阶楼梯的方法数


// 标签：动态规划
//本问题其实常规解法可以分成多个子问题，爬第n阶楼梯的方法数量，等于 2 部分之和
//
//爬上 n−1 阶楼梯的方法数量。因为再爬1阶就能到第n阶
//爬上 n−2 阶楼梯的方法数量，因为再爬2阶就能到第n阶

// 也就是斐波那契数列
class Solution {
    public int climbStairs(int n) {
        if(n<=2){
            return n;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
