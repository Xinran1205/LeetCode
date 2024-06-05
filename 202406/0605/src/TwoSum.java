import java.util.HashMap;
import java.util.Map;

/**
 * @author Xinran
 * @version 1.0
 * @description TODO
 * @date 2024/6/5 15:57
 */

// Method1: brute force
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i=0;i<nums.length;i++){
            int j= target - nums[i];
            for(int k=i+1;k<nums.length;k++){
                if (j==nums[k]){
                    return new int[]{i,k};
                }
            }
        }
        return null;
    }
}

// Method2: hash table
class Solution2{
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            int j = target - nums[i];
            if (map.containsKey(j)){
                return new int[]{map.get(j),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }
}

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        Solution solution = new Solution();
        int[] result = solution.twoSum(nums, target);
        for (int i : result) {
            System.out.println(i);
        }
    }
}

