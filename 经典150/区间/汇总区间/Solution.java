import java.util.*;
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        // 1. 空数组直接返回空列表
        if (nums == null || nums.length == 0) {
            return result;
        }

        // 2. 初始化当前区间的起点和上一个值
        int start = nums[0];
        int end  = nums[0];

        // 3. 从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            int x = nums[i];
            // 3.1 如果当前值与前一个值不连续，区间断开
            if (x != end + 1) {
                // 收尾上一个区间
                if (start == end) {
                    // 单个数字
                    result.add(String.valueOf(start));
                } else {
                    // 连续区间
                    result.add(start + "->" + end);
                }
                // 开启新区间
                start = x;
            }
            // 3.2 更新 prev 为当前值（无论是否断开，下一轮都以此为基准）
            end = x;
        }

        // 4. 最后别忘了收尾最后一个区间
        if (start == end) {
            // 单个数字
            result.add(String.valueOf(start));
        } else {
            // 连续区间
            result.add(start + "->" + end);
        }

        return result;
    }
}