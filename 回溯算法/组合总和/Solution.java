import java.util.*;

class Solution {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTracing(candidates,target,0,0);
        return ret;
    }

    public void backTracing(int[] candidates,int target,int sum,int startIndex){
        if(sum==target){
            ret.add(new ArrayList<>(path));
            return;
        }else if(sum>target){
            // 剪枝
            return;
        }else{
            for(int i=startIndex;i<candidates.length;i++){
                sum +=candidates[i];
                path.add(candidates[i]);
                // 这里很关键，以前都是i+1，因为每个数字只能用一次
                // 但是这里可以重复使用，所以是i
                backTracing(candidates,target,sum,i);
                sum -= candidates[i];
                path.remove(path.size()-1);
            }
        }
    }
}