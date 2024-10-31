
// 定义二维dp数组 表示从[0][0]到[i][j]有多少种走法
// dp[i][j] = dp[i-1][j] + dp[i][j-1]


class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // 先初始化第一行和第一列
        for(int i=0;i<n;i++){
            dp[0][i] = 1;
        }
        for(int j=0;j<m;j++){
            dp[j][0] = 1;
        }
        // 每个格子的走法等于左边格子的走法加上面格子的走法
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                dp[j][i] = dp[j-1][i] + dp[j][i-1];
            }
        }
        return dp[m-1][n-1];
    }
}
