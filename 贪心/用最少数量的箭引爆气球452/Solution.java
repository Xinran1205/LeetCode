import java.util.Arrays;
import java.util.Comparator;

//先对开始位置排序
// 然后从第二个气球开始遍历
// 两种情况 当前气球的开始位置大于前一个气球的结束位置，说明需要一支箭
// else 说明两个气球有重叠，更新当前气球的结束位置为两个气球结束位置的最小值


class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points.length==0){
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                if (point1[0] > point2[0]) {
                    return 1;
                } else if (point1[0] < point2[0]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int ret = 1;
        for(int i=1;i<points.length;i++){
            if(points[i][0]>points[i-1][1]){
                ret++;
            }else{
                points[i][1] = Math.min(points[i][1],points[i-1][1]);
            }
        }
        return ret;
    }
}