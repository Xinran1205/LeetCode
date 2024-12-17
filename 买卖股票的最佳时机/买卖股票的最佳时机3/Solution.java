// 这一题的关键是状态分析
// 持有股票意思是：之前买了股票，现在还持有 或者 之前没有买股票，现在买了股票
// 不持有股票意思是：之前没有买股票，现在还没有买 或者 之前买了股票，现在卖了股票
// 一个5个状态
// dp[i][0]  不操作   这个可以忽略
// dp[i][1]  第一次持有
// dp[i][2]  第一次不持有
// dp[i][3]  第二次买入
// dp[i][4]  第二次不持有

// dp[i][0] = dp[i-1][0]
// dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i])
// 因为是第一次买入dp[i-1][0]-prices[i]   前一次必须要是不操作

// dp[i][2] = Math.max(dp[i-1][2],dp[i-1][1]+prices[i])
// dp[i][3] = Math.max(dp[i-1][3],dp[i-1][2]-prices[i])
// dp[i][4] = Math.max(dp[i-1][4],dp[i-1][3]+prices[i])



class Solution {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;
        for(int i=1;i<prices.length;i++){
            dp[i][0] = dp[i-1][0];
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
            dp[i][2] = Math.max(dp[i-1][2],dp[i-1][1]+prices[i]);
            dp[i][3] = Math.max(dp[i-1][3],dp[i-1][2]-prices[i]);
            dp[i][4] = Math.max(dp[i-1][4],dp[i-1][3]+prices[i]);
        }
        return dp[prices.length-1][4];
    }
}

// 小优化，dp[0][0] 可以不用
class Solution2 {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = -prices[0];
        dp[0][3] = 0;
        for(int i=1;i<prices.length;i++){
            dp[i][0] = Math.max(dp[i-1][0],-prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]+prices[i]);
            dp[i][2] = Math.max(dp[i-1][2],dp[i-1][1]-prices[i]);
            dp[i][3] = Math.max(dp[i-1][3],dp[i-1][2]+prices[i]);
        }
        return dp[prices.length-1][3];
    }
}