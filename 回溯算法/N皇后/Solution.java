import java.util.*;

class Solution {
    List<List<String>> ret = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(char[] c:board){
            Arrays.fill(c,'.');
        }
        backTracing(0,n,board);
        return ret;
    }

    public void backTracing(int rowIndex, int n, char[][] board){
        if(rowIndex==n){
            ret.add(Array2List(board));
            return;
        }
        for(int col=0;col<n;col++){
            if(isvalid(col,rowIndex,n,board)){
                board[rowIndex][col] = 'Q';
                backTracing(rowIndex+1,n,board);
                board[rowIndex][col] = '.';
            }
        }
    }

    // 这个语法要学会
    public List Array2List(char[][] chessboard) {
        List list = new ArrayList<>();
        for(char[] c:chessboard){
            //String.copyValueOf(char[] data) 是 Java 标准库中的一个方法，用于将字符数组转换为一个新的字符串。
            //这个方法的工作原理是创建一个字符串对象，内容是字符数组 data 的拷贝
            list.add(String.copyValueOf(c));
        }
        return list;
    }

    public boolean isvalid(int col,int row,int n,char[][] board){
        // 检查这一列（因为我们一行只有一个，不用检查行）
        // 列固定
        for(int i=0;i<row;i++){
            if(board[i][col]=='Q'){
                return false;
            }
        }
        //检查135°
        //并且因为是从上往下放的，下面不需要检查
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        //检查45°
        for(int i=row-1,j=col+1;i>=0&&j<n;i--,j++){
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}