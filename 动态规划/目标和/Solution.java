class Solution {

    // 还是二维的好理解，它主要还是两个选项，选和不选，然后选为什么要加上之前，
    // 因为方法数量是选的数量+不选的数量，但是他这里就是能选必须要选！没有比较！因为统计方法
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        int left = (sum+target)/2;
        // 并且sum+target需要是偶数
        if(left<0||(sum+target)%2!=0){
            return 0;
        }

        // dp数组的含义是，对于nums数组的前i个元素，有多少种方法可以填满容量为j的背包
        int[][] dp = new int[nums.length+1][left+1];
        // 只需要初始化dp[0][0] = 1，其他都是0
        dp[0][0] = 1;
        for(int i=1;i<=nums.length;i++){
            int num = nums[i-1];
            for(int j=0;j<=left;j++){
                dp[i][j] = dp[i-1][j];
                if(num<=j){
                    dp[i][j]+=dp[i-1][j-num];
                }
            }
        }
        return dp[nums.length][left];
    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        // 公式推导：
        // left(正数) + right(负数) = sum
        // left - right = target
        if(sum < target || (target + sum) % 2 == 1) {
            return 0;
        }
        int left = (target + sum) / 2;
        // 如果计算出的 left 是负数，直接返回 0，因为无法用正整数数组来填充负数的背包
        if (left < 0) {
            return 0;
        }
        // left是正数集合的和，正数集合确定，那么负数集合也就确定了，只用计算有多少种方法填充正数集合即可
        // 给定背包大小left，求有多少种方法装满背包
        // 装满容量为left的背包有dp[left]种方法
        // 假如left = 5
        // dp[5] = dp[4] + dp[3] + dp[2] + dp[1] + dp[0]

        // 举例，已有物品重量为1，那么有dp[4]种方法凑成dp[5]
        // 已有物品重量为2，那么有dp[3]种方法凑成dp[5]

        int[] dp = new int[left+1];
        dp[0] = 1;
        for(int i = 0; i < nums.length; i++) {
            for(int j = left; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[left];
    }
}