
import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        // 1.按照左端点排序，那么在排完序的列表中，可以合并的区间一定是连续的
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
        List<int[]> merged = new ArrayList<int[]>();
        // 这一部分逻辑很简单，就如果是第一个，直接放进去，对后面每一个，如果它的最左大于前一个的最右，那么放进去
        // 如果它的最左小于前一个的最右，说明可以合并，把前一个的最右更新成两个最右的最大值即可。
        for(int i=0;i<intervals.length;i++){
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        // 当调用 merged.toArray(new int[merged.size()][]) 时，List 中的每个 int[] 元素都被复制到新数组中。
        // 这个新数组的类型与 List 中元素的类型（int[]）一致，长度正好等于 List 的大小。
        return merged.toArray(new int[merged.size()][]);
    }
}