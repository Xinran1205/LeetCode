import java.util.*;

// 方法一，感觉挺好想的，性能也还行
class Solution {
    public String reverseWords(String s) {
        // 按顺序找出每个单词放到String[] 中
        // hello  world"（两个空格），那么 split(" ") 会生成数组
        // ["hello", "", "world"]，其中的 "" 是不需要的。
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        // 反向做成一个String
        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].equals("")) {
                sb.append(words[i]).append(" ");
            }
        }
        //trim 是 String 类的一个方法，用于删除字符串两端的空白字符，包括空格、制表符、换行符等。
        return sb.toString().trim();
    }
}

// 方法二
// 更骚的操作，但是性能没上面快
class Solution2 {
    public String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
}

// 方法三
// 对整个字符串反串，然后对每个单词反串
class Solution3{
    public String reverseWords(String s) {
        // 先反转整个字符串
        s = reverse(s);
        // 再反转每个单词
        StringBuilder sb = new StringBuilder();
        // 双指针去除空格，空间复杂度O(1)
        int i = 0;
        int j = 0;
        while (i < s.length()) {
            while (i < s.length() && s.charAt(i) == ' ') {
                i++;
            }
            j = i;
            while (j < s.length() && s.charAt(j) != ' ') {
                j++;
            }
            // i 到 j 之间是一个单词
            if (i < s.length()) {
                String word = s.substring(i, j);
                sb.append(reverse(word)).append(" ");
                i = j;
            }
        }
        return sb.toString().trim();
    }
    private String reverse(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        return new String(chars);
    }
}
