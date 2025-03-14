// 这个比之前的零钱兑换要简单
class Solution {
    public int change(int amount, int[] coins) {

        // 这种计算个数的，遍历顺序非常固定！
        // 如果计算排列（考虑不同顺序）必须先背包后物品
        // 本体是计算组合（不考虑不同顺序）必须先物品后背包

        // dp数组的含义：dp[i]表示凑成总金额为i的组合数
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int i=0;i<coins.length;i++){
            for(int j=0;j<=amount;j++){
                if(coins[i]<=j){
                    // 见到这种公式，就是求有多少种方法
                    dp[j]+=dp[j-coins[i]];
                }
            }
        }
        return dp[amount];
    }
}