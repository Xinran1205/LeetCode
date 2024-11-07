import java.util.*;

class Solution {
    // 这一题思路和组合这道题思路基本上完全一样
    // 注意审题
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        // k个数，之和为n
        backTracing(k,n,1,0);
        return ret;
    }
    public void backTracing(int k,int n,int startIndex,int sum){
        if(path.size()==k&&sum==n){
            ret.add(new ArrayList<>(path));
            return;
        }
        // 注意审题，题目说只使用数字1到9
        // 这个写成i<=8也可以，但是这是因为题目说了k>=2
        for(int i=startIndex;i<=9&&sum<n;i++){
            path.add(i);
            sum+=i;
            backTracing(k,n,i+1,sum);
            sum-=i;
            path.remove(path.size()-1);
        }
    }
}