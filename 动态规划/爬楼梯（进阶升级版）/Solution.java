//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
//每次你可以爬至多m (1 <= m < n)个台阶。你有多少种不同的方法可以爬到楼顶呢？
//
//注意：给定 n 是一个正整数。
//
//输入描述：输入共一行，包含两个正整数，分别表示n, m
//
//输出描述：输出一个整数，表示爬到楼顶的方法数。
//
//输入示例：3 2
//
//输出示例：3
//
//提示：
//
//当 m = 2，n = 3 时，n = 3 这表示一共有三个台阶，m = 2 代表你每次可以爬一个台阶或者两个台阶。


// 每次可以爬 1 、 2、.....、m 个台阶。问有多少种不同的方法可以爬到楼顶呢？
// 一共有 n 个台阶。

import java.util.*;

public class Solution{
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println(climbStairs(n,m));
        /* code */
    }
    public static int climbStairs(int n, int m) {
        if (n <= 0 || m <= 0) {
            return 0;
        }
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int j=1;j<=n;j++){
            for(int i=0;i<=m;i++){
                if(i<=j){
                    // 见到这种公式，就是求有多少种方法
                    dp[j] += dp[j-i];
                }
            }
        }
        return dp[n];
    }
}
