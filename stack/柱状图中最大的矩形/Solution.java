import java.util.*;

class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        // 对每个柱子找左边比它矮的和右边比它矮的
        for(int i=0;i<heights.length;i++){
            while(!stack.isEmpty()&&heights[stack.peek()]>heights[i]){
                // 凹槽的高度
                int aoHeight = heights[stack.peek()];
                int distance = i-stack.peek()-1;
                stack.pop();
                if(!stack.isEmpty()){
                    distance = i-stack.peek()-1;
                }
                int val = distance*aoHeight;
                max = Math.max(val,max);
            }
            stack.push(i);
        }
        return max;
    }
}

class Solution2 {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        // 用一次循环完成初始化并首尾补0
        int[] newHeights = new int[n + 2];
        System.arraycopy(heights, 0, newHeights, 1, n);
        newHeights[0] = 0; // 首部补0
        newHeights[n + 1] = 0; // 尾部补0

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        // 遍历新数组
        for (int i = 0; i < newHeights.length; i++) {
            while (!stack.isEmpty() && newHeights[stack.peek()] > newHeights[i]) {
                int height = newHeights[stack.pop()];
                int width = i - stack.peek() - 1; // 计算宽度
                maxArea = Math.max(maxArea, height * width); // 更新最大面积
            }
            stack.push(i); // 入栈当前索引
        }
        return maxArea;
    }
}
