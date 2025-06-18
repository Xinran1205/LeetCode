import java.util.*;

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        //hashMap记录magazine每个单词次数
        HashMap<Character,Integer> hashMap = new HashMap<>();
        for(int i=0;i<magazine.length();i++){
            hashMap.put(magazine.charAt(i),hashMap.getOrDefault(magazine.charAt(i),0)+1);
        }
        for(int i=0;i<ransomNote.length();i++){
            Character a = ransomNote.charAt(i);
            if(!hashMap.containsKey(a)||hashMap.get(a)<=0){
                return false;
            }
            hashMap.put(a,hashMap.get(a)-1);
        }
        return true;
    }
}

// 上面方法有点孬
// 不用哈希 用26个字母的数组统计频率，思路是一样的！
class Solution2 {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnt[c - 'a']--;
            if(cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}