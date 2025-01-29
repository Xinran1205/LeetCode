import java.util.*;
class Solution {
    List<String> ret;
    public List<String> generateParenthesis(int n) {
        ret = new ArrayList<>();
        backTracing(n,new StringBuilder(),0,0);
        return ret;
    }
    public void backTracing(int n, StringBuilder  cur, int usedOpen, int usedClosed){
        if(usedOpen==n&&usedClosed==n){
            ret.add(cur.toString());
            return;
        }
        if(usedOpen<n){
            cur.append('(');
            backTracing(n, cur, usedOpen+1, usedClosed);
            // 回溯，删除最后一个字符
            cur.deleteCharAt(cur.length() - 1);
        }
        // 这里要注意，只有当已经使用的左括号大于已经使用的右括号时，才能使用右括号
        if(usedClosed<usedOpen){
            cur.append(')');
            backTracing(n, cur, usedOpen, usedClosed+1);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}