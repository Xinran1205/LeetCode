// 这道题和两个字符串的删除操作几乎完全一样
class Solution {
    public int minDistance(String word1, String word2) {
        // dp[i][j] 表示：把 word1 的前 i 个字符转换成 word2 的前 j 个字符的最小编辑距离。
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


//                    为什么「删除」会是 dp[i-1][j] + 1？
//                    2.1 含义：从 word1 的前 i 个字符里删掉最后一个字符
//                    你此刻正在考虑 dp[i][j]，也就是说想让 word1[0..i-1]（长度 i）变成 word2[0..j-1]（长度 j）。
//                    如果你决定“删除 word1[i-1] 这个字符”，那么 word1 的长度从 i 变成 i-1，最后一个字符就被干掉了。
//                    这样一来，你需要解决的子问题就变成了 “把 word1 的前 i-1 个字符变成 word2 的前 j 个字符”，也就是 dp[i-1][j]。
//                    但是别忘了，你刚刚做了一次“删除”操作，所以总的操作次数要 +1。
                    // 插入同理
                    dp[i][j] = Math.min(dp[i - 1][j - 1]+1,Math.min(dp[i-1][j]+1,dp[i][j-1]+1));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}