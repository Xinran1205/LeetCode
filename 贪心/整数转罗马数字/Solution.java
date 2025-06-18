class Solution {
    public String intToRoman(int num) {
        // 直接把对应出来，然后贪心
        // 预定义所有罗马符号及其对应的数值
        int[]    values  = {1000,  900, 500, 400, 100,  90,  50,  40,  10,   9,   5,   4,   1};
        String[] symbols = {"M",  "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        // 从最大面值开始，依次“贪心”减去
        // 因为一个钱可以用很多次！
        for (int i = 0; i < values.length; i++) {
            // 能减就拼对应符号，直到减不动为止
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
            // 如果刚好凑成 0，提前结束
            if (num == 0){
                break;
            }
        }
        return sb.toString();

    }
}