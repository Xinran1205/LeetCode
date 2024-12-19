// 秒了，主要是第一个持有的时候，原来是dp[i-1][1] - prices[i] （前面赚的钱减去当前的价格）
// 现在因为要隔一个交易，所以是dp[i-2][1] - prices[i]

class Solution {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for(int i=1;i<prices.length;i++){
            int k;
            if(i<2){
                k = 0;
            }else{
                k = dp[i-2][1];
            }
            dp[i][0] = Math.max(dp[i-1][0],k-prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]+prices[i]);
        }
        return dp[prices.length-1][1];
    }
}