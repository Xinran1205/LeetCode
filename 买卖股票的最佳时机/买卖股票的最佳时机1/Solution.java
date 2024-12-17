// 这个方法就是每天就当这一天卖出，记录之前的最小的值
class Solution {
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int max = 0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]-min>max){
                max = prices[i]-min;
            }
            min = Math.min(min,prices[i]);
        }
        return max;
    }
}

// 动态规划！
// 这个理解需要一定的思考
// 持有股票意思是：之前买了股票，现在还持有 或者 之前没有买股票，现在买了股票
// 不持有股票意思是：之前没有买股票，现在还没有买 或者 之前买了股票，现在卖了股票
// dp[i][0] 代表第i天持有股票的最大收益
// dp[i][1] 代表第i天不持有股票的最大收益
class Solution2 {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for(int i=1;i<prices.length;i++){
            // 这个地方写一个0很好，更直观，代表这个时候手上是0元
            dp[i][0] = Math.max(dp[i-1][0],0-prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]+prices[i]);
        }
        // 这里仔细思考，不需要求max
        return dp[prices.length-1][1];
//        return Math.max(dp[prices.length-1][0],dp[prices.length-1][1]);
    }
}