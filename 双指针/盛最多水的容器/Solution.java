class Solution {
    public int maxArea(int[] height) {
        int ret = 0;
        // 移动短的
        int left = 0;
        int right = height.length-1;
        while(left<right){
            int min = Math.min(height[left],height[right]);
            int size = min*(right-left);
            ret = Math.max(size,ret);
            if(min==height[left]){
                left++;
            }else{
                right--;
            }
        }
        return ret;
    }
}