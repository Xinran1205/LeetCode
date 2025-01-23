// 方法1，最孬，拷贝一整个数组
class Solution {
    public void rotate(int[][] matrix) {
        int[][] keep = new int[matrix.length][matrix.length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                keep[i][j] = matrix[i][j];
            }
        }
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                matrix[i][j] = keep[matrix.length-1-j][i];
            }
        }
    }
}

// 方法2，先水平翻转，再对角线翻转
class Solution2 {
    public void rotate(int[][] matrix) {
        // 这个除2非常关键！防止翻过去又翻回来了
        for(int i=0;i<matrix.length/2;i++){
            for(int j=0;j<matrix.length;j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length-1-i][j];
                matrix[matrix.length-1-i][j] = tmp;
            }
        }
        for(int i=0;i<matrix.length;i++){
            // 这个+1也很重要，和上面同理，防止翻过去又翻回来
            for(int j=i+1;j<matrix.length;j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

    }
}

// 方法3
// 最好的方法！其实确实就是一圈一圈的处理，换！
class Solution3 {
    public void rotate(int[][] matrix) {
        int iter = matrix.length/2;
        for(int i=0;i<iter;i++){
            for(int j=i;j<matrix.length-i-1;j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length-1-j][i];
                matrix[matrix.length-1-j][i] = matrix[matrix.length-1-i][matrix.length-1-j];
                matrix[matrix.length-1-i][matrix.length-1-j] = matrix[j][matrix.length-1-i];
                matrix[j][matrix.length-1-i] = tmp;
            }
        }
    }
}