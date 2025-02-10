// 最长公共子序列  不相交的线

//不相交的线
//题意里说：有两个数组 A、B，我们要连线，要求每个连线都连相等的数字，并且这些线不能相交。
//如果仔细想，就会发现“不能相交”意味着如果 A 中的某个元素 A[i] 和 B 中的元素 B[j] 连上线，那么在 A 中 i 的顺序和在 B 中 j 的顺序必须一致，不可以出现 i < i' 但 j > j' 的交叉情况。
//这正好跟“子序列”这个概念所要求的相对顺序保持一致是一样的。
//进一步地，A[i] == B[j] 还要求元素值要对应相等，这与 LCS 中“如果最后一个元素相等，就可以往前继续匹配”的逻辑类似。
//因此，“不相交的线”在本质上就是要找在这两个数组里相同数字最多但顺序又一致的匹配数量。换言之，就是最长公共子序列的长度。

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        int max = 0;
        for(int i=1;i<=text1.length();i++){
            for(int j=1;j<=text2.length();j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    // 这里非常重要！需要仔细思考！纸上画一下就很明显了
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
                max = Math.max(dp[i][j],max);
            }
        }
        return max;
    }
}