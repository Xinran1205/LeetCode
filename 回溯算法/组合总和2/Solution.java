import java.util.*;

// 这题直接看代码随想录网站讲解：https://www.programmercarl.com/0040.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CII.html#%E6%80%9D%E8%B7%AF
// 他分析的就是 是树枝去重还是树层去重，这很重要，也很好理解
class Solution {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backTracing(candidates,target,0,0);
        return ret;
    }

    public void backTracing(int[] candidates,int target,int sum,int startIndex){
        if(sum==target){
            ret.add(new ArrayList<>(path));
            return;
        }else if(sum>target){
            // 这里就是剪枝
            return;
        }else{
            for(int i=startIndex;i<candidates.length;i++){
                // 关键是这一步，树层去重
                if(i>startIndex&&candidates[i]==candidates[i-1]){
                    continue;
                }
                sum +=candidates[i];
                path.add(candidates[i]);
                backTracing(candidates,target,sum,i+1);
                sum -= candidates[i];
                path.remove(path.size()-1);
            }
        }
    }
}