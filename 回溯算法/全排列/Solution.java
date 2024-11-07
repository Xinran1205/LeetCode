import java.util.*;

// 46. 全排列，这个方法秒了，但是性能有点低
class Solution {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        //树枝去重
        backTracing(nums);
        return ret;
    }

    public void backTracing(int[] nums){
        if(path.size()==nums.length){
            ret.add(new ArrayList<>(path));
        }
        for(int i=0;i<nums.length;i++){
            // 这里要优化。
            if(path.contains(nums[i])){
                continue;
            }else{
                path.add(nums[i]);
                backTracing(nums);
                path.remove(path.size()-1);
            }
        }
    }
}

//优化！用used数组，很好理解
class Solution2 {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int[] used;

    public List<List<Integer>> permute(int[] nums) {
        //树枝去重
        used = new int[nums.length];
        backTracing(nums);
        return ret;
    }

    public void backTracing(int[] nums){
        if(path.size()==nums.length){
            ret.add(new ArrayList<>(path));
        }
        for(int i=0;i<nums.length;i++){
            if(used[i]==1){
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