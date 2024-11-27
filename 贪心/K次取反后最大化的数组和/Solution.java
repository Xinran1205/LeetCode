import java.util.*;

class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        //排序
        // 从小到大翻，如果有负数就直接翻
        // 没有负数直接break
        Arrays.sort(nums);
        for(int i = 0;i<nums.length&&k>0;i++){
            if(nums[i]<0){
                k--;
                nums[i] = -nums[i];
            }else{
                break;
            }
        }
        // 此时全部为正数，再次排序
        // 如果k为奇数，翻转最小的数
        Arrays.sort(nums);
        if(k%2!=0){
            nums[0] = -nums[0];
        }
        // 求和
        int sum = 0;
        for(int i = 0;i<nums.length;i++){
            sum = sum+nums[i];
        }
        return sum;
    }
}