import java.util.ArrayList;
import java.util.List;

class Solution {
    List<List<String>> ret = new ArrayList<>();
    List<String> path = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backTracing(s, 0);
        return ret;
    }

    public void backTracing(String s, int startIndex) {
        // 到叶子节点就收集，也可以把判断回文放这里
        if (startIndex >= s.length()) {
            ret.add(new ArrayList<>(path));
            return;
        }

        // 把判断是否回文的逻辑放在for循环
        for (int i = startIndex; i < s.length(); i++) {
            if (isPalindrome(s, startIndex, i)) { // 注意这里参数传递需要两个索引
                // 获取[startIndex, i]在s中的子串
                path.add(s.substring(startIndex, i + 1));
                backTracing(s, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    // 判断字符串s中从start到end的子串是否是回文
    public boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
