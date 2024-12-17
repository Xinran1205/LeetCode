import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for(int i=0;i<coins.length;i++){
            for(int j=0;j<=amount;j++){
                if(coins[i]<=j){
                    // 如果某个金额 j 可以被凑成，dp[j] 会被更新为一个较小的值
                    dp[j] = Math.min(dp[j],dp[j-coins[i]]+1);
                }
            }
        }
        if(dp[amount]==amount+1){
            return -1;
        }else{
            return dp[amount];
        }
    }
}