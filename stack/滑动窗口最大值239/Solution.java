import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    // 方法1，暴力解法，超时
    public int[] maxSlidingWindow(int[] nums, int k) {
        int [] ret = new int[nums.length-k+1];
        for (int i=0;i<nums.length-k+1;i++){
            int max = nums[i];
            for (int j=i+1;j<i+k;j++){
                if (nums[j]>max){
                    max = nums[j];
                }
            }
            ret[i] = max;
        }
        return ret;
    }

    // 方法2， 本题的关键主要是，把内循环（找出最大值）的时间复杂度变成O(1)
    // 滑动窗口，单调队列!
    public int[] maxSlidingWindow2(int[] nums, int k) {
        //非严格递减（意思就是>=,基本上就是递减）
        Deque<Integer> queue = new ArrayDeque<Integer>();
        int [] ret = new int[nums.length-k+1];
        // 4个步骤！
        for(int i=-k+1, j=0;j<nums.length&&i<nums.length-k+1;i++,j++){
            // 如果队首元素（队列中最大的数）是即将要删除的元素，那么队首元素出列
            if(i>0&&queue.peek()==nums[i-1]){
                queue.poll();
            }
            // 这一步保持队列是降序的！
            while(!queue.isEmpty() && queue.peekLast() < nums[j]){
                queue.removeLast();
            }
            // 把当前元素加入队列
            queue.add(nums[j]);
            // 读取队首元素（最大的）到结果数组
            if (i>=0){
                ret[i] = queue.peek();
            }
        }
        return ret;
    }
}