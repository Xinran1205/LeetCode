import java.util.*;
// 单调栈就是适合求当前元素右边第一个比它大的元素，方向大小无所谓

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ret = new int[nums1.length];
        // 直接放value
        Stack<Integer> stack = new Stack<>();
        // 存放nums2每个元素的下一个最大的
        Map<Integer, Integer> nextGreater = new HashMap<>();
        for(int i=0;i<nums2.length;i++){
            while(!stack.isEmpty()&&stack.peek()<nums2[i]){
                // 这里别在nums1找，直接处理nums2就可以，最后再找nums1
                nextGreater.put(stack.peek(),nums2[i]);
                stack.pop();
            }
            stack.push(nums2[i]);
        }
        for(int i=0;i<nums1.length;i++){
            ret[i] = nextGreater.getOrDefault(nums1[i], -1);
        }
        return ret;
    }
}