class Solution {
    public int maxSubArray(int[] nums) {
        int curVal = 0;
        int max = nums[0];
        for(int i=0;i<nums.length;i++){
            // 理解为，如果当前的总和是负数，不需要管nums[i]是正数还是负数
            // 那么就清空之前所有的值，从当前值开始计算
            if(curVal<0){
                curVal = nums[i];
            }else{
                curVal = curVal + nums[i];
            }
            // max用来记录最大值
            if(curVal>max){
                max = curVal;
            }
        }
        return max;
    }
}