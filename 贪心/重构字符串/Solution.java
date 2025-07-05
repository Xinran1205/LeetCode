class Solution {
    public String reorganizeString(String s) {
        int n = s.length();
        // 1. 统计频次
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        // 2. 找到出现最多的字母及其次数
        int maxCount = 0, maxIdx = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > maxCount) {
                maxCount = count[i];
                maxIdx = i;
            }
        }
        // 3. 可行性检查
        if (maxCount > (n + 1) / 2) {
            return "";
        }
        // 4. 准备结果数组
        char[] res = new char[n];
        int idx = 0;
        // 5. 先把出现最多的字母放到偶数位
        while (count[maxIdx]-- > 0) {
            res[idx] = (char)('a' + maxIdx);
            idx += 2;
        }
        // 6. 再把剩下的字母依次放入
        // 这个有点贪心的思想，就是假如是aabbc，a没有占完偶数位，b会从那个偶数位开始放
        // 并且b把偶数位放完了就会从奇数位开始放，并且仍然不会有问题
        // a	b	a	c	b
        for (int i = 0; i < 26; i++) {
            while (count[i]-- > 0) {
                if (idx >= n) {
                    idx = 1;  // 从第一个奇数位开始
                }
                res[idx] = (char)('a' + i);
                idx += 2;
            }
        }
        return new String(res);
    }
}