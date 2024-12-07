// 纯粹的01背包问题
// 物品  重量  价值
// 0     1    15
// 1     3    20
// 2     4    30
// 背包容量为4
// 问：最多能装多少价值的物品

// 方法1.暴力，回溯
public class Solution {
    public static void main(String[] args) {
        int[] w = {1, 3, 4};
        int[] v = {15, 20, 30};
        int n = 3;
        int c = 4;
        System.out.println(dynamic1(w,v,n,c));
    }
    // 一维动态数组
    public static int dynamic1(int[] weight,int[] value,int numberOfThings,int capacity){
        int[] dp = new int[capacity+1];
        for(int i=0;i<numberOfThings;i++){
            //倒序
            for(int j=capacity;j>=0;j--){
                if(weight[i]<=j){
                    dp[j] = Math.max(dp[j],dp[j-weight[i]]+value[i]);
                }
            }
        }
        return dp[capacity];
    }
}