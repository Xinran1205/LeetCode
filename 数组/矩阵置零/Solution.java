//太简单了！有优化算法，节省空间
class Solution {
    public void setZeroes(int[][] matrix) {
        int[] row = new int[matrix.length];
        int[] column = new int[matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    row[i] = 1;
                    column[j] = 1;
                }
            }
        }
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(row[i]==1||column[j]==1){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}


// 优化算法，也很简单：就是把表示存在第一列和第一行，
// 然后我之前还要看一下第一列和第一行是否本来就有0，如果有，最后把这两个变0
class Solution2 {
    public void setZeroes(int[][] matrix) {
        int columnZero = 0;
        int rowZero = 0;
        for(int i=0;i<matrix.length;i++){
            if(matrix[i][0]==0){
                columnZero = 1;
                break;
            }
        }
        for(int i=0;i<matrix[0].length;i++){
            if(matrix[0][i]==0){
                rowZero = 1;
                break;
            }
        }
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    matrix[0][j]=0;
                    matrix[i][0]=0;
                }
            }
        }
        for(int i=1;i<matrix.length;i++){
            for(int j=1;j<matrix[0].length;j++){
                if(matrix[0][j]==0||matrix[i][0]==0){
                    matrix[i][j]=0;
                }
            }
        }
        if(columnZero==1){
            for(int i=0;i<matrix.length;i++){
                matrix[i][0]=0;
            }
        }
        if(rowZero==1){
            for(int i=0;i<matrix[0].length;i++){
                matrix[0][i]=0;
            }
        }
    }
}