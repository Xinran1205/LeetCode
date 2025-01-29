// 简单优化过的，防止使用visited，并且使用boolean返回值
class Solution {
    public boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(backTracing(i,j,board,word,0)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean backTracing(int i, int j, char[][] board, String word, int curIndex){
        if(board[i][j]!=word.charAt(curIndex)){
            return false;
        }
        if(curIndex==word.length()-1){
            return true;
        }
        board[i][j] = '#';
        int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
        for(int[] dir:directions){
            int newX = i+dir[0];
            int newY = j+dir[1];
            if(newX>=0&&newX<board.length&&newY>=0&&newY<board[0].length
                    &&board[newX][newY]!='#'){
                if(backTracing(newX, newY, board, word, curIndex + 1)){
                    return true;
                }
            }
        }
        board[i][j] = word.charAt(curIndex);
        return false;
    }
}

// 自己之前写的源代码,没有上面好！但是其实性能差不多
class Solution2 {
    boolean ret;
    public boolean exist(char[][] board, String word) {
        ret = false;
        int[][] visited = new int[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                backTracing(i,j,board,word,0,visited);
                if(ret){
                    return true;
                }
            }
        }
        return false;
    }
    public void backTracing(int i, int j, char[][] board, String word, int curIndex,int[][] visited){
        if(board[i][j]!=word.charAt(curIndex)){
            return;
        }
        if(curIndex==word.length()-1){
            ret = true;
            return;
        }
        visited[i][j] = 1;
        int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
        for(int[] dir:directions){
            int newX = i+dir[0];
            int newY = j+dir[1];
            if(newX>=0&&newX<board.length&&newY>=0&&newY<board[0].length
                    &&visited[newX][newY]!=1){
                backTracing(newX,newY,board,word,curIndex+1,visited);
            }
        }
        visited[i][j] = 0;
    }
}

// 究极优化
class Solution3 {
    public static int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
    public boolean exist(char[][] board, String word) {
        // 优化一,先计算board中每个字母出现的次数，再计算word中每个字母的次数，如果比board多，则直接false。
        int[] cnt = new int[128];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                cnt[board[i][j]]++;
            }
        }
        char[] w = word.toCharArray();
        int[] wordCnt = new int[128];
        for (char c : w) {
            if (++wordCnt[c] > cnt[c]) {
                return false;
            }
        }
        // 优化二，这个意思是如果word中最后一个字母在board中出现的次数比第一个字母少，那么就反转word，这样可以减少递归次数
        if (cnt[w[w.length - 1]] < cnt[w[0]]) {
            w = new StringBuilder(word).reverse().toString().toCharArray();
        }

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(backTracing(i,j,board,w,0)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean backTracing(int i, int j, char[][] board, char[] word, int curIndex){
        if(board[i][j]!=word[curIndex]){
            return false;
        }
        if(curIndex==word.length-1){
            return true;
        }
        board[i][j] = '#';
        for(int[] dir:directions){
            int newX = i+dir[0];
            int newY = j+dir[1];
            if(newX>=0&&newX<board.length&&newY>=0&&newY<board[0].length
                    &&board[newX][newY]!='#'){
                if(backTracing(newX, newY, board, word, curIndex + 1)){
                    return true;
                }
            }
        }
        board[i][j] = word[curIndex];
        return false;
    }
}