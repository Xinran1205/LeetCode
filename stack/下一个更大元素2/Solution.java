import java.util.*;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        // 方法1.孬方法，拼两个数组
        int[] nums2 = new int[nums.length*2];
        for(int i=0;i<nums.length;i++){
            nums2[i] = nums[i];
            nums2[i+nums.length] = nums[i];
        }
        int[] ret = new int[nums.length*2];
        Arrays.fill(ret,-1);
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<nums2.length;i++){
            while(!stack.isEmpty()&&nums2[stack.peek()]<nums2[i]){
                ret[stack.peek()] = nums2[i];
                stack.pop();
            }
            stack.push(i);
        }
        return Arrays.copyOfRange(ret,0,nums.length);
    }
}

class Solution2 {
    public int[] nextGreaterElements(int[] nums) {
        // 方法2.原数组取模
        int[] ret = new int[nums.length];
        Arrays.fill(ret,-1);
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<nums.length*2;i++){
            int index = i%nums.length;
            while(!stack.isEmpty()&&nums[stack.peek()]<nums[index]){
                ret[stack.peek()] = nums[index];
                stack.pop();
            }
            stack.push(index);
        }
        return ret;
    }
}