
class Solution {
    public int rob(int[] nums) {
        int [] dp = new int[nums.length+2];
        // 这个我等于把数组往后移了两个，前面空出来，方便后面边界处理
        dp[0] = 0;
        dp[1] = 0;
        for(int i=2;i<=nums.length+1;i++){
            int notRob = dp[i-1];
            int yesRob = dp[i-2]+nums[i-2];
            dp[i] = Math.max(notRob,yesRob);
        }
        return dp[nums.length+1];
    }
}