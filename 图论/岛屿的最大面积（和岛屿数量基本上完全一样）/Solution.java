import java.util.*;

// 这一题我基本上把前一题岛屿数量代码直接拿过来，简单修改就可以用了！

class Solution {
    int[][] visited;
    int count;
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        visited = new int[grid.length][grid[0].length];
        for(int x = 0; x < grid.length; x++) {
            for(int y = 0; y < grid[0].length; y++) {
                // 只有当当前节点既没有被访问过，又是岛屿时，才处理
                if(visited[x][y]!=1&&grid[x][y] == 1){
                    count = 0;
                    bfs(grid, x, y);
                    if(count>max){
                        max = count;
                    }
                }
            }
        }
        return max;
    }

    public void bfs(int[][] grid,int x,int y){
        Queue<int[]> list = new LinkedList<>();
        // 先把当前节点加入
        list.add(new int[] { x, y });
        visited[x][y] = 1;
        count++;

        while(!list.isEmpty()){
            int[] cur = list.poll();
            int curX = cur[0];
            int curY = cur[1];
            // 遍历这个结点的4个方向,这四个方向顺序随意
            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : directions) {
                int newX = curX + dir[0];
                int newY = curY + dir[1];
                // 判断这4个方向是否在范围内
                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length &&
                        visited[newX][newY] == 0 && grid[newX][newY] == 1) {
                    list.add(new int[]{newX, newY});
                    visited[newX][newY] = 1; // 立即标记为已访问
                    count++;
                }
            }
        }
    }
}