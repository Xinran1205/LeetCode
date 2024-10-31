import java.util.*;

class Solution {
    public int orangesRotting(int[][] grid) {
        int count = 0; // count 表示新鲜橘子的数量
        // 先遍历一遍，统计新鲜橘子的数量，和把初始腐烂橘子的坐标加入队列
        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    count++;
                } else if (grid[r][c] == 2) {
                    queue.add(new int[]{r, c});
                }
            }
        }
        // 注意这里不要像岛屿问题遍历每个点，因为他是一种连着的传播问题
        int round = 0;
        while(count>0&&!queue.isEmpty()){
            round++;
            int n = queue.size();
            // 这里要处理一遍当前所有腐烂的橘子，他们都进行一次传播，并且一定要用队列
            for (int i = 0; i < n; i++) {
                int[] cur = queue.poll();
                int curX = cur[0];
                int curY = cur[1];
                // 遍历这个结点的4个方向,这四个方向顺序随意
                int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                for (int[] dir : directions) {
                    int newX = curX + dir[0];
                    int newY = curY + dir[1];
                    // 判断这4个方向是否在范围内
                    if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length &&
                            grid[newX][newY] == 1) {
                        queue.add(new int[]{newX, newY});
                        grid[newX][newY] = 2;
                        count--;
                    }
                }
            }
        }

        if (count > 0) {
            return -1;
        } else {
            return round;
        }
    }
}