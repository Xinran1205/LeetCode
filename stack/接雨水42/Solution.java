// 接雨水
import java.util.*;

class Solution {
    // 1. 按行求
    // 这个方法是按行遍历，就是先统计，最高的墙有多高，这个方法会超时！
    // 然后算对于每一个高度（从1开始）它可以存多少个1的水量
    public int trap1(int[] height) {
        int sum = 0;
        int maxHeight = getMax(height);
        //遍历每个高度
        for (int i=1;i<=maxHeight;i++){
            //对于这个高度，算出这一行的存储量
            int tmp = 0;
            Boolean isStart = false;
            for (int j=0;j<height.length;j++){
                if (height[j]>=i){
                    isStart = true;
                }
                if (isStart&&height[j]>=i){
                    sum = sum +tmp;
                    tmp = 0;
                }else if (isStart&&height[j]<i){
                    tmp = tmp+1;
                }
            }
        }
        return sum;
    }
    // 获取最高的墙的高度
    public int getMax(int[] height){
        int max = 0;
        for (int i=0;i<height.length;i++){
            if(height[i]>max){
                max = height[i];
            }
        }
        return max;
    }

    // 2. 按列求，这个就是遍历一次，对于每一列，看这一列能存多少水
    // 条件就是取决于这一列左右两边最高的墙，看这两个最高的中最小的那个，减去当前列的高度就是这一列能存的水
    // 仍然超时，因为每次找左最高和右最高太麻烦了。改进用动态规划。
    public int trap(int[] height) {
        int sum = 0;
        // 遍历所有的列,求每一列的水，两端一定没有水
        for (int i=1;i<height.length;i++){
            int currentHei = height[i];
            // 找出最左边最高的墙的下标
            int maxLeft = 0;
            for (int j=i-1;j>=0;j--){
                if (height[j]>maxLeft){
                    maxLeft = height[j];
                }
            }
            int maxRight = 0;
            for (int k=i+1;k<height.length;k++){
                if (height[k]>maxRight){
                    maxRight = height[k];
                }
            }
            int min = maxLeft;
            if (maxLeft>maxRight){
                min = maxRight;
            }
            if (min>currentHei){
                sum = sum + (min-currentHei);
            }
        }
        return sum;
    }

    //方法3. 动态规划
    public int trap3(int[] height) {
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];
        maxLeft[0] = 0;
        maxRight[height.length-1] = 0;
        // 先计算出每个位置左边最高的墙和右边最高的墙，通过动态规划计算
        for(int i=1;i<height.length;i++){
            maxLeft[i] = Math.max(maxLeft[i-1],height[i-1]);
        }
        for(int i=height.length-2;i>=0;i--){
            maxRight[i] = Math.max(maxRight[i+1],height[i+1]);
        }
        int sum = 0;
        for(int i=1;i<height.length;i++){
            int minHeight = Math.min(maxLeft[i],maxRight[i]);
            int volume = 0;
            if(minHeight>height[i]){
                volume = minHeight-height[i];
            }
            sum+=volume;
        }
        return sum;
    }

    //方法4 单调栈，
    public int trap4(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        for(int i=0;i<height.length;i++){
            // 这里很有意思，小于或者小于等于都可以
            while(!stack.isEmpty()&&height[stack.peek()]<height[i]){
                // 凹槽的高度
                int aoHeight = height[stack.peek()];
                stack.pop();
                // 没有左墙
                if(stack.isEmpty()){
                    break;
                }
                int rightWall = height[i];
                int leftWall = height[stack.peek()];
                int distance = i-stack.peek()-1;
                int minWall = Math.min(rightWall,leftWall);
                int val = distance*(minWall-aoHeight);
                sum = sum+val;
            }
            stack.push(i);
        }
        return sum;
    }

}