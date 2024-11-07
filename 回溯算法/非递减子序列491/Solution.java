import java.util.*;

// 这一题很有意思，首先，不能排序，排序就把题目意思改变了
// 但是我们可以用hashset来对这一层去重，这样就不会有重复的结果
class Solution {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backTracing(nums,0);
        return ret;
    }

    public void backTracing(int[] nums,int startIndex){
        if(path.size()>1){
            ret.add(new ArrayList<>(path));
        }
        HashSet<Integer> used = new HashSet<>();
        for(int i=startIndex;i<nums.length;i++){
            if(used.contains(nums[i])) {
                continue;
            }
            if(path.size()==0||nums[i]>=path.get(path.size()-1)){
                path.add(nums[i]);
                backTracing(nums,i+1);
                path.remove(path.size()-1);
                // 这句话位置无所谓
                used.add(nums[i]);
            }
        }
    }
}