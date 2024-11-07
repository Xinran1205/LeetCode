import java.util.*;

// 这一题我直接秒了
class Solution {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backTracing(nums,0);
        return ret;
    }

    public void backTracing(int[] nums,int startIndex){
        ret.add(new ArrayList(path));
        for(int i=startIndex;i<nums.length;i++){
            path.add(nums[i]);
            backTracing(nums,i+1);
            path.remove(path.size()-1);
        }
        return;
    }
}