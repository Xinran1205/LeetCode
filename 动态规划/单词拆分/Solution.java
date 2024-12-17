import java.util.*;
// 单词拆分，回溯算法(超时)
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 用回溯算法！
        // wordDict要选这个就得全选
        if (wordDict.size() == 0) {
            return false;
        }
        Set<String> wordSet = new HashSet<>(wordDict);
        return backtrack(s, 0, wordSet);
    }
    public boolean backtrack(String s, int start, Set<String> wordSet) {
        // 递归终止条件
        if (start == s.length()) {
            return true;
        }
        for (int i = start; i < s.length(); i++) {
            // 从start到i的子串
            String subStr = s.substring(start, i + 1);
            // 如果这个子串在wordSet中，继续递归
            if (wordSet.contains(subStr)) {
                if (backtrack(s, i + 1, wordSet)) {
                    return true;
                }
            }
        }
        return false;
    }
}

// 动规
class Solution2{
    public boolean wordBreak(String s, List<String> wordDict) {
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
        return dp[s.length()];
    }

}
