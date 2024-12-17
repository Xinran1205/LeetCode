import java.util.Arrays;
class Solution {
    public int numSquares(int n) {
        // 完全背包问题，正序遍历！求最少，一般全部初始化成最大
        // n是背包
        // 完全平方数是物品
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1;i*i<=n;i++){
            for(int j=0;j<=n;j++){
                if(i*i<=j){
                    dp[j] = Math.min(dp[j],dp[j-i*i]+1);
                }
            }
        }
        return dp[n];
    }
}