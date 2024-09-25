import java.util.ArrayDeque;
import java.util.Deque;

class solution {
    public int calculate(String s) {
        // 以后用deque,这个性能更好一些，比stack要好！
        Deque<Integer> stack = new ArrayDeque<Integer>();
        // Stack<Integer> stack = new Stack<Integer>();
        int sign = 0;
        // + * /   3个sign
        for (int i=0;i<s.length();i++){
            char character = s.charAt(i);
            // 如果是数字
            if(Character.isDigit(character)){
                int val = character-'0';
                while(i+1<s.length()&&Character.isDigit(s.charAt(i+1))){
                    val = val*10+(s.charAt(i+1)-'0');
                    i++;
                }
                if (sign == 0){
                    stack.push(val);
                }else if (sign==1){
                    int prev = stack.peek();
                    int now = prev * val;
                    stack.pop();
                    stack.push(now);
                }else if (sign==2){
                    int prev = stack.peek();
                    int now = prev / val;
                    stack.pop();
                    stack.push(now);
                }else if (sign==3){
                    stack.push(-val);
                }
            }else{
                if(character=='+'){
                    sign = 0;
                }else if(character=='*'){
                    sign = 1;
                }else if(character=='/'){
                    sign = 2;
                }else if (character=='-'){
                    sign = 3;
                }
            }
        }
        int sum = 0;
        while(!stack.isEmpty()){
            sum = sum+stack.pop();
        }
        return sum;
    }

    public static void main(String[] args) {
        solution sol = new solution();
        String s = "3+2*2+3-4/2";
        int res = sol.calculate(s);
        System.out.println(res);
    }
}
