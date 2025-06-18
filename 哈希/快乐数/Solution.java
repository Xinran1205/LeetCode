import java.util.*;
class Solution {
    public boolean isHappy(int n) {
        // 这个可以用Floyd判环来做
        //每次记录
        HashSet<Integer> hashSet = new HashSet<>();
        while(!hashSet.contains(n)){
            hashSet.add(n);
            if(n==1){
                return true;
            }
            //取出每个数，计算新数，放进hashSet
            int val = n;
            int sum = 0;
            while(val>0){
                int tmp = val%10;
                sum+=tmp*tmp;
                val = val/10;
            }
            n = sum;
        }
        return false;
    }
}