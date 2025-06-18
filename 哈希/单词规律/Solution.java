import java.util.*;

// 这一题不简单，主要涉及了一个双向匹配的问题
public class Solution {
    public boolean wordPattern(String pattern, String s) {
        // 1. 先把 s 按空格拆成单词数组
        String[] words = s.split(" ");
        // 长度不同，不可能一一对应
        if (words.length != pattern.length()) {
            return false;
        }

        // 2. 建立双向映射：char -> word 和 word -> char
        // ch是abba的a例如
        Map<Character, String> c2w = new HashMap<>();
        Map<String, Character> w2c = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String word = words[i];

            // 如果字符 ch 还没映射过
            if (!c2w.containsKey(ch)) {
                // 但 word 已经被其它字符占用了，冲突
                if (w2c.containsKey(word)) {
                    return false;
                }
                // 建立新映射
                c2w.put(ch, word);
                w2c.put(word, ch);

            } else {
                // ch 已有映射，检查映射到的单词是否与当前单词一致
                String mapped = c2w.get(ch);
                if (!mapped.equals(word)) {
                    return false;
                }
            }
        }

        // 全部检查完毕，未发现冲突
        return true;
    }
}