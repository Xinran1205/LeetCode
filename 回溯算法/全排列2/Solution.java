import java.util.*;
class Solution {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        used = new int[nums.length];
        backTracing(nums);
        return ret;
    }

    public void backTracing(int[] nums){
        if(path.size()==nums.length){
            ret.add(new ArrayList<>(path));
        }
        for(int i=0;i<nums.length;i++){
            // 树枝去重
            if(used[i]==1){
                continue;
            }else if(i>0&&nums[i]==nums[i-1]&&used[i-1]==0){
                //树层去重,这个条件used[i-1]==0非常重要！防止把树枝给砍掉了
                continue;
            }else{
                path.add(nums[i]);
                used[i] = 1;
                backTracing(nums);
                used[i] = 0;
                path.remove(path.size()-1);
            }
        }
    }
}