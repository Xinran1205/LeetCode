import java.util.ArrayList;
import java.util.List;

// 这一题我采用和螺旋矩阵2不一样的思路，我使用左闭右闭来做！
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        int tar = rows * cols;

        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = cols - 1;

        while (ret.size() < tar) {
            // 从左到右遍历顶部行
            for (int j = left; j <= right && ret.size() < tar; j++) {
                ret.add(matrix[top][j]);
            }
            top++;

            // 从上到下遍历右侧列
            for (int i = top; i <= bottom && ret.size() < tar; i++) {
                ret.add(matrix[i][right]);
            }
            right--;

            // 从右到左遍历底部行
            for (int j = right; j >= left && ret.size() < tar; j--) {
                ret.add(matrix[bottom][j]);
            }
            bottom--;

            // 从下到上遍历左侧列
            for (int i = bottom; i >= top && ret.size() < tar; i--) {
                ret.add(matrix[i][left]);
            }
            left++;
        }

        return ret;
    }
}
