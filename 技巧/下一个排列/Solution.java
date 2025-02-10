class Solution {
    public void nextPermutation(int[] nums) {
        int smallIndex = 0;
        int jIndex = 0;
        for(int i=nums.length-1;i>0;i--){
            if(nums[i]>nums[i-1]){
                smallIndex = i-1;
                jIndex = i;
                break;
            }
        }
        // jIndex 到 end一定是降序
        int bigIndex = 0;
        for(int k=nums.length-1;k>=jIndex;k--){
            if(nums[k]>nums[smallIndex]){
                bigIndex = k;
                break;
            }
        }
        int tmp = nums[smallIndex];
        nums[smallIndex] = nums[bigIndex];
        nums[bigIndex] = tmp;
        // 此时jIndex 到 end一定还是降序（这个简单思考一下就很好理解）
        // 把这一部分升序
        int left = jIndex;
        int right = nums.length-1;
        while(left<right){
            int tmp1 = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp1;
            left++;
            right--;
        }
    }
}