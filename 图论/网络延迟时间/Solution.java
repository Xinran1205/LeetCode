import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> list = new ArrayList<>();
        // n个节点，初始化邻接表
        for(int i=0;i<n;i++){
            list.add(new ArrayList<>());
        }
        // 遍历所有的边，加入邻接表
        for(int ed=0;ed<times.length;ed++){
            list.get(times[ed][0]-1).add(new int[]{times[ed][1]-1,times[ed][2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
                (a, b) -> a[1] - b[1]);

        // 初始化dist数组全部为最大值
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 加入源节点到pq,dist设置为0
        int[] visited = new int[n];
        dist[k-1] = 0;
        pq.offer(new int[]{k-1,0});
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            int nodeNumber = node[0];
            // 这个是source到这个节点的最小距离
            int minDis = node[1];
            visited[nodeNumber] = 1;

            //遍历nodeNumber相邻的节点
            for(int[] listT:list.get(nodeNumber)){
                if(visited[listT[0]]!=1&&dist[listT[0]]>minDis+listT[1]){
                    dist[listT[0]] = minDis+listT[1];
                    pq.offer(new int[]{listT[0],dist[listT[0]]});
                }
            }
        }
        //遍历dis
        int ret = 0;
        for(int i=0;i<dist.length;i++){
            if(dist[i]==Integer.MAX_VALUE){
                return -1;
            }
            if(dist[i]>ret){
                ret = dist[i];
            }
        }
        return ret;
    }
}


// 邻接矩阵解法
class Solution2 {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] graph = new int[n][n];
        // n个节点，初始化邻接矩阵
        // 初始化邻接矩阵，默认连接为无穷大
        for(int i = 0; i < n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }
        // 赋值邻接矩阵
        for(int i=0;i<times.length;i++){
            graph[times[i][0]-1][times[i][1]-1] = times[i][2];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
                (a, b) -> a[1] - b[1]);

        // 初始化dist数组全部为最大值
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 加入源节点到pq,dist设置为0
        int[] visited = new int[n];
        dist[k-1] = 0;
        pq.offer(new int[]{k-1,0});
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            int nodeNumber = node[0];
            // 这个是source到这个节点的最小距离
            int minDis = node[1];
            visited[nodeNumber] = 1;

            //遍历nodeNumber相邻的节点
            for(int i=0;i<n;i++){
                // 这里要注意，要多一个判断
                if(graph[nodeNumber][i] < Integer.MAX_VALUE) {
                    if(visited[i]!=1&&dist[i]>minDis+graph[nodeNumber][i]){
                        dist[i] = minDis+graph[nodeNumber][i];
                        pq.offer(new int[]{i,dist[i]});
                    }
                }
            }
        }
        //遍历dis
        int ret = 0;
        for(int i=0;i<dist.length;i++){
            if(dist[i]==Integer.MAX_VALUE){
                return -1;
            }
            if(dist[i]>ret){
                ret = dist[i];
            }
        }
        return ret;
    }
}