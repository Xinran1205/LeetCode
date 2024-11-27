import java.util.*;

// 这题别用哈希，纯搞笑的，就用双指针秒了！

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // 双指针？
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        for(int i=0;i<nums.length-2;i++){
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            if(nums[i]+nums[i+1]+nums[i+2]>0){
                break;
            }
            if(nums[i]+nums[nums.length-1]+nums[nums.length-2]<0){
                continue;
            }

            int j = i+1;
            int k = nums.length-1;
            while(j<k){
                if(nums[i]+nums[j]+nums[k]==0){
                    ret.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while(j<k&&nums[j]==nums[j+1]){
                        j++;
                    }
                    // 这里无论如何都可以j++和k--，同时缩减，仔细思考，因为排过序了！
                    // 如果匹配成功，j++不k--，后面也不可能有匹配的情况！
                    j++;
                    while(j<k&&nums[k]==nums[k-1]){
                        k--;
                    }
                    k--;
                }else if(nums[i]+nums[j]+nums[k]<0){
                    j++;
                }else{
                    k--;
                }
            }
        }
        return ret;
    }
}