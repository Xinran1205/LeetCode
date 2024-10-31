import java.util.*;

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