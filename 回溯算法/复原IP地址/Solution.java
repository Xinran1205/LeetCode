import java.util.*;

class Solution {
    // 可以单单一个0，但是不能数字前面有0
    List<String> ret = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        StringBuilder st = new StringBuilder(s);
        backTracing(st,0,0);
        return ret;
    }

    public void backTracing(StringBuilder s,int startIndex,int depth){
        // 树的深度最多为3，打3个逗号，
        // 即使为3，我们也还要校验最后一个（第四个）字符串是否合法
        if(depth==3){
            if(isValid(s,startIndex,s.length()-1)){
                ret.add(s.toString());
            }
            return;
        }
        // 一个for循环理解成处理一层
        for(int i=startIndex;i<s.length();i++){
            if(isValid(s,startIndex,i)){
                s.insert(i+1,'.');
                backTracing(s,i+2,depth+1);
                s.delete(i + 1,i+2);
            }else{
                return;
            }
        }
    }

    public boolean isValid(StringBuilder s, int startIndex,int endIndex){
        if(startIndex>endIndex){
            return false;
        }
        if(s.charAt(startIndex)=='0'&&startIndex<endIndex){
            return false;
        }
        int num = 0;
        for(int i=startIndex;i<=endIndex;i++){
            if(s.charAt(i)<'0'||s.charAt(i)>'9'){
                return false;
            }
            // 这个地方简单的逻辑要熟练！
            num = num*10 + (s.charAt(i)-'0');
            if(num>255){
                return false;
            }
        }
        return true;
    }

}