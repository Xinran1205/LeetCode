// 这道题的目标 是尽可能选择最多数量的字符串
// 这一题是3维的背包问题，dp[i][k][p]表示前i个字符串，0的个数小于等于k，1的个数小于等于p的最大子集的大小

// 遍历每个字符串，计算当前字符串的0和1的个数，然后遍历每个k和p，
// 如果当前字符串的0和1的个数小于等于k和p，那么dp[i][k][p] = dp[i-1][k-zeroNumber][p-oneNumber]+1

// 这道题就是理解为有两个背包，一个大小是m，一个大小是n
// 求最多有多少个物品（字符串），这一个字符串需要把想象成一个整体，要么全选，要么全不选

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length+1][m+1][n+1];
        for(int i=1;i<strs.length+1;i++){
            int len = strs[i-1].length();
            int zeroNumber = 0;
            int oneNumber = 0;
            for(int j=0;j<len;j++){
                if(strs[i-1].charAt(j)=='0'){
                    zeroNumber++;
                }else{
                    oneNumber++;
                }
            }
            for(int k=0;k<=m;k++){
                for(int p=0;p<=n;p++){
                    dp[i][k][p] = dp[i-1][k][p];
                    if(zeroNumber<=k&&oneNumber<=p){
                        dp[i][k][p] = Math.max(dp[i][k][p],dp[i-1][k-zeroNumber][p-oneNumber]+1);
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }
}