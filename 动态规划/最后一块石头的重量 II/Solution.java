class Solution {
    public int lastStoneWeightII(int[] stones) {
        // 和分割等和子集非常类似
        // 尽可能的把石头分为两堆然后相撞
        int sum = 0;
        for(int i=0;i<stones.length;i++){
            sum += stones[i];
        }
        int target = sum/2;
        int[] dp = new int[target+1];
        for(int i=0;i<stones.length;i++){
            for(int j=target;j>=1;j--){
                if(stones[i]<=j){
                    dp[j] = Math.max(dp[j],dp[j-stones[i]]+stones[i]);
                }
            }
        }
        // 因为target是向下取整的
        // 所以另一边sum-dp[target]肯定是大于等于dp[target]的
        return sum-2*dp[target];
    }
}