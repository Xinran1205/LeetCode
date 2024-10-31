import java.util.*;

// 广度搜索
// 思路：遇到一个没有遍历过的节点陆地，计数器就加一，然后把该节点陆地所能遍历到的陆地都标记上

class Solution {
    int[][] visited;
    public int numIslands(char[][] grid) {
        int count = 0;
        visited = new int[grid.length][grid[0].length];
        for(int x = 0; x < grid.length; x++) {
            for(int y = 0; y < grid[0].length; y++) {
                // 只有当当前节点既没有被访问过，又是岛屿时，才处理
                if(visited[x][y]!=1&&grid[x][y] == '1'){
                    bfs(grid, x, y);
                    count++;
                }
            }
        }
        return count;
    }

    public void bfs(char[][] grid,int x,int y){
        Queue<int[]> list = new LinkedList<>();
        // 先把当前节点加入
        list.add(new int[] { x, y });
        visited[x][y] = 1;

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
                        visited[newX][newY] == 0 && grid[newX][newY] == '1') {
                    list.add(new int[]{newX, newY});
                    visited[newX][newY] = 1; // 立即标记为已访问
                }
            }
        }
    }
}


//import java.util.*;
// 卡码网 岛屿数量
public class Main{
    static int[][] visited;

    public static void main(String[] arg){
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] graph = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                graph[i][j] = scanner.nextInt();
            }
        }
        int count = 0;
        visited = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(graph[i][j]!=0&&visited[i][j]!=1){
                    bfs(graph,i,j);
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void bfs(int[][] graph, int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        visited[x][y] = 1;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int[][] dir = {{1,0},{0,-1},{-1,0},{0,1}};
            for(int[] a:dir){
                int curX = cur[0]+a[0];
                int curY = cur[1]+a[1];
                if(curX>=0&&curX<graph.length&&curY>=0&&curY<graph[0].length
                        &&visited[curX][curY]==0&&graph[curX][curY]==1){
                    queue.add(new int[]{curX,curY});
                    visited[curX][curY] = 1;
                }
            }
        }
    }
}

// 卡码网 DFS 深搜,主体逻辑基本与广搜一致
public class Main{
    static int [][] visited;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int [][] graph = new int[m][n];
        visited = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                graph[i][j] = scanner.nextInt();
            }
        }
        int count = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(graph[i][j]!=0&&visited[i][j]!=1){
                    dfs(graph,i,j);
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void dfs(int[][] graph, int x, int y){
        visited[x][y] = 1;
        int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
        for(int[] a : dir){
            int curX = x+a[0];
            int curY = y+a[1];
            if(curX>=0&&curX<graph.length&&curY>=0&&curY<graph[0].length
                    &&visited[curX][curY]!=1&&graph[curX][curY]!=0){
                dfs(graph,curX,curY);
            }
        }
    }
}
