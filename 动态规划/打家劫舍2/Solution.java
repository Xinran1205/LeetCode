import java.util.*;
class Solution {
    // 遇到环形问题，如果找不到首尾情况，建议分类讨论，展开问题！
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 考虑首元素，不考虑尾元素
        int[] dp =  new int[nums.length+1];
        int max = 0;
        dp[0] = 0;
        dp[1] = 0;
        for(int i=2;i<nums.length+1;i++){
            int notChoose = dp[i-1];
            int choose = dp[i-2]+nums[i-2];
            dp[i] = Math.max(notChoose,choose);
        }
        // 考虑尾元素，不考虑首元素
        int[] dp2 =  new int[nums.length+1];
        dp2[0] = 0;
        dp2[1] = 0;
        for(int i=2;i<nums.length+1;i++){
            int notChoose = dp2[i-1];
            int choose = dp2[i-2]+nums[i-1];
            dp2[i] = Math.max(notChoose,choose);
        }
        return Math.max(dp2[nums.length],dp[nums.length]);
    }
}

// 封装优化一下，好看。
class Solution2 {
    // 遇到环形问题，如果找不到首尾情况，建议分类讨论，展开问题！
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp1 = new int[nums.length+1];
        int[] dp2 = new int[nums.length+1];
        int[] nums1 = Arrays.copyOfRange(nums, 0, nums.length-1);
        int[] nums2 = Arrays.copyOfRange(nums, 1, nums.length);
        func(dp1,nums1);
        func(dp2,nums2);
        return Math.max(dp1[nums.length],dp2[nums.length]);
    }


    public void func(int[] dp,int[] nums){
        dp[0] = 0;
        dp[1] = 0;
        for(int i=2;i<=nums.length+1;i++){
            int notChoose = dp[i-1];
            int choose = dp[i-2]+nums[i-2];
            dp[i] = Math.max(notChoose,choose);
        }
    }
}