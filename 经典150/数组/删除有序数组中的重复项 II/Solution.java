class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length<=2){
            return nums.length;
        }
        int i = 2;
        int j = 2;
        while(j<nums.length){
            if(nums[j]==nums[i-2]){
                j++;
            }else{
                nums[i] = nums[j];
                i++;
                j++;
            }
        }
        return i;
    }
}