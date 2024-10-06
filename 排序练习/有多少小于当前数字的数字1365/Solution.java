import java.util.*;

// 这道题非常好
// 两种解法
// 1.计数排序（默认取值范围0-100），然后进行一个小型动态规划
// 2.进行一个元素的对应，然后排序
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[][] data = new int[nums.length][2];
        for(int i=0;i<nums.length;i++){
            data[i][0] = nums[i];
            data[i][1] = i;
        }
        Arrays.sort(data,new Comparator<int[]>(){
            public int compare(int[] data1,int[] data2){
                return data1[0] - data2[0];
            }
        });
        //上面这个比较也可以简写成lambda
        //Arrays.sort(data,(a,b)->a[0]-b[0]);
        
        int val = 0;
        int[] ret = new int[nums.length];
        for(int i=1;i<nums.length;i++){
            if(data[i][0]!=data[i-1][0]){
                val = i;
            }
            ret[data[i][1]] = val;
        }
        return ret;
    }

    public int[] smallerNumbersThanCurrent2(int[] nums) {
        //因为数字0-100
        int[] cnt = new int[101];
        for(int i=0;i<nums.length;i++){
            cnt[nums[i]] = cnt[nums[i]]+1;
        }
        for(int i=1;i<=100;i++){
            cnt[i] = cnt[i]+cnt[i-1];
        }
        int [] ret = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                ret[i] = cnt[nums[i]-1];
            }else{
                ret[i] = 0;
            }
        }
        return ret;
    }
}