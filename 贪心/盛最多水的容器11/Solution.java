
class Solution {
    public int maxArea(int[] height) {
        //观察需要向内移动哪根柱子
        // 1）如果移动较高的柱子，由于水的宽度在变小，而水的高度一定不会增加，因
        //    为取决于最矮的柱子的高度，矮的不动水的高度肯定不会增加
        //    所以最终水的面积不会超过之前记录的水的面积
        // 2）所以，只能移动较短的柱子，然后计算此时水的面积，再与之前记录的水的面积 res 进行比较，保存那个更大的值
        int left = 0;
        int right = height.length-1;
        int max = 0;
        while(left<right){
            int minHeight = Math.min(height[right],height[left]);
            int cur = minHeight*(right-left);
            if(cur>max){
                max = cur;
            }
            //移动
            if(height[right]<height[left]){
                right--;
            }else{
                left++;
            }
        }
        return max;
    }
}