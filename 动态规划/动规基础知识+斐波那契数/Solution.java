
//1.背包问题
//2.打家劫舍
//3.股票问题
//4.子序列问题

// 方法论：这五步思考清楚！！！
// 1.理解DP数组以及下标含义
// 2.理解递推公式
// 3.如何初始化DP数组
// 4.遍历顺序
// 5.通过打印DP数组进行调试

// 如何用动态规划的思想解决斐波那契数列
// 1.理解DP数组以及下标含义：dp[i]的定义为第i个斐波那契数
// 2.理解递推公式：dp[i] = dp[i-1] + dp[i-2]
// 3.如何初始化DP数组：dp[0] = 0, dp[1] = 1

class Solution {
    public int fib(int n) {
        if(n<2){
            return n;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}

//方法2. 递归
class Solution2 {
    public int fib(int n) {
        if(n<2){
            return n;
        }
        return fib(n-1)+fib(n-2);
    }
}
