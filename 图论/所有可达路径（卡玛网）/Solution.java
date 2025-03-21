import java.util.*;

// 这是leetcode 797. 所有可能的路径
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // 打印graph看一下
        // 这个很有意思，java二维数组可以是不规则的，也就是说每一行的长度可以不一样
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        // 这里加个0是因为题目说了，起始节点是0
        path.add(0);
        dfs(graph, 0, graph.length - 1);
        return result;
    }

    public void dfs(int[][] graph, int curNode, int destNode){
        if(curNode==destNode){
            result.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i : graph[curNode]) { // 遍历当前链接的所有节点
            path.add(i); // 遍历到的节点加入到路径中来
            dfs(graph, i, destNode); // 进入下一层递归
            path.remove(path.size() - 1); // 回溯，撤销本节点
        }
    }
}

//使用 result.add(path);： 当你使用 result.add(path); 时，你实际上是将 path 列表的引用（而不是列表的内容）添加到 result 列表中。
// 这意味着，当 path 列表在后续的代码中被修改时（例如在回溯时删除元素），这些修改也会反映在 result 列表中保存的相同引用上。
// 因此，所有的 result 中的元素（它们都是指向同一个 path 的引用）会在算法结束时显示相同的最终状态，这通常是一个空列表，在本题中全是0。

//使用 result.add(new ArrayList<Integer>(path));： 使用这种方式时，你每次都创建 path 列表的一个新的副本，
// 并将这个副本添加到 result 列表中。这样做的好处是，无论 path 列表后来如何变化，添加到 result 中的列表副本都保持不变。
// 这对于深度优先搜索（DFS）这样需要回溯的算法来说非常关键，因为它确保保存的每条路径都是在递归过程中的一个独立快照。


// 这是卡玛网，采用邻接表实现
//import java.util.*;

public class Main{
    static List<List<Integer>> result = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();

    public static void main(String[] arg){
        Scanner scanner = new Scanner(System.in);
        int nodeNumber = scanner.nextInt();
        int edgeNumber = scanner.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<=nodeNumber;i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0;i<edgeNumber;i++){
            int node = scanner.nextInt();
            int nextNode = scanner.nextInt();
            graph.get(node).add(nextNode);
        }
        path.add(1);
        dfs(graph,1,nodeNumber);

        //打印result;
        if(result.isEmpty()){
            System.out.println(-1);
        }
        for (List<Integer> pa : result) {
            // 这个循环遍历 pa 列表中的每个元素，除了最后一个元素。这是为了在每个元素后面打印一个空格，
            // 但避免在行的末尾也打印一个空格。循环中的每次迭代都打印当前索引 i 对应的元素，并在其后加一个空格。
            for (int i = 0; i < pa.size() - 1; i++) {
                System.out.print(pa.get(i) + " ");
            }
            // 打印路径中的最后一个节点，在输出最后一个节点后换行，这样每条路径都会单独占一行
            System.out.println(pa.get(pa.size() - 1));
        }
    }

    public static void dfs(List<List<Integer>> graph, int node, int dest){
        if(node==dest){
            result.add(new ArrayList<>(path));
            return;
        }
        List<Integer> adjNode = graph.get(node);
        for(Integer i : adjNode){
            path.add(i);
            dfs(graph,i,dest);
            path.remove(path.size()-1);
        }
    }
}
