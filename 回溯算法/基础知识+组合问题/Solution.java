import java.util.*;
// 回溯算法：

// 在递归函数的下面就是回溯的过程
// 回溯法是一种纯暴力的问题，因为有的问题用for循环暴力不了，所以要采用回溯法
// 回溯法主要用在这几个问题
// 1. 组合问题：N个数里面按一定规则找出k个数的集合
// 2. 切割问题：一个字符串按一定规则有几种切割方式
// 3. 子集问题：一个N个数的集合里有多少符合条件的子集
// 4. 排列问题：N个数按一定规则全排列，有几种排列方式
// 5. 棋盘问题：N皇后，解数独等等

// 回溯法可以抽象成树形结构
// 树的宽度是集合的大小，通常用for循环
// 树的深度是递归的深度
// 代码框架
// void backtrack(参数) {
//     if (终止条件) {
//         收集结果;
//         return;
//     }
//     for (选择 in 选择列表) {
//         做选择;
//         backtrack(路径, 选择列表);
//         撤销选择（回溯）;
//     }
// }

// 回溯三部曲 1.递归函数参数返回值 2.递归终止条件 3.单层递归逻辑

// Leetcode77: 组合
public class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0 || n < k) {
            return res;
        }
        backtrack(n, k, 1);
        return res;
    }
    public void backtrack(int n, int k, int startIndex) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            backtrack(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }
}

// 剪枝优化
// for (int i = startIndex; i <= n; i++) 改成 for (int i = startIndex; i <= n - (k - path.size()) + 1; i++)
// 优化后的代码
class Solution2 {
    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        backTracing(n,k,1);
        return ret;
    }
    void backTracing(int n,int k, int startIndex){
        if(path.size()==k){
            ret.add(new ArrayList<>(path));
            return;
        }
        // k - path.size() 表示还需要选多少个元素来完成一个有效的组合。
        // n - (k - path.size())+1表示在保证还有足够的元素可选的前提下，i 可以达到的最大值。如果 i 太大，
        // 那么即使将 n 之后的所有元素全部选中，元素的数量也达不到 k 个
        for(int i=startIndex;i<=n-(k-path.size())+1;i++){
            path.add(i);
            backTracing(n,k,i+1);
            path.remove(path.size()-1);
        }
        return;
    }
}

