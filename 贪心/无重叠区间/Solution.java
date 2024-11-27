import java.util.*;

// 这一题和用箭射气球的题目基本完全一样
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 先排序
        Arrays.sort(intervals,new Comparator<int[]>(){
            public int compare(int[] a,int[] b){
                if(a[0]<b[0]){
                    return -1;
                }else if(a[0]>b[0]){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
        // 射气球逻辑
        int ret = 0;
        for (int i=1;i<intervals.length;i++) {
            if(intervals[i][0]<intervals[i-1][1]){
                ret++;
                intervals[i][1] = Math.min(intervals[i][1],intervals[i-1][1]);
            }
        }
        return ret;
    }
}