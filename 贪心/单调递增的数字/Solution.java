
// 这道题思路不难
// 思路就是从右到左遍历，如果左边的数大于右边的数，那么左边的数减一，右边的所有数变成9
// 这题主要考察字符串的操作
class Solution {
    public int monotoneIncreasingDigits(int n) {
        String str = String.valueOf(n);
        StringBuilder sb = new StringBuilder(str);
        // 从后往前遍历
        // 只要遇到左边大于右边
        for(int i=sb.length()-2;i>=0;i--){
            int cur = Character.getNumericValue(sb.charAt(i));
            int last = Character.getNumericValue(sb.charAt(i + 1));
            if(cur>last){
                int now = cur - 1;
                char val = (char) ('0' + now); // 将数字转回字符
                sb.setCharAt(i,val);
                // 这里可以进一步优化，因为我只要找到最前面的就可以，然后在循环外面一次for循环赋值成9就可以
                for (int j = i+1; j < sb.length(); j++) {
                    sb.setCharAt(j, '9');
                }
            }
        }
        return Integer.parseInt(sb.toString());
    }
}


// 优化！！！
class Solution2 {
    public int monotoneIncreasingDigits(int n) {
        String str = String.valueOf(n);
        StringBuilder sb = new StringBuilder(str);
        // 从后往前遍历
        // 只要遇到左边大于右边
        int start = sb.length();
        for(int i=sb.length()-2;i>=0;i--){
            int cur = Character.getNumericValue(sb.charAt(i));
            int last = Character.getNumericValue(sb.charAt(i + 1));
            if(cur>last){
                int now = cur - 1;
                char val = (char) ('0' + now); // 将数字转回字符
                sb.setCharAt(i,val);
                start = i;
            }
        }
        for (int j = start+1; j < sb.length(); j++) {
            sb.setCharAt(j, '9');
        }
        return Integer.parseInt(sb.toString());
    }
}