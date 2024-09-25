import java.util.Stack;

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int sum = 0;
        int sign = 1;
        for(int i=0;i<s.length();i++){
            char character = s.charAt(i);
            // 如果是数字
            if (Character.isDigit(character)){
                int val = character - '0';
                while(i+1<s.length()&&Character.isDigit(s.charAt(i+1))){
                    i++;
                    val = val*10+(s.charAt(i)-'0');
                }
                sum = sum + sign*val;
            }else if(character=='+'){
                sign = 1;
            }else if(character=='-'){
                sign = -1;
            }else if(character=='('){
                // 括号前的sum和符号放进去
                stack.push(sum);
                stack.push(sign);
                // 重置sum和sign，此时在遇到')'前，sum都是括号内的
                sign = 1;
                sum = 0;
            }else if(character==')'){
                int preSign = stack.pop();
                int preVal = stack.pop();
                sum = preVal + preSign*sum;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.calculate("1 + 1"));
        System.out.println(solution.calculate(" 2-1 + 2 "));
        System.out.println(solution.calculate("(1-(4+5+2)-3)+(6+8)"));
    }
}
