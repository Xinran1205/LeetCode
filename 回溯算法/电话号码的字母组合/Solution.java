import java.util.*;

public class Solution {
    List<String> ret = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        //通过递归实现这个digits有多少个字符
        if(digits.length() == 0){
            return ret;
        }
        //创建一个map集合，用来存储数字对应的字母
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        StringBuffer s = new StringBuffer();
        backTracing(phoneMap, digits, 0, s);
        return ret;
    }
    // index表示当前取的数字的下标
    public void backTracing(Map<Character, String> phoneMap, String digits, int index, StringBuffer s){
        if(index == digits.length()){
            ret.add(s.toString());
            return;
        }
        char digit = digits.charAt(index);
        String letters = phoneMap.get(digit);
        int lettersCount = letters.length();
        // 遍历这个数字对应的所有字母
        // 本题不需要startIndex去重
        for(int i = 0; i < lettersCount; i++) {
            s.append(letters.charAt(i));
            // 递归下一个数字
            backTracing(phoneMap, digits, index + 1, s);
            s.deleteCharAt(s.length() - 1);
        }
    }
}
