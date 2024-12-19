class Solution {
    public int numDistinct(String s, String t) {
        // 在 s 串身上 “挑选” 字符，去匹配 t 串的字符，求挑选的方式数
        // dp[i][j] 从开头到s[i-1]的子串中，出现『从开头到t[j-1]的子串』的 次数。
        // 即：前 i 个字符的  s 子串中，出现前 j 个字符的 t 子串的次数。
        int[][] dp = new int[s.length()+1][t.length()+1];

        // 当 t 是空串 (j=0)，即要匹配的目标序列为 ""，不管 s 是啥，"空子序列"总是存在的，
        // 即对于任何 s 的前 i 个字符来说，都存在一种子序列方式可以匹配 ""——那就是不选取任何字符。
        // 因此 dp[i][0] = 1。
        for(int i=0;i<s.length()+1;i++){
            dp[i][0] = 1;
        }
        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=t.length();j++){
                //当s i == t j 时，有两种情况，两种情况要相加
                if(s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }
}