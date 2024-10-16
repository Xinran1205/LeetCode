class Solution {
    public int[] sortedSquares(int[] nums) {
        // 用双指针，因为负数最大的在最左边，正数最大的一定是最后一个
        // 所以从两头开始比较
        int i = 0;
        int j = nums.length-1;
        int[] ret = new int[nums.length];
        for(int p = nums.length-1;p>=0;p--){
            if(nums[i]*nums[i]>nums[j]*nums[j]){
                ret[p] = nums[i]*nums[i];
                i++;
            }else{
                ret[p] = nums[j]*nums[j];
                j--;
            }
        }
        return ret;
    }
}