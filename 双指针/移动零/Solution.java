class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0;
        // 这个0非常关键，如果是1就会导致顺序打乱了！
        int j = 0;
        while(j<nums.length){
            if(nums[j]!=0){
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
                i++;
            }
            j++;
        }
    }
}