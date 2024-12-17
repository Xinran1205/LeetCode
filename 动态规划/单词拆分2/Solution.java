import java.util.*;

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        // 直接把上一题的代码放在这！!!
        // s是背包，wordDict是物品，问能否用物品正好填满背包，每个物品可以使用多次
        // dp[i]表示s的前i个字符能否用wordDict中的单词表示  （i是长度）
        boolean dp[] = new boolean[s.length() + 1];
        dp[0] = true;
        // 这个遍历顺序太牛逼了，物品的顺序是有要求的！所以是排列问题！就是和题目组合总和4一样
        for (int i = 1; i <= s.length(); i++) {
            // 这里非常有意思，这里为什么是遍历物品呢？
            // 因为我们是截取s的前i个字符，然后看这个子串能否用wordDict中的单词表示
            for (int j = 0; j < i; j++) {
                // 如果前j个字符可以用wordDict中的单词表示，且s[j, i - 1]在wordDict中
                // 那么前i个字符可以用wordDict中的单词表示
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        int len = s.length();
        // 第 2 步：回溯算法搜索所有符合条件的解
        List<String> res = new ArrayList<>();
        if (dp[len]) {
            Deque<String> path = new ArrayDeque<>();
            dfs(s, len, wordDict, dp, path, res);
            return res;
        }
        return res;
    }
    private void dfs(String s, int len, List<String> wordSet, boolean[] dp, Deque<String> path, List<String> res) {
        if (len == 0) {
            res.add(String.join(" ",path));
            return;
        }

        // 可以拆分的左边界从 len - 1 依次枚举到 0
        for (int i = len - 1; i >= 0; i--) {
            String suffix = s.substring(i, len);
            if (wordSet.contains(suffix) && dp[i]) {
                path.addFirst(suffix);
                dfs(s, i, wordSet, dp, path, res);
                path.removeFirst();
            }
        }
    }
}