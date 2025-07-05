import java.util.*;
class Solution {
    public void solve(char[][] board) {
        // 把被围绕的区域变成X，边缘部分排除（不能变，并且有带上边缘的，整个都不能变）

        // 思路就是看保存哪些O，想法就是统计边缘的O，从这个O出发开始

        //用队列 BFS
        // 边界检查
        if (board == null || board.length < 2 || board[0].length < 2) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        // 四个方向数组：下、上、右、左
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Deque<int[]> deque = new ArrayDeque<>();

        // 1. 从边界上的 'O' 开始 BFS，将所有可达 'O' 标记为 '#'
        // 第一行和最后一行
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                markSafe(0, j, board, m, n, dirs, deque);
            }
            if (board[m - 1][j] == 'O') {
                markSafe(m - 1, j, board, m, n, dirs, deque);
            }
        }
        // 第一列和最后一列
        for (int i = 1; i < m - 1; i++) {
            if (board[i][0] == 'O') {
                markSafe(i, 0, board, m, n, dirs, deque);
            }
            if (board[i][n - 1] == 'O') {
                markSafe(i, n - 1, board, m, n, dirs, deque);
            }
        }

        // 2. 遍历全板，翻转被围绕的 'O' 并恢复安全标记 '#'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    // 未被标记的 'O'，说明被围住
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    // 安全的还原回来
                    board[i][j] = 'O';
                }
            }
        }
    }

    /**
     * 对从 (i, j) 出发的边界 'O' 区域做 BFS 标记，将它们改为 '#'
     */
    private void markSafe(int i, int j, char[][] board, int m, int n, int[][] dirs, Deque<int[]> queue) {
        // 入队并标记
        queue.add(new int[]{i, j});
        board[i][j] = '#';

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];
            // 遍历四个方向
            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];
                // 检查边界和未访问的 'O'
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && board[nx][ny] == 'O') {
                    queue.add(new int[]{nx, ny});
                    board[nx][ny] = '#';
                }
            }
        }
    }
}