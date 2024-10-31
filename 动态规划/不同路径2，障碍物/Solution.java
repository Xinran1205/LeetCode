class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 先初始化第一行和第一列
        // 如果左边有1，那么这一行都为0,同理行也是
        // 主要改动初始化的逻辑
        for(int p = 0;p<n;p++){
            if(obstacleGrid[0][p]==0){
                dp[0][p] = 1;
            }else{
                // 直接break，后面不用初始化了，默认为0
                break;
            }
        }
        for(int q=0;q<m;q++){
            if(obstacleGrid[q][0]==0){
                dp[q][0] = 1;
            }else{
                break;
            }
        }

        // 每个格子的走法等于左边格子的走法加上面格子的走法
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(obstacleGrid[j][i]==0){
                    dp[j][i] = dp[j-1][i] + dp[j][i-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}