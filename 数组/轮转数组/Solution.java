// 方法1，最孬
class Solution {
    public void rotate(int[] nums, int k) {
        int[] numsCopy = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            numsCopy[(i+k)%nums.length] = nums[i];
        }
        System.arraycopy(numsCopy, 0, nums, 0, nums.length);
    }
}

// 方法2，三次翻转，这个其实非常好理解！纸上画一下就懂了！
class Solution2 {
    public void rotate(int[] nums, int k) {
        // 三次翻转
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k%nums.length-1);
        reverse(nums,k%nums.length,nums.length-1);
    }
    public void reverse(int[] nums,int start,int end){
        while(start<end){
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}