class Solution {
    public int trailingZeroes(int n) {
        // 每个尾部 0 都源自一个 “2×5” 的组合。
        // 在阶乘的所有因数里，2 的个数远多于 5（每隔 2 就有一个偶数），所以 真正稀缺的是“5”的个数，也就决定了 0 的数量。
        int count = 0;
        // d 依次取 5, 25, 125, 625, ... 直到 d > n 为止
        for (long d = 5; d <= n; d *= 5) {
            count += n / d;
        }
        return count;
    }
}