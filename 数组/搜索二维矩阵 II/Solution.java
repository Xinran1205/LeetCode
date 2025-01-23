class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //每行二分法
        for(int i=0;i<matrix.length;i++){
            int left = 0;
            int right = matrix[0].length;
            while(left<right){
                int mid = (right-left)/2+left;
                if(matrix[i][mid]==target){
                    return true;
                }else if(matrix[i][mid]>target){
                    right = mid;
                }else{
                    left = mid+1;
                }
            }
        }
        return false;
    }
}

class Solution2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        //二叉树
        int columnIndex = matrix[0].length-1;
        int rowIndex = 0;
        while(columnIndex>=0&&rowIndex<matrix.length){
            if(matrix[rowIndex][columnIndex]==target){
                return true;
            }else if(matrix[rowIndex][columnIndex]<target){
                rowIndex++;
            }else{
                columnIndex--;
            }
        }
        return false;
    }
}