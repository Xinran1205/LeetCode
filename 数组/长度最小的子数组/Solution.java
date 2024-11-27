
// 方法1.最孬，两个for循环暴力，一个起始位置，一个终止位置



// 方法2.滑动窗口（双指针）
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int startIndex = 0;
        int curSum = 0;
        // sig用来判断是否找到
        int sig = 0;
        for(int endIndex=0;endIndex<nums.length;endIndex++){
            curSum = curSum + nums[endIndex];
            // 这个地方非常非常重要，一定要是while，非常关键！！！
            while(curSum>=target){
                min = Math.min(min,(endIndex-startIndex+1));
                curSum = curSum - nums[startIndex];
                startIndex++;
                sig = 1;
            }
        }
        if(sig==0){
            return 0;
        }
        return min;
    }
}
