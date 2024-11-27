import java.util.*;

class Solution {
    public List<Integer> partitionLabels(String s) {
        // 一遍遍历构建哈希表
        // 这一题关键思路是要想到记录每个字符最后出现的位置
        HashMap<Character,Integer> hash = new HashMap<Character,Integer>();
        for(int i=0;i<s.length();i++){
            hash.put(s.charAt(i),i);
        }
        List<Integer> ret = new ArrayList<>();
        int startIndex = 0;
        int endIndex = hash.get(0);
        // 判断startIndex到endIndex之间的数，他们最后出现的距离是不是小于endIndex
        // 如果都小于则这是一个区间，加入ret，更新start和end。
        // 如果有大于的，更新endIndex；

        // 以上是初步逻辑思路，不完善
        // 应该改为下面动态的思路，每次在startIndex到endIndex之间的数，都要求max，endIndex
        for(int i=0;i<s.length();i++){
            if(i>endIndex){
                ret.add(endIndex-startIndex+1);
                startIndex = i;
                endIndex = hash.get(s.charAt(i));
            }else{
                endIndex = Math.max(endIndex,hash.get(s.charAt(i)));
            }
        }
        // 这里把最后一个不要忘了
        ret.add(endIndex-startIndex+1);
        return ret;
    }
}

// 上面也可以改写成这样，这样就不需要单独处理最后一个了

// 而且这个思路更清晰！！！以后做这种题，要先在纸上画一画，找到规律，再写代码
class Solution2 {
    public List<Integer> partitionLabels(String s) {
        HashMap<Character,Integer> hash = new HashMap<Character,Integer>();
        for(int i=0;i<s.length();i++){
            hash.put(s.charAt(i),i);
        }
        List<Integer> ret = new ArrayList<>();
        int startIndex = 0;
        int endIndex = hash.get(s.charAt(0));

        for(int i=0;i<s.length();i++){
            endIndex = Math.max(endIndex,hash.get(s.charAt(i)));
            // 直到遍历到最远区间
            if(i==endIndex){
                ret.add(endIndex-startIndex+1);
                startIndex = i+1;
            }
        }
        return ret;
    }
}

// 究极优化，别用哈希表，用数组就可以
class Solution3 {
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }
}
