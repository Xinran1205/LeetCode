class Solution {
    public int singleNumber(int[] nums) {
        //异或运算符符合交换律和结合律
        int ret = nums[0];
        for(int i=1;i<nums.length;i++){
            ret = ret^nums[i];
        }
        return ret;
    }
}