// 方法1：贪心算法
// 能卖就卖
class Solution {
    public int maxProfit(int[] prices) {
        int sum = 0;
        int min = prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i]>min){
                sum+=(prices[i]-min);
            }
            min = prices[i];
        }
        return sum;
    }
}


//方法2.动态规划
// dp[i][0] 代表第i天持有股票的最大收益
// dp[i][1] 代表第i天不持有股票的最大收益
class Solution2 {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for(int i=1;i<prices.length;i++){
            // 这一句是唯一和上一题的区别，这里dp[i-1][1]代表之前买卖过程中余下的本金（可以这么理解）
            // 上一道题，这个本金始终是0
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]-prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]+prices[i]);
        }
        return dp[prices.length-1][1];
    }
}