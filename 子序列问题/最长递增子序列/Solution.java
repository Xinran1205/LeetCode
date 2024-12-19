import java.util.*;

class Solution {
    public int lengthOfLIS(int[] nums) {
        // dp数组含义是以下标i为结尾的最长递增子序列，所以结果不是在dp[nums.length-1]中
        // 需要求整个数组的最大值
        int [] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int ret = 1;
        for(int i=1;i<nums.length;i++){
            // 这里遍历之前所有的，就不会有遗漏
            for(int j=i-1;j>=0;j--){
                if(nums[j]<nums[i]){
                    dp[i] = Math.max(dp[j]+1,dp[i]);
                }
            }
            ret = Math.max(ret,dp[i]);
        }
        return ret;
    }
}