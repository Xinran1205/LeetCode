// 这个题目关键是prefixArr[i+1] = nums[i]+prefixArr[i]; 尽量一步for循环完成
// prefixArr就是多往后移了一位。


class NumArray {
    int[] prefixArr;

    public NumArray(int[] nums) {
        prefixArr = new int[nums.length+1];
        for(int i=0;i<nums.length;i++){
            prefixArr[i+1] = nums[i]+prefixArr[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefixArr[right+1] - prefixArr[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */