import java.util.*;
class Solution {
    public int subarraySum(int[] nums, int k) {

        // 子数组的定义：子数组是数组中元素的连续非空序列。
        // 一定要是连续！！！
        // 例如，数组 [1, 2, 3] 的子数组包括 [1], [1, 2], [1, 2, 3], [2], [2, 3], [3]。
        // 目标：统计所有子数组中，元素之和等于 k 的子数组的数量。

        // 利用前缀和解决问题：
        // 对于每个位置 j，我们想找到之前有多少个位置 i，使得 prefixSum[j] - prefixSum[i] = k。
        // 这等价于 prefixSum[i] = prefixSum[j] - k。
        // 我们可以使用一个哈希表来存储每个前缀和出现的次数，以便快速查询 prefixSum[j] - k 出现的次数。
        int sum = 0;
        Map<Integer,Integer> hashMap = new HashMap<>();
        hashMap.put(0,1);
        int pre = 0;
        for(int i=0;i<nums.length;i++){
            pre = pre+nums[i];
            if(hashMap.containsKey(pre-k)){
                sum+=hashMap.get(pre-k);
            }
            // 这里动态更新很重要，因为可能遇到负数或者0
            // 一个前缀和可能出现多次，尤其是当数组中有负数或零时。
            hashMap.put(pre,hashMap.getOrDefault(pre,0)+1);
        }
        return sum;
    }
}

class Solution2 {
    public int subarraySum(int[] nums, int k) {
        // 暴力
        int count = 0;
        // 以i开始
        for(int i=0;i<nums.length;i++){
            int sum = 0;
            // 以j结束
            for(int j=i;j<nums.length;j++){
                sum+=nums[j];
                if(sum==k){
                    count++;
                }
            }
        }
        return count;
    }
}