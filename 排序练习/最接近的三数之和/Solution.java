
import java.util.Arrays;

// 这道题主要注意的是灵活使用绝对值！
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int sum = nums[0]+nums[1]+nums[2];
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(i!=0&&nums[i]==nums[i-1]){
                continue;
            }
            int j = i+1;
            int k = nums.length-1;
            while(j<k){
                int val = nums[i] + nums[j] + nums[k];
                if(Math.abs(val-target)<Math.abs(sum-target)){
                    sum = val;
                }
                if(val==target){
                    return val;
                }else if(val>target){
                    k--;
                }else{
                    j++;
                }
            }
        }
        return sum;
    }
}