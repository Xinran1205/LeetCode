import java.util.*;

//        问题本质
//        你可以将每门课程看成图中的一个 节点，先修关系看成 有向边，也就是说如果存在先修关系 [a, b]，就相当于从 b 指向 a 的一条边（意思是先学 b 后学 a）。
//        这样问题就转换为：判断这个有向图中是否存在环。
//
//        如果图中存在环（例如课程 A 依赖于课程 B，而课程 B 又依赖于课程 A），那么就没有合法的学习顺序。
//        如果图中没有环，那么就一定存在一种顺序可以完成所有课程。

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1.把入度为0的元素加入队列
        // 2.把队列中这些元素出度（指向）的元素入度-1；
        // 3.把入度为0的元素加入队列 循环！
        // 如果队列为空，并且仍然有入度不为0的元素，说明false

        // 这个数组用于记录每个课程的入度数
        int[] indegrees = new int[numCourses];
        // 邻接表，用于存储每个课程指向的后继课程列表
        List<List<Integer>> adjacency = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            adjacency.add(new ArrayList<>());
        }
        // Get the indegree and adjacency of every course.
        // prerequisites[i] = [a, b]  b -> a 学了b才能学a
        for(int[] cp : prerequisites) {
            indegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        // Get all the courses with the indegree of 0.
        for(int i = 0; i < numCourses; i++){
            if(indegrees[i] == 0){
                queue.add(i);
            }
        }

        // BFS TopSort.
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            // 把这门课的后继课程的入度-1
            // 把这个course连着后面的课indegree--
            for(int cur : adjacency.get(pre)){
                indegrees[cur]--;
                if(indegrees[cur] == 0) {
                    queue.add(cur);
                }
            }
        }
        return numCourses == 0;
    }
}