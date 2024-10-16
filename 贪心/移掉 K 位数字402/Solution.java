import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<Character>();
        for(int i=0;i<num.length();i++){
            while(!deque.isEmpty()&&k>0&&deque.peekLast()>num.charAt(i)){
                // 这个换成removeLast()也可以
                deque.pollLast();
                k--;
            }
            // 这个换成offer也可以
            deque.offerLast(num.charAt(i));
        }
        // 这个情况是如果此时k不等于，那么还要删，就从最小位开始删就可以
        for(int i=0;i<k;i++){
            deque.pollLast();
        }

        StringBuilder ret = new StringBuilder();
        Boolean isHead = true;
        while(!deque.isEmpty()){
            char digit = deque.pollFirst();
            if(isHead&&digit=='0'){
                continue;
            }
            isHead = false;
            ret.append(digit);
        }
        if(ret.length()==0){
            return "0";
        }else{
            return ret.toString();
        }
    }
}