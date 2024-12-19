class Solution {
    public String longestPalindrome(String s) {
        // 表示i到j的子串是否是回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        // 遍历顺序是从左下角
        // 从下往上，从左往右
        int start = 0;
        int maxLen = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            // j>=i
            for (int j = i; j < s.length(); j++) {
                // i和j相等，且j-i<=1或者dp[i+1][j-1]为true
                if (s.charAt(i) == s.charAt(j)){
                    if(j-i<=1){
                        dp[i][j] = true;
                    }else if(dp[i+1][j-1]==true){
                        dp[i][j] = true;
                    }
                }
                // 主要是多一个这个判断，如果当前是回文数，并且长度大于之前的最大长度
                // 那么更新最大长度和起始位置
                if(dp[i][j]&&j-i+1>maxLen){
                    maxLen = j-i+1;
                    start = i;
                }
            }
        }
        return s.substring(start,start+maxLen);
    }
}