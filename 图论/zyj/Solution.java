import java.util.*;
import java.io.*;

class Dijkstra {
    int numberOfNodes;
    int[][] graph;

    Dijkstra(int numberOfNodes, int[][] graph) {
        this.numberOfNodes = numberOfNodes;
        this.graph = graph;
    }

    public int[] buildGraph(int source, int[] prev) {
        boolean[] visited = new boolean[numberOfNodes];
        int[] distance = new int[numberOfNodes];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(prev, -1); // 初始化前驱数组
        distance[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node minNode = pq.poll();
            int u = minNode.index;
            if (visited[u]) continue;
            visited[u] = true;

            for (int v = 0; v < numberOfNodes; v++) {
                if (graph[u][v] != Integer.MAX_VALUE && !visited[v]) {
                    int newDist = distance[u] + graph[u][v];
                    if (newDist < distance[v]) {
                        distance[v] = newDist;
                        prev[v] = u; // 更新前驱节点
                        pq.add(new Node(v, newDist));
                    }
                }
            }
        }
        return distance;
    }
}

class Node {
    int index;
    int weight;

    Node(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }
}

public class Solution {
    public static void main(String[] args) {
        String filename = "graph.txt";
        Map<String, Integer> cityIndices = new HashMap<>();
        List<String> cityNames = new ArrayList<>();
        int numberOfNodes = 0;
        int[][] graph = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // 读取节点数量
            numberOfNodes = Integer.parseInt(br.readLine().trim());
            graph = new int[numberOfNodes][numberOfNodes];

            // 初始化邻接矩阵
            for (int i = 0; i < numberOfNodes; i++) {
                Arrays.fill(graph[i], Integer.MAX_VALUE);
            }

            // 读取节点名称并建立索引
            for (int i = 0; i < numberOfNodes; i++) {
                String cityName = br.readLine().trim();
                cityIndices.put(cityName, i);
                cityNames.add(cityName);
            }

            // 读取边数量
            int numberOfEdges = Integer.parseInt(br.readLine().trim());

            // 读取边信息并添加到图中
            for (int i = 0; i < numberOfEdges; i++) {
                String[] edgeInfo = br.readLine().trim().split("\\s+");
                String city1 = edgeInfo[0];
                String city2 = edgeInfo[1];
                int weight = Integer.parseInt(edgeInfo[2]);

                int index1 = cityIndices.get(city1);
                int index2 = cityIndices.get(city2);

                // 添加无向边
                graph[index1][index2] = weight;
                graph[index2][index1] = weight;
            }

            // 创建 Dijkstra 对象
            Dijkstra dijkstra = new Dijkstra(numberOfNodes, graph);

            // 从起始城市计算最短路径
            int sourceIndex = cityIndices.get("Tromse"); // 您可以更改起始城市
            int[] prev = new int[numberOfNodes]; // 用于存储路径
            int[] distances = dijkstra.buildGraph(sourceIndex, prev);

            // 打印结果
            for (int i = 0; i < numberOfNodes; i++) {
                if (i == sourceIndex) continue; // 跳过起始城市自身
                System.out.println("起点: " + cityNames.get(sourceIndex));
                System.out.println("终点: " + cityNames.get(i));
                System.out.println("路径长度: " + distances[i]);
                System.out.print("路径: ");
                printPath(i, prev, cityNames);
                System.out.println("\n--------------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 递归打印路径
    public static void printPath(int current, int[] prev, List<String> cityNames) {
        if (prev[current] == -1) {
            System.out.print(cityNames.get(current));
            return;
        }
        printPath(prev[current], prev, cityNames);
        System.out.print(" -> " + cityNames.get(current));
    }
}
