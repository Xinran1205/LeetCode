class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //先看在哪一行
        int hang = matrix.length;
        int i = 0;
        int j = hang;
        while(i<j){
            int mid = (j-i)/2+i;
            if(matrix[mid][0]==target){
                return true;
            }else if(matrix[mid][0]<target){
                i = mid + 1;
            }else{
                j = mid;
            }
        }
        i = i - 1;
        if(i<0){
            return false;
        }
        // 此时i == j
        // 这个时候i应该是大于target的，因为是插入位置的模板
        // 所以target在i-1这一行
        int left = 0;
        int right = matrix[0].length;
        while(left<right){
            int mid = (right-left)/2+left;
            if(matrix[i][mid]<target){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        if(left==matrix[0].length||matrix[i][left]!=target){
            return false;
        }else{
            return true;
        }
    }
}