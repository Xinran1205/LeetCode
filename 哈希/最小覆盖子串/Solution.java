import java.util.*;

// 题目特点
// 1. 用两个哈希表，不要用数组了，因为可能有大写也可能有小写
// 2. 这个cnt非常巧妙，就是判断总数是否相等就可以等价于判断每个字母的个数是否相等 （因为如果单个字符超了cnt不++）

// 步骤如下：
// 1.加入右端点字母
// 2.判断是否符合条件，符合条件则cnt++
// 3.循环删除左端点字母（如果左端点属于超了的字母，就要删除）
// 4.判断cnt是否等于t的长度并且长度是否更小，更新最小长度和起始位置
class Solution {
    public String minWindow(String s, String t) {
        if(s.length()<t.length()){
            return "";
        }
        Map<Character, Integer> tCount = new HashMap<Character, Integer>();
        Map<Character, Integer> windowCount = new HashMap<Character, Integer>();

        for(int i = 0; i < t.length(); i++){
            tCount.put(t.charAt(i),tCount.getOrDefault(t.charAt(i),0)+1);
        }
        int cnt = 0;
        int left = 0;
        int right = 0;
        int start = 0;
        int minLen = Integer.MAX_VALUE;

        while(right<s.length()) {
            windowCount.put(s.charAt(right),windowCount.getOrDefault(s.charAt(right),0)+1); // 右端点字母进入窗口
            if (windowCount.get(s.charAt(right))<=tCount.getOrDefault(s.charAt(right),0)) { // s' 和 t 的每种字母的出现次数都相同
                cnt++;
            }
            // 删除
            while(left<right&&windowCount.getOrDefault(s.charAt(left),0)>tCount.getOrDefault(s.charAt(left),0)){
                windowCount.put(s.charAt(left),windowCount.get(s.charAt(left))-1);
                left++;
            }
            // 加入
            if(cnt == t.length()&&right-left<minLen){
                start = left;
                minLen = right-left+1;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}

// 性能更好！用数组代替HashMap，假设字符集为ASCII
class Solution2 {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        // 使用数组代替HashMap，假设字符集为ASCII
        int[] tCount = new int[128];
        int[] windowCount = new int[128];

        // 统计t中每个字符的数量
        for (int i = 0; i < t.length(); i++) {
            tCount[t.charAt(i)]++;
        }

        int cnt = 0; // 窗口中满足t的字符总数
        int left = 0;
        int right = 0;
        int start = 0;
        int minLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            char c = s.charAt(right);
            windowCount[c]++; // 右端点字母进入窗口

            // 如果当前字符在t中，并且窗口中该字符的数量不超过t中需要的数量
            if (windowCount[c] <= tCount[c]) {
                cnt++;
            }

            // 当窗口中当前字符的数量超过t中需要的数量时，尝试收缩窗口
            while (left < right && windowCount[s.charAt(left)] > tCount[s.charAt(left)]) {
                windowCount[s.charAt(left)]--;
                left++;
            }

            // 当窗口中满足所有t的字符时，更新最小窗口
            if (cnt == t.length() && (right - left + 1) < minLen) {
                start = left;
                minLen = right - left + 1;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
