import java.util.*;
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        // i+j=-k
        List<List<Integer>> ret = new ArrayList<>();
        for(int i=0;i<nums.length-2;i++){
            if(i!=0&&nums[i]==nums[i-1]){
                continue;
            }
            if(nums[i]+nums[i+1]+nums[i+2]>0){
                break;
            }
            if(nums[i]+nums[nums.length-2]+nums[nums.length-1]<0){
                continue;
            }
            int j = i+1;
            int k = nums.length-1;
            while(j<k){
                int sum = nums[i]+nums[j]+nums[k];
                if(sum==0){
                    ret.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    k--;
                    while(j<k&&nums[k]==nums[k+1]){
                        k--;
                    }
                    j++;
                    while(j<k&&nums[j]==nums[j-1]){
                        j++;
                    }
                }else if(sum>0){
                    k--;
                    while(nums[k]==nums[k+1]){
                        k--;
                    }
                }else{
                    j++;
                    while(nums[j]==nums[j-1]){
                        j++;
                    }
                }
            }
        }
        return ret;
    }
}