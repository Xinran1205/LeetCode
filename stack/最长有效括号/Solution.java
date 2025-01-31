import java.util.*;
class Solution {
    public int longestValidParentheses(String s) {
        // 具体做法是我们始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        // 如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中，
        // 这样就不满足提及的「最后一个没有被匹配的右括号的下标」，
        // 为了保持统一，我们在一开始的时候往栈中放入一个值为 −1 的元素
        stack.push(-1); // 初始标记

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            } else {
                stack.pop();
                // 如果栈为空，说明当前的右括号为没有被匹配的右括号，
                // 我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
                if(stack.isEmpty()){
                    stack.push(i);
                } else {
                    // 如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
                    max = Math.max(max, i - stack.peek());
                }
            }
        }

        return max;
    }
}
