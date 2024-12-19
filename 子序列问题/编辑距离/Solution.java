// 这道题和两个字符串的删除操作几乎完全一样
class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        // 初始化，一个空另一个不为空就是把另一个删完
        for(int i=0;i<=word1.length();i++){
            dp[i][0] = i;
        }
        for(int i=0;i<=word2.length();i++){
            dp[0][i] = i;
        }
        for(int i=1;i<=word1.length();i++){
            for(int j=1;j<=word2.length();j++){
                // 如果相等，就不操作，操作数不变
                if(word1.charAt(i-1)==word2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    // 只有这里和上一道题不一样，首先插入和删除是一样的
                    // 替换就是更牛逼一点就是dp[i - 1][j - 1]+1。前一题没有替换只能两个都删
                    // 如果不相等，有三种情况，替换，删第一个，删第二个，求三个的最小值
                    dp[i][j] = Math.min(dp[i - 1][j - 1]+1,Math.min(dp[i-1][j]+1,dp[i][j-1]+1));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}