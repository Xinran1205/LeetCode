// 接雨水
import java.util.ArrayDeque;
import java.util.Deque;

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
        int sum = 0;
        // 先计算出每个位置左边最高的墙和右边最高的墙，通过动态规划计算
        int [] max_left = new int [height.length];
        int [] max_right = new int [height.length];
        for (int i = 1;i<height.length;i++){
            max_left[i] = Math.max(max_left[i-1],height[i-1]);
        }
        for (int i=height.length-2;i>=0;i--){
            max_right[i] = Math.max(max_right[i+1],height[i+1]);
        }
        for (int i=1;i<height.length;i++){
            int currentHei = height[i];
            int min = max_left[i];
            if (max_left[i]>max_right[i]){
                min = max_right[i];
            }
            if (min>currentHei){
                sum = sum + (min-currentHei);
            }
        }
        return sum;
    }

    //方法4 单调栈，比较难
    public int trap4(int[] height) {
        int sum = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i=0;i<height.length;i++){
            while(!stack.isEmpty()&&height[i]>height[stack.peek()]){
                int low = stack.pop();
                if (stack.isEmpty()){
                    break;
                }
                int min = Math.min(height[stack.peek()],height[i])-height[low];
                int distance = i -stack.peek()-1;
                sum = sum + min*distance;
            }
            stack.push(i);
        }
        return sum;
    }
}