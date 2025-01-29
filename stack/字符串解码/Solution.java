import java.util.*;

// 这一题的关键是，思路是第一，如何使用栈
// 第二个关键的是性能！ 首先一定要使用deque代替栈，其次一定要优先使用StringBuilder代替string

class Solution {
    public String decodeString(String s) {
        Deque<Integer> kStack = new ArrayDeque<>();
        Deque<StringBuilder> stringStack = new ArrayDeque<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        for(int i=0;i<s.length();i++){
            char val = s.charAt(i);
            if(Character.isDigit(val)){
                k = k*10 + val-'0';
            }else if(val=='['){
                kStack.push(k);
                stringStack.push(currentString);
                k = 0;
                currentString = new StringBuilder();
            }else if(val==']'){
                int repeat = kStack.pop();
                StringBuilder newStr = new StringBuilder();
                while(repeat>0){
                    newStr.append(currentString);
                    repeat--;
                }
                currentString = stringStack.pop().append(newStr);
            }else{
                //是字母
                currentString.append(val);
            }
        }
        return currentString.toString();
    }
}