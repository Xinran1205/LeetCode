// 我用的贪心的思路解的
class Solution {
    public int maxSubArray(int[] nums) {
        int cur = nums[0];
        int max = nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i]>cur&&cur<0){
                cur = nums[i];
            }else{
                cur +=nums[i];
            }
            max = Math.max(cur,max);
        }
        return max;
    }
}

// 动态规划，这个感觉也非常好理解！
class Solution2 {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        //dp[i]代表以i结尾的最大子数组和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            // 也是和上面思路有点类似，两种状态
            // 1.加上这个数，2.直接从这个数开始
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
            max = Math.max(max,dp[i]);
        }
        return max;
    }
}