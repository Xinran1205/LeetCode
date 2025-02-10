class Solution {
    public boolean canPartition(int[] nums) {
        // 太封神了
        // 这道题可以换一种表述：给定一个只包含正整数的非空数组 nums[]，
        // 判断是否可以从数组中选出一些数字，使得这些数字的和等于整个数组的元素和的一半。

        // 判断是否能装满容量为sum/2的背包
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        if(sum%2!=0){
            return false;
        }
        int target = sum/2;

        // dp[j] 表示在遍历过数组中某些数字后，在不超过容量 j 的前提下，能够凑出的最大的数字之和是多少。
        int[] dp = new int[target+1];
        for(int i=0;i<nums.length;i++){
            for(int j=target;j>=1;j--){
                if(nums[i]<=j){
                    dp[j] = Math.max(dp[j],nums[i]+dp[j-nums[i]]);
                }
            }
        }
        return dp[target]==target;
    }
}