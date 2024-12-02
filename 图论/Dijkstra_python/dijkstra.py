import sys

class Node:
    def __init__(self, index, weight):
        self.index = index
        self.weight = weight

class priQueue:
    def __init__(self, capacity):
        self.size = 0
        self.capacity = capacity
        self.heap = [None] * capacity

    def insertVal(self, val):
        if self.size >= self.capacity:
            raise Exception("优先队列已满")
        self.heap[self.size] = val
        self.swim(self.size)
        self.size += 1

    def swim(self, index):
        if index < 1:
            return
        parent = (index - 1) // 2
        if self.heap[parent].weight > self.heap[index].weight:
            self.heap[parent], self.heap[index] = self.heap[index], self.heap[parent]
            self.swim(parent)

    def PopMin(self):
        if self.size == 0:
            raise Exception("优先队列为空")
        ret = self.heap[0]
        self.size -= 1
        self.heap[0] = self.heap[self.size]
        self.heap[self.size] = None  # 清除最后一个元素
        self.sink(0)
        return ret

    def sink(self, index):
        minIndex = index
        left = 2 * index + 1
        right = 2 * index + 2
        if left < self.size and self.heap[left].weight < self.heap[minIndex].weight:
            minIndex = left
        if right < self.size and self.heap[right].weight < self.heap[minIndex].weight:
            minIndex = right
        if minIndex != index:
            self.heap[minIndex], self.heap[index] = self.heap[index], self.heap[minIndex]
            self.sink(minIndex)

class Dijkstra:
    def __init__(self, numberOfNodes, graph):
        self.numberOfNodes = numberOfNodes
        self.graph = graph

    def buildGraph(self, source, prev):
        visited = [False] * self.numberOfNodes
        distance = [float('inf')] * self.numberOfNodes
        for i in range(len(prev)):
            prev[i] = -1  # 初始化前驱数组
        distance[source] = 0

        pq = priQueue(self.numberOfNodes)
        pq.insertVal(Node(source, 0))

        while pq.size > 0:
            minNode = pq.PopMin()
            u = minNode.index
            if visited[u]:
                continue
            visited[u] = True

            for v in range(self.numberOfNodes):
                if self.graph[u][v] != float('inf') and not visited[v]:
                    newDist = distance[u] + self.graph[u][v]
                    if newDist < distance[v]:
                        distance[v] = newDist
                        prev[v] = u  # 更新前驱节点
                        pq.insertVal(Node(v, newDist))
        return distance

def printPath(current, prev, cityNames):
    if prev[current] == -1:
        print(cityNames[current], end="")
        return
    printPath(prev[current], prev, cityNames)
    print(" -> " + cityNames[current], end="")

def main():
    filename = "graph.txt"
    cityIndices = {}
    cityNames = []
    numberOfNodes = 0
    graph = None

    try:
        with open(filename, 'r', encoding='utf-8') as br:
            # 读取节点数量
            numberOfNodes = int(br.readline().strip())
            graph = [[float('inf')] * numberOfNodes for _ in range(numberOfNodes)]

            # 读取节点名称并建立索引
            for i in range(numberOfNodes):
                cityName = br.readline().strip()
                cityIndices[cityName] = i
                cityNames.append(cityName)

            # 读取边数量
            numberOfEdges = int(br.readline().strip())

            # 读取边信息并添加到图中
            for i in range(numberOfEdges):
                edgeInfo = br.readline().strip().split()
                city1 = edgeInfo[0]
                city2 = edgeInfo[1]
                weight = int(edgeInfo[2])

                index1 = cityIndices[city1]
                index2 = cityIndices[city2]

                # 添加无向边
                graph[index1][index2] = weight
                graph[index2][index1] = weight

        # 创建 Dijkstra 对象
        dijkstra = Dijkstra(numberOfNodes, graph)

        # 从起始城市计算最短路径
        sourceIndex = cityIndices.get("Tromse")  # 您可以更改起始城市
        prev = [-1] * numberOfNodes  # 用于存储路径
        distances = dijkstra.buildGraph(sourceIndex, prev)

        # 打印结果
        for i in range(numberOfNodes):
            if i == sourceIndex:
                continue  # 跳过起始城市自身
            print("起点: " + cityNames[sourceIndex])
            print("终点: " + cityNames[i])
            print("路径长度: " + str(distances[i]))
            print("路径: ", end="")
            printPath(i, prev, cityNames)
            print("\n--------------------------")

    except Exception as e:
        print(e)

if __name__ == "__main__":
    main()
