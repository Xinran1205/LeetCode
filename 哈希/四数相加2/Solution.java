import java.util.*;

class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        // 这一题主要是要意识到不需要考虑重复，那就很好搞，不需要去重的话
        // 可以用测试用例举个重复的例子看一下就懂了。
        Map<Integer,Integer> hashMap = new HashMap<Integer,Integer>();

        for(int i=0;i<A.length;i++){
            for(int j=0;j<B.length;j++){
                hashMap.put(A[i]+B[j],hashMap.getOrDefault(A[i]+B[j],0)+1);
            }
        }
        int ret = 0;
        for(int i=0;i<C.length;i++){
            for(int j=0;j<D.length;j++){
                if(hashMap.containsKey(-C[i]-D[j])){
                    ret= ret + hashMap.get(-C[i]-D[j]);
                }
            }
        }
        return ret;
    }
}
