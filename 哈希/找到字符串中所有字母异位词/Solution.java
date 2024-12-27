import java.util.*;

// 1. 用数组记录出现频率（这个很巧妙！）（防止aba也被abc接受的情况，不要用哈希）
// 2. 滑动窗口，窗口大小不足就continue，大小一样就比较频率，然后左边界右移
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] pCount = new int[26];
        int[] windowCount = new int[26];

        for(int i = 0; i < p.length(); i++){
            pCount[p.charAt(i) - 'a']++;
        }

        List<Integer> ret = new ArrayList<>();
        for (int right = 0; right < s.length(); right++) {
            windowCount[s.charAt(right) - 'a']++; // 右端点字母进入窗口
            int left = right - p.length() + 1;
            if (left < 0) { // 窗口长度不足 p.length()
                continue;
            }
            if (Arrays.equals(pCount, windowCount)) { // s' 和 p 的每种字母的出现次数都相同
                ret.add(left); // s' 左端点下标加入答案
            }
            windowCount[s.charAt(left) - 'a']--; // 左端点字母离开窗口
        }
        return ret;
    }
}