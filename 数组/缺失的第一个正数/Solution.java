class Solution {
    // 原地哈希！
    // 要找的数一定在 [1, N + 1] 左闭右闭（这里 N 是数组的长度）这个区间里。
    // 因此，我们可以就把原始的数组当做哈希表来使用。事实上，哈希表其实本身也是一个数组；

    public int firstMissingPositive(int[] nums) {
        //把nums[i]放到下标i-1中
        for(int i=0;i<nums.length;i++){
            // 这个循环是这道题的关键！仔细回忆一下以前学习的布谷鸟哈希就懂了！
            // 这个地方不能写成nums[i]!=i+1 因为新的nums[i]中的i已经变了
            while(nums[i]>0&&nums[i]<nums.length&&nums[i]!=nums[nums[i]-1]){
                int tmp = nums[i];
                nums[i] = nums[nums[i]-1];
                nums[tmp-1] = tmp;
            }
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=i+1){
                return i+1;
            }
        }
        return nums.length+1;
    }
}