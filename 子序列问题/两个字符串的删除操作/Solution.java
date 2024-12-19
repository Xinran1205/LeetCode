// 巧思的神！
// 这个就是求最长公共子序列（允许不连续）
// 然后 word1.length()-max+word2.length()-max
class Solution {
    public int minDistance(String word1, String word2) {
        //匹配的最长个数，word1与最长的差+word2与最长的差
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        int max = 0;
        for(int i=1;i<=word1.length();i++){
            for(int j=1;j<=word2.length();j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
                max = Math.max(dp[i][j],max);
            }
        }

        return word1.length()-max+word2.length()-max;
    }
}

// 另一种方法，主要是为编辑距离做铺垫
class Solution2{
    public int minDistance(String word1,String word2){
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
                    // 如果不相等，有三种情况，两个都删，删第一个，删第二个，求三个的最小值
                    dp[i][j] = Math.min(dp[i-1][j-1]+2,Math.min(dp[i-1][j]+1,dp[i][j-1]+1));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}