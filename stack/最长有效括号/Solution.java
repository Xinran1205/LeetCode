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

// 最好的方法！ 动态规划！
class Solution2 {
    public int longestValidParentheses(String s) {
//        dp 的含义：dp[i] 表示 以下标 i 的字符作为结尾，能形成的最长有效括号子串的长度。
//        所以，一旦发现位置 i 处的字符无法与前面字符配合形成有效括号，“以 i 结尾的有效括号子串”就不存在，
//        长度只能是 0。这也是为什么我们会有很多地方直接将 dp[i] = 0，因为表示到 i 这里断了，没法形成有效串。


//        为什么“不符合要求”时要 dp[i] = 0
//        1. dp[i] 表示的是**“以 i 结尾的有效括号子串长度”**，它跟全局最大值不是一回事
//        dp[i] 只关心：“有没有一个有效括号子串正好以 i 这个字符作为结尾”。
//        如果不能以 i 这个字符结尾形成有效括号，意味着“到 i 这里”这个局部的有效括号串就断了。
//        一旦断了，这个局部（到 i）就没法贡献新的连续有效长度，所以只能标记成 0。因为从“定义”上讲，根本不存在以 i 为结尾的有效子串。
//        很多人会疑惑：“为什么不是取 dp[i-1] 的值或者别的值，而是 0？”
//
//        因为 dp[i] 只代表正好到 i 的连续有效括号长度，不是任意地方的最大值，也不是全局最大值。
//        当 i 这个位置无法构成新的有效括号时，就说明这条线到 i 就断了，只能归 0。
//
//        2. 不等于前一个，是因为有效括号串必须连续
//        如果 dp[i] 不能和前面有效部分连接起来，那你强行 dp[i] = dp[i-1] 没意义，那样就不是“以 i 结尾”了。
//        只有当 s[i] 可以和前面某些括号配对时，我们才会把前面有效子的长度加上 2（再加上之前的部分），否则就只能是 0。
//        这跟求“最长子串”或者“连续序列”的思路是一致的：中间一旦断开，当前这一位置就要从头开始。
        int ret = 0;
        if(s.length()==0){
            return 0;
        }
        int[] dp = new int[s.length()];
        dp[0] = 0;
        for(int i=1;i<s.length();i++){
            char val = s.charAt(i);
            if(val=='('){
                dp[i] = 0;
            }else{
                //val==')'
                char front = s.charAt(i-1);
                if(front=='('){
                    if(i-2>=0){
                        dp[i] = dp[i-2]+2;
                    }else{
                        dp[i] = 2;
                    }
                }else{
                    //front==')'
                    int j = i-dp[i-1]-1;
                    if(j>=0){
                        char jc = s.charAt(j);
                        if(jc=='('){
                            //如果j之前还有部分例如:()()  pre就是第一个()
                            int pre = 0;
                            if(j-1>=0){
                                pre = dp[j-1];
                            }
                            dp[i] = dp[i-1] + 2 + pre;
                        }else{
                            dp[i] = 0;
                        }
                    }else{
                        dp[i] = 0;
                    }
                }
            }
            ret = Math.max(ret,dp[i]);
        }
        return ret;
    }
}