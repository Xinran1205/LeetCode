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

    public void buildGraph(int source){
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
        printPath(distance);
    }

    // 打印最短路径
    public void printPath(int[] distance){
        System.out.println("Vertex Distance from Source");
        for(int i=0;i<NumberOfNodes;i++){
            System.out.println(i+" \t\t "+distance[i]);
        }
    }
}

public class Solution {
    public static void main(String[] args) {
        dijkstra g = new dijkstra(6);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 6);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 5, 2);
        g.addEdge(2, 3, 7);
        g.buildGraph(0); // 从顶点0开始运行Dijkstra算法
        g.buildGraph(1);
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
