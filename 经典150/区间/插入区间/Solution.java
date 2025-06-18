import java.util.*;
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0, n = intervals.length;

        // 1. 添加所有在 newInterval 左侧且不重叠的区间
        //    它们的 end 都小于 newInterval 的 start
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // 2. 合并所有与 newInterval 有重叠的区间
        //    只要 intervals[i].start <= newInterval.end，就说明重叠
        int mergedStart = newInterval[0];
        int mergedEnd   = newInterval[1];
        while (i < n && intervals[i][0] <= mergedEnd) {
            // 扩展合并区间的左右端点
            mergedStart = Math.min(mergedStart, intervals[i][0]);
            mergedEnd   = Math.max(mergedEnd,   intervals[i][1]);
            i++;
        }
        // 添加合并后的新区间
        result.add(new int[]{mergedStart, mergedEnd});

        // 3. 添加剩余所有在 newInterval 右侧且不重叠的区间
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        // 列表转数组返回
        return result.toArray(new int[result.size()][]);
    }
}