import java.util.*;

// 这个又是我写的孬方法 哈哈哈哈
// 可以不用list，直接再来一个set就可以，因为set不允许重复，所以直接add就可以了
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> ret = new ArrayList<>();
        Set<Integer> hashSet = new HashSet<>();
        for(int i=0;i<nums1.length;i++){
            hashSet.add(nums1[i]);
        }
        Arrays.sort(nums2);
        for(int i=0;i<nums2.length;i++){
            if(hashSet.contains(nums2[i])){
                ret.add(nums2[i]);
            }
            while(i<nums2.length-1&&nums2[i]==nums2[i+1]){
                i++;
            }
        }
        return ret.stream().mapToInt(Integer::intValue).toArray();
    }
}

// 快了很多！！！
class Solution2 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> ret = new HashSet<>();
        Set<Integer> hashSet = new HashSet<>();
        for(int i=0;i<nums1.length;i++){
            hashSet.add(nums1[i]);
        }
        for(int i=0;i<nums2.length;i++){
            if(hashSet.contains(nums2[i])){
                ret.add(nums2[i]);
            }
        }
        int[] result = new int[ret.size()];
        int i = 0;
        for(int num : ret){
            result[i++] = num;
        }
        return result;
    }
}