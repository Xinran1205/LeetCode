// 究极优化！ O(1)空间复杂度
class Solution {
    public void gameOfLife(int[][] board) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                int count = getNeiburt(i-1,j-1,board)+getNeiburt(i-1,j,board)+getNeiburt(i-1,j+1,board)
                        +getNeiburt(i,j-1,board)+getNeiburt(i,j+1,board)
                        +getNeiburt(i+1,j-1,board)+getNeiburt(i+1,j,board)+getNeiburt(i+1,j+1,board);
                if((count>3||count<2)&&board[i][j]==1){
                    board[i][j] = -1;
                }else if(count==3&&board[i][j]==0){
                    board[i][j] = 2;
                }
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==-1){
                    board[i][j] = 0;
                }else if(board[i][j]==2){
                    board[i][j] = 1;
                }
            }
        }
    }
    public int getNeiburt(int i,int j,int[][] board){
        if(i<0||i>=board.length||j<0||j>=board[0].length){
            return 0;
        }
        if(board[i][j]==-1){
            return 1;
        }else if(board[i][j]==2){
            return 0;
        }
        return board[i][j];
    }
}

// 额外空间，孬方法
class Solution2 {
    public void gameOfLife(int[][] board) {
        int[][] copyBoard = new int[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                int count = getNeiburt(i-1,j-1,board)+getNeiburt(i-1,j,board)+getNeiburt(i-1,j+1,board)
                        +getNeiburt(i,j-1,board)+getNeiburt(i,j+1,board)
                        +getNeiburt(i+1,j-1,board)+getNeiburt(i+1,j,board)+getNeiburt(i+1,j+1,board);
                if(count>3||count<2){
                    copyBoard[i][j] = 0;
                }else if(count==3){
                    copyBoard[i][j] = 1;
                }else{
                    copyBoard[i][j] = board[i][j];
                }
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                board[i][j] = copyBoard[i][j];
            }
        }
    }
    public int getNeiburt(int i,int j,int[][] board){
        if(i<0||i>=board.length||j<0||j>=board[0].length){
            return 0;
        }
        return board[i][j];
    }
}