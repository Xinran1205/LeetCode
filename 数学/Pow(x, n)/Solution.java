
//这一题其实没怎么搞懂
class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        long exp = n;               // 用 long 防止溢出
        // 判断指数是否为负数
        if (exp < 0) {
            x = 1 / x;
            exp = -exp;
        }

        double result = 1.0;
        double base = x;
        // 处理 exp 的每一位二进制
        while (exp > 0) {
            // 如果最低位是 1，就把当前 base（x^(2^k)）乘入结果
            if ((exp & 1) == 1) {
                result *= base;
            }
            // base = base^2，准备下一位
            base *= base;
            // 右移一位
            exp >>= 1;
        }
        return result;
    }
}