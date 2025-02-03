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

// 最牛逼
class Solution3 {
    public void rotate(int[] nums, int k) {
        // 1.首先根据群论的知识，从任意起始点开始，不管走多少步（k），最后一定会形成环（或者说遇到重复的）并且这个重复的点一定是起始点。
        // 2.遍历的圈数是nums长度和k的最大公约数
        int count = gcd(nums.length,k);
        for(int i=0;i<count;i++){
            int curVal  = nums[i];
            int nextIndex = (i+k)%nums.length;
            while(nextIndex!=i){
                int tmp = nums[nextIndex];
                nums[nextIndex] = curVal;
                curVal = tmp;
                nextIndex = (nextIndex+k)%nums.length;
            }
            nums[i] = curVal;
        }
    }
    public int gcd(int x,int y){
        if(y>0){
            return gcd(y,x%y);
        }else{
            return x;
        }
    }
}