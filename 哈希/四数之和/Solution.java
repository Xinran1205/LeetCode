import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        for(int i=0;i<nums.length-3;i++){
            if((long)nums[i]+(long)nums[i+1]+(long)nums[i+2]+(long)nums[i+3]>target){
                break;
            }
            // 这个地方的剪枝要注意，是continue而不是break
            if((long)nums[i]+(long)nums[nums.length-2]+(long)nums[nums.length-1]+(long)nums[nums.length-3]<target){
                continue;
            }
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            for(int j=i+1;j<nums.length-2;j++){
                if((long)nums[i]+(long)nums[j]+(long)nums[j+1]+(long)nums[j+2]>target){
                    break;
                }
                // 这个地方的剪枝要注意，是continue而不是break
                if((long)nums[i]+(long)nums[j]+(long)nums[nums.length-1]+(long)nums[nums.length-2]<target){
                    continue;
                }
                if(j>i+1&&nums[j]==nums[j-1]){
                    continue;
                }
                int k = j+1;
                int p = nums.length-1;
                while(k<p){
                    if((long)nums[i]+(long)nums[j]+(long)nums[k]+(long)nums[p]==target){
                        ret.add(Arrays.asList(nums[i],nums[j],nums[k],nums[p]));
                        while(k<p&&nums[k]==nums[k+1]){
                            k++;
                        }
                        k++;
                        while(k<p&&nums[p]==nums[p-1]){
                            p--;
                        }
                        p--;
                    }else if ((long)nums[i]+(long)nums[j]+(long)nums[k]+(long)nums[p]>target){
                        p--;
                    }else{
                        k++;
                    }
                }
            }
        }
        return ret;
    }
}