import java.util.*;
// 很简单，贪心，一遍遍历
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int max = 1;
        int cur = 1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[i-1]){
                cur++;
            }else{
                cur = 1;
            }
            max = Math.max(max,cur);
        }
        return max;
    }
}

// 动态规划，其实和上面思路很相似，但是不太一样
// 这里dp数组的含义还是dp[i]表示以下标i为结尾的最长连续递增子序列
class Solution2 {
    public int findLengthOfLCIS(int[] nums) {
        int [] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int ret = 1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[i-1]){
                dp[i] = dp[i-1]+1;
            }
            ret = Math.max(ret,dp[i]);
        }
        return ret;
    }
}