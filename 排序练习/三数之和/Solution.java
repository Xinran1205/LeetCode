import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// 排序+双指针，
// 有一个值得注意的，当sum==0时，添加完还需要移动两个指针，因为同一个i有可能有多个解
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for(int i=0;i<nums.length-2;i++){
            if(i!=0&&nums[i]==nums[i-1]){
                continue;
            }
            int j = i+1;
            int k = nums.length-1;
            while(j<k){
                int sum = nums[i] + nums[j] + nums[k];
                if(sum==0){
                    ret.add(new ArrayList<Integer>(Arrays.asList(nums[i],nums[j],nums[k])));
                    while(j<k&&nums[k]==nums[k-1]){
                        k--;
                    }
                    k--;
                    while(j<k&&nums[j+1]==nums[j]){
                        j++;
                    }
                    j++;
                }else if(sum>0){
                    k--;
                }else{
                    j++;
                }
            }
        }
        return ret;
    }
}