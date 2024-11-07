import java.util.*;

class Solution {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 先排序
        Arrays.sort(nums);
        backTracing(nums,0);
        return ret;
    }
    public void backTracing(int[] nums,int startIndex){
        ret.add(new ArrayList(path));
        for(int i=startIndex;i<nums.length;i++){
            // 树层去重，i>startIndex防止树枝被剪枝
            if(i>startIndex&&nums[i]==nums[i-1]){
                continue;
            }
            path.add(nums[i]);
            backTracing(nums,i+1);
            path.remove(path.size()-1);
        }
        return;
    }
}