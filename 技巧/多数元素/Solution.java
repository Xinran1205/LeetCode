import java.util.*;

class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}

// 一遍遍历统计次数，再找出最大的value对应的key
class Solution2 {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        // 记录每个number出现的次数
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num,1) + 1);
        }
        return counts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }
}

// Boyer-Moore 投票算法  最好的方法！
class Solution3 {
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        for(int i=0;i<nums.length;i++){
            if(count==0){
                candidate = nums[i];
            }
            if(candidate!=nums[i]){
                count--;
            }else{
                count++;
            }
        }
        return candidate;
    }
}
