
// 性能很好！！！
class Solution2{
    public String reverseStr(String s, int k) {
        char[] cs = s.toCharArray();
        int index = 0;
        while(index<s.length()){
            int i = index;
            int j = index+k-1;
            if(index+k-1>=s.length()){
                j = s.length()-1;
            }
            while(i<j){
                char tmp = cs[i];
                cs[i] = cs[j];
                cs[j] = tmp;
                i++;
                j--;
            }
            index = index + 2*k;
        }
        return String.valueOf(cs);
    }
}

// 思路没问题，但是别用StringBuilder，用char[]，这样效率更高！！！
class Solution {
    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int index = 0;
        while(index<sb.length()){
            int i = index;
            int j = index+k-1;
            if(index+k-1>=sb.length()){
                j = sb.length()-1;
            }
            while(i<j){
                char tmp = sb.charAt(i);
                sb.setCharAt(i,sb.charAt(j));
                sb.setCharAt(j,tmp);
                i++;
                j--;
            }
            index = index + 2*k;
        }
        return sb.toString();
    }
}