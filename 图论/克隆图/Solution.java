import java.util.*;
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        // 旧节点 -> 新节点 映射
        HashMap<Node, Node> hashMap = new HashMap<>();
        // 克隆第一个节点并放入映射
        Node start = new Node(node.val);
        hashMap.put(node, start);
        // 用队列做 BFS
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(node);

        while (!deque.isEmpty()) {
            Node currentNode = deque.poll();
            Node currentClone = hashMap.get(currentNode);

            for (Node nei : currentNode.neighbors) {
                if (!hashMap.containsKey(nei)) {
                    // 第一次遇到该邻居时，克隆并入队
                    Node neiClone = new Node(nei.val);
                    hashMap.put(nei, neiClone);
                    deque.add(nei);
                }
                // 这一步非常非常重要，可以理解成图的那个性质，结构定义，我中有你，你中有我
                // 无论是否第一次遇到，都要把邻居的克隆加到当前克隆的邻居列表里
                currentClone.neighbors.add(hashMap.get(nei));
            }
        }

        return start;
    }
}