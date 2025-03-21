import java.util.*;
// 这道题需要仔细思考，用到滑动窗口的思想
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> hashMap = new HashMap<>();
        int ret = 0;
        int left = 0;
        for(int i=0;i<s.length();i++){
            if(hashMap.containsKey(s.charAt(i))){
                // 这里left取max要注意，因为每次做这一步，都需要抛弃left左边所有的元素，所以需要取max
                left = Math.max(left,hashMap.get(s.charAt(i))+1);
            }
            hashMap.put(s.charAt(i),i);
            ret = Math.max(ret,i-left+1);
        }
        return ret;
    }


    public int lengthOfLongestSubstring2(String s) {
        // 滑动窗口
        int max = 0;
        int left = 0;
        Map<Character,Integer> hashMap = new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(hashMap.containsKey(s.charAt(i))&&hashMap.get(s.charAt(i))>=left){
                left = hashMap.get(s.charAt(i))+1;
            }else{
                max = Math.max(max,i-left+1);
            }
            hashMap.put(s.charAt(i),i);
        }
        return max;
    }
}