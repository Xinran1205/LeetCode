import java.util.*;

// 动态规划
// dp[i][j] 代表第i行第j列的值，除了头尾都是上一行的j-1和j的值之和
class Solution {
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> generate(int numRows) {
        int[][] dp = new int[numRows][numRows];
        for(int i=0;i<numRows;i++){
            dp[i][0] = 1;
            dp[i][i] = 1;
            for(int k=1;k<i;k++){
                dp[i][k] = dp[i-1][k-1]+dp[i-1][k];
            }

            List<Integer> path = new ArrayList<>();
            for(int j=0;j<=i;j++){
                path.add(dp[i][j]);
            }
            ret.add(new ArrayList<>(path));
        }
        return ret;
    }
}