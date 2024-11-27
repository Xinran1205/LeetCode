class Solution {
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length-1;
        while(i<j){
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }
}

// 骚操作，性能慢的一笔
// 我试了一下用char性能非常好
class Solution2 {
    public void reverseString(char[] s) {
        String reversedString = new StringBuilder(new String(s)).reverse().toString();
        for (int i = 0; i < s.length; i++) {
            s[i] = reversedString.charAt(i);
        }
    }
}
