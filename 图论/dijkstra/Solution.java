import java.util.*;


// 先手写一个优先队列
// 手动实现优先队列+Dijkstra算法
class priQueue{
    int size;
    int capacity;
    Node[] heap;

    priQueue(int capacity){
        this.size = 0;
        this.capacity = capacity;
        heap = new Node[capacity];
    }

    public void insertVal(Node val){
        if(size >= capacity){
            throw new IllegalStateException("优先队列已满");
        }
        heap[size] = val;
        swim(size);
        size++;
    }

    public void swim(int index){
        if(index<1){
            return;
        }
        int parent = (index-1)/2;
        if(index>0&&heap[parent].weight>heap[index].weight){
            Node tmp = heap[parent];
            heap[parent] = heap[index];
            heap[index] = tmp;
            swim(parent);
        }
    }

    public Node PopMin(){
        if(size==0){
            throw new IllegalStateException("优先队列为空");
        }
        Node ret = heap[0];
        // 赋值成最大元素
        heap[0] = heap[size-1];
        // 把最大元素沉下去，要先把size减1
        size--;
        sink(0);
        return ret;
    }

    public void sink(int index){
        int minIndex = index;
        if(2*index+1<size&&heap[2*index+1].weight<heap[index].weight){
            minIndex = 2*index+1;
        }
        if(2*index+2<size&&heap[2*index+2].weight<heap[minIndex].weight){
            minIndex = 2*index+2;
        }
        if(minIndex!=index){
            Node tmp = heap[minIndex];
            heap[minIndex] = heap[index];
            heap[index] = tmp;
            sink(minIndex);
        }
    }
}

// 记录一个节点的下标和到他的权重，邻接表一行的一个节点
class Node{
    int index;
    int weight;

    Node(int index,int weight){
        this.index = index;
        this.weight = weight;
    }
}

class dijkstra{
    int NumberOfNodes;
    List<List<Node>> graph;

    dijkstra(int NumberOfNodes){
        this.NumberOfNodes = NumberOfNodes;
        graph = new ArrayList<>();
        //初始化graph
        for(int i=0;i<NumberOfNodes;i++){
            graph.add(new ArrayList<>());
        }
    }

    public void addEdge(int source,int dest, int weight){
        graph.get(source).add(new Node(dest,weight));
    }

    public int[] buildGraph(int source){
        boolean[] visited = new boolean[NumberOfNodes];
        int[] distance = new int[NumberOfNodes];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[source] = 0;
        priQueue pq = new priQueue(NumberOfNodes);
        pq.insertVal(new Node(source,0));
        while(pq.size>0){
            Node minNode = pq.PopMin();
            visited[minNode.index] = true;
            for(Node node:graph.get(minNode.index)){
                if(!visited[node.index]&&distance[minNode.index]+node.weight<distance[node.index]){
                    distance[node.index] = distance[minNode.index]+node.weight;
                    pq.insertVal(new Node(node.index,distance[node.index]));
                }
            }
        }
        return distance;
    }
}

public class Solution {
    public static void main(String[] args) {
        // 我把城市名字转换成Integer
        Map<String,Integer> hashMap = new HashMap<>();

        hashMap.put("Tromse",0);
        hashMap.put("Bergen",1);
        hashMap.put("Oulu",2);
        hashMap.put("Murmansk",3);
        hashMap.put("Edinburgh",4);
        hashMap.put("Stockholm",5);
        hashMap.put("Saint Petersburg",6);
        hashMap.put("Arkhangel'sk",7);
        hashMap.put("London",8);
        hashMap.put("Hamburg",9);
        hashMap.put("Budapest",10);
        hashMap.put("kyiv",11);
        hashMap.put("Moscow",12);
        hashMap.put("Ulyaovsk",13);
        hashMap.put("Mardrid",14);
        hashMap.put("Rome",15);
        hashMap.put("Isatnbul",16);
        hashMap.put("Donetsk",17);
        hashMap.put("Baku",18);

        // 然后我需要使用邻接矩阵，因为是无向图
        int[][] graph = new int[19][19];
        // 手动初始化邻接矩阵
        for(int i=0;i<19;i++){
            for(int j=0;j<19;j++){
                graph[i][j] = Integer.MAX_VALUE;
            }
        }
        // 手动添加边
        graph[0][1] = 4;
        graph[0][2] = 2;
        graph[0][3] = 2;
        graph[1][2] = 4;
        graph[1][4] = 2;
        graph[1][5] = 2;
        graph[1][9] = 2;
        graph[2][3] = 4;
        graph[2][5] = 2;
        graph[2][6] = 1;
        graph[2][7] = 2;
        graph[3][7] = 2;
        graph[4][8] = 1;
        graph[4][9] = 2;
        graph[5][9] = 2;
        graph[5][10] = 3;
        graph[5][11] = 3;
        graph[5][6] = 2;
        graph[6][11] = 2;
        graph[6][12] = 1;
        graph[6][7] = 2;
        graph[7][12] = 3;
        graph[7][13] = 4;
        graph[8][14] = 2;
        graph[8][15] = 3;
        graph[8][9] = 1;
        graph[9][15] = 3;
        graph[9][10] = 2;
        graph[10][15] = 1;
        graph[10][11] = 1;
        graph[10][16] = 2;
        graph[11][16] = 2;
        graph[11][17] = 1;
        graph[11][12] = 1;
        graph[12][17] = 1;
        graph[12][13] = 1;
        graph[13][17] = 2;
        graph[13][18] = 6;
        graph[14][15] = 4;
        graph[15][16] = 2;
        graph[16][17] = 2;
        graph[16][18] = 3;
        graph[17][18] = 2;

        // 填充另一边
        for(int i=0;i<19;i++){
            for(int j=0;j<19;j++){
                if(graph[i][j]!=Integer.MAX_VALUE){
                    graph[j][i] = graph[i][j];
                }
            }
        }

        // 测试算法
        dijkstra d = new dijkstra(19);
        for(int i=0;i<19;i++){
            for(int j=0;j<19;j++){
                if(graph[i][j]!=Integer.MAX_VALUE){
                    d.addEdge(i,j,graph[i][j]);
                }
            }
        }
        int[] distance = d.buildGraph(0);

        // 打印起始城市到其他城市的最短路径
        for(Map.Entry<String,Integer> entry:hashMap.entrySet()){
            System.out.println(entry.getKey()+" "+distance[entry.getValue()]);
        }

    }
}








































//import java.util.*;
//
//class Graph {
//    private int numVertices; // 图中顶点的数量
//    private List<List<Node>> adjList; // 邻接表
//
//    public Graph(int numVertices) {
//        this.numVertices = numVertices;
//        adjList = new ArrayList<>();
//        for (int i = 0; i < numVertices; i++) {
//            adjList.add(new ArrayList<>());
//        }
//    }
//
//    // 向图中添加边
//    public void addEdge(int src, int dest, int weight) {
//        adjList.get(src).add(new Node(dest, weight));
//    }
//
//    // Dijkstra 算法的实现
//    public void dijkstra(int startVertex) {
//        int[] distances = new int[numVertices]; // 距离数组
//        boolean[] visited = new boolean[numVertices]; // 访问数组
//        PriorityQueue<Node> pq = new PriorityQueue<>(numVertices, new Node());
//
//        // 初始化距离和访问状态
//        Arrays.fill(distances, Integer.MAX_VALUE);
//        distances[startVertex] = 0;
//        pq.add(new Node(startVertex, 0)); // 从起点开始
//
//        while (!pq.isEmpty()) {
//            // 取出队列中最小距离顶点
//            int u = pq.poll().node;
//
//            // 标记为已访问
//            visited[u] = true;
//
//            // 遍历邻接点
//            for (Node edge : adjList.get(u)) {
//                int v = edge.node;
//                int weight = edge.cost;
//
//                // 如果未访问并且通过u能够获得更短的路径
//                if (!visited[v] && distances[u] + weight < distances[v]) {
//                    distances[v] = distances[u] + weight;
//                    pq.add(new Node(v, distances[v]));
//                }
//            }
//        }
//
//        printSolution(distances);
//    }
//
//    // 输出所有顶点的最短路径长度
//    private void printSolution(int[] distances) {
//        System.out.println("Vertex Distance from Source");
//        for (int i = 0; i < numVertices; i++)
//            System.out.println(i + " \t\t " + distances[i]);
//    }
//
//    // 辅助类，表示顶点和与其相连的边的权重
//    class Node implements Comparator<Node> {
//        public int node;
//        public int cost;
//
//        public Node() {}
//
//        public Node(int node, int cost) {
//            this.node = node;
//            this.cost = cost;
//        }
//
//        @Override
//        public int compare(Node node1, Node node2) {
//            if (node1.cost < node2.cost)
//                return -1;
//            if (node1.cost > node2.cost)
//                return 1;
//            return 0;
//        }
//    }
//}
//
//public class Solution {
//    public static void main(String[] args) {
//        Graph g = new Graph(6); // 创建一个有6个顶点的图
//        g.addEdge(0, 1, 5);
//        g.addEdge(0, 2, 3);
//        g.addEdge(1, 3, 6);
//        g.addEdge(1, 2, 2);
//        g.addEdge(2, 4, 4);
//        g.addEdge(2, 5, 2);
//        g.addEdge(2, 3, 7);
//        g.addEdge(3, 4, -1); // 注意这里不应该有负权重，这里仅作示例
//        g.addEdge(4, 5, -2); // 同上，Dijkstra算法不适用于负权重
//
//        g.dijkstra(0); // 从顶点0开始运行Dijkstra算法
//    }
//}
