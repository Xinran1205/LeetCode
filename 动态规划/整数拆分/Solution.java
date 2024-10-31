
// dp[i] 代表对i这个数的最大乘积
// 假设对i拆成两个数为i-j和j
// 假设拆成3个或多个数 就可以写为 j * dp[i-j]（相当于把i-j再拆分）
// 固定j，拆分i-j，遍历j
class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[2] = 1;
        for(int i=3;i<=n;i++){
            for(int j = 1;j<=i/2;j++){
                // 这里dp[i]也要加入求最大值的范围
                // 因为我举个例子，假如j=1，Math.max(j*(i-j),j * dp[i-j])返回了某个值，下一轮j=2，这两个值如果都比之前的小
                // 没有与dp[i]再求一次最大值，这个小的值就把之前的值覆盖了，那显然不对。
                dp[i] = Math.max(dp[i],Math.max(j*(i-j),j * dp[i-j]));
            }
        }
        return dp[n];
    }
}
