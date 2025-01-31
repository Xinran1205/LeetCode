class Solution {
    public int maxProduct(int[] nums) {
        // 以i结尾的最大乘积子数组
        int[] dp = new int[nums.length];
        // 以i结尾的最小乘积子数组
        int[] mindp = new int[nums.length];
        dp[0] = nums[0];
        mindp[0] = nums[0];
        int max = nums[0];
        for(int i=1;i<nums.length;i++){
            // 这里还是有点绕！
            // 这样一来，无论 nums[i] 是正数还是负数，
            // dp[i] 和 mindp[i] 都会正确地反映以当前位置结尾的最大和最小乘积，从而确保全局最大值能够被正确地找到。
            dp[i] = Math.max(Math.max(nums[i],dp[i-1]*nums[i]),mindp[i-1]*nums[i]);
            mindp[i] = Math.min(Math.min(nums[i],mindp[i-1]*nums[i]),dp[i-1]*nums[i]);
            max = Math.max(max,dp[i]);
        }
        return max;
    }
}

// 小优化内存，其实优化不了多少
class Solution2 {
    public int maxProduct(int[] nums) {
        int dppre = nums[0];
        int mindppre = nums[0];
        int max = nums[0];
        for(int i=1;i<nums.length;i++){
            int dp = Math.max(Math.max(nums[i],dppre*nums[i]),mindppre*nums[i]);
            int mindp = Math.min(Math.min(nums[i],mindppre*nums[i]),dppre*nums[i]);
            max = Math.max(max,dp);
            dppre = dp;
            mindppre = mindp;
        }
        return max;
    }
}