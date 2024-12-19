class Solution {
    public int countSubstrings(String s) {
        // 表示i到j的子串是否是回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = 0;
        // 不需要初始化
        // 遍历顺序其实就是从字符串末尾开始遍历，j从i开始向后遍历
        for (int i = s.length() - 1; i >= 0; i--) {
            // j>=i
            for (int j = i; j < s.length(); j++) {
                // i和j相等，且j-i<=1或者dp[i+1][j-1]为true
                if (s.charAt(i) == s.charAt(j)){
                    if(j-i<=1){
                        dp[i][j] = true;
                        count++;
                    }else if(dp[i+1][j-1]==true){
                        dp[i][j] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}