import java.util.*;
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            hashMap.put(i,hashMap.getOrDefault(i,0)+1);
        }
        // 然后对value进行排序即可，但是这样就是要对n个值排序了，其实没必要
        // 可以使用优先队列（堆排序）只需要维护k个有序，时间复杂度是O(nlogk)

        //使用小顶堆，因为堆里面最后放的是最大的k个元素，每次pop是从根pop，
        //如果大顶堆，每次把最大的pop了，直接导致最后是k个最小元素了
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1,n2)->hashMap.get(n1)-hashMap.get(n2));
        for(int key:hashMap.keySet()){
            heap.add(key);
            if(heap.size()>k){
                heap.poll();
            }
        }
        int[] res = new int[k];
        for(int i=0;i<k;i++){
            res[i] = heap.poll();
        }
        return res;
    }
}