// 双指针
class Solution {
    public boolean isSubsequence(String s, String t) {
        int i=0;
        int j=0;
        while(i<s.length()&&j<t.length()){
            if(s.charAt(i)==t.charAt(j)){
                i++;
            }
            j++;
        }
        if(i==s.length()){
            return true;
        }
        return false;
    }
}

// 动态规划
class Solution2 {
    // 回忆最长公共子序列这道题，其实就是判断长度是不是s的长度
    public boolean isSubsequence(String s, String t) {
        int max = 0;
        int[][] dp = new int[s.length()+1][t.length()+1];
        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=t.length();j++){
                if(s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
                max = Math.max(dp[i][j],max);
            }
        }
        return max==s.length();
    }
}