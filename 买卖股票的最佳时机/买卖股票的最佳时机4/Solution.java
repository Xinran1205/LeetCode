
// 我可能智力超群，秒了！
// 就是和买卖股票3完全一样，就是做一个索引的换算

class Solution {
    public int maxProfit(int k, int[] prices) {
        int states = k*2;
        int[][] dp = new int[prices.length][states];
        for(int i=0;i<states;i++){
            if(i%2==0){
                dp[0][i] = -prices[0];
            }else if(i%2==1){
                dp[0][i] = 0;
            }
        }
        for(int i=1;i<prices.length;i++){
            for(int j=0;j<states;j++){
                int p = 0;
                if(j==0){
                    p = 0;
                }else{
                    p = dp[i-1][j-1];
                }
                if(j%2==0){
                    dp[i][j] = Math.max(dp[i-1][j],p-prices[i]);
                }else if(j%2==1){
                    dp[i][j] = Math.max(dp[i-1][j],p+prices[i]);
                }
            }
        }
        return dp[prices.length-1][states-1];
    }
}