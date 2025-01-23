class Solution {
    // 原地哈希！
    public int firstMissingPositive(int[] nums) {
        // 从左到右遍历一遍数组
        for(int i=0;i<nums.length;i++){
            // 这个循环是这道题的关键！仔细回忆一下以前学习的布谷鸟哈希就懂了！
            while(nums[i]>=1&&nums[i]<nums.length&&nums[nums[i] - 1]!=nums[i]){
                int tmp = nums[i];
                nums[i] = nums[nums[i]-1];
                nums[tmp-1] = tmp;
            }
        }
        int i=0;
        while(i<nums.length){
            if(nums[i]!=i+1){
                return i+1;
            }
            i++;
        }
        if(i==nums.length){
            return i+1;
        }
        return 0;
    }
}