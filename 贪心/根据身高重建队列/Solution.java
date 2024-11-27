import java.util.*;

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        // 这一题思路，先按h身高从高到低排序，排完序再思考k
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 如果身高相同，按k从小到大排序
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                // 否则按身高从大到小排序
                return o2[0] - o1[0];
            }
        });

        List<int[]> list = new ArrayList<>();
        for (int[] p : people) {
            int position = p[1];
            list.add(position, p);
        }
        return list.toArray(new int[people.length][2]);
    }
}