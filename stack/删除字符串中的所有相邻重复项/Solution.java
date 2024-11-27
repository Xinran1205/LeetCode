import java.util.*;
class Solution {
    public String removeDuplicates(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for(int i=0;i<s.length();i++){
            if(!deque.isEmpty()&&deque.peekLast()==s.charAt(i)){
                deque.removeLast();
            }else{
                deque.addLast(s.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()){
            sb.append(deque.pop());
        }
        return sb.toString();
    }
}