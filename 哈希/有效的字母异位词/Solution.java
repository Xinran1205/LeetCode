import java.util.*;

// 这个方法非常孬，构建两次哈希，然后比较两个哈希表是否相等
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        // 遍历一遍
        Map<Character,Integer> hashS = new HashMap<>();
        Map<Character,Integer> hashT = new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(!hashS.containsKey(s.charAt(i))){
                hashS.put(s.charAt(i),1);
            }else{
                hashS.put(s.charAt(i),hashS.get(s.charAt(i))+1);
            }

            if(!hashT.containsKey(t.charAt(i))){
                hashT.put(t.charAt(i),1);
            }else{
                hashT.put(t.charAt(i),hashT.get(t.charAt(i))+1);
            }
        }
        // 这里直接比较两个哈希表是否相等，不要遍历
        return hashS.equals(hashT);
    }
}

//这个和上面一样，就是用了一个新包，代码好看了一些
class Solution2 {
    public boolean isAnagram(String s, String t) {
        // 如果长度不同，直接返回 false
        if (s.length() != t.length()) {
            return false;
        }

        // 预设初始容量（假设字符集较小，比如26个字母）
        Map<Character, Integer> hashS = new HashMap<>(26);
        Map<Character, Integer> hashT = new HashMap<>(26);

        // 遍历字符串，使用 getOrDefault 简化逻辑
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            hashS.put(c1, hashS.getOrDefault(c1, 0) + 1);

            char c2 = t.charAt(i);
            hashT.put(c2, hashT.getOrDefault(c2, 0) + 1);
        }

        // 直接比较两个 HashMap 是否相等
        return hashS.equals(hashT);
    }
}

// 唯一真神！！！ 用数组代替哈希表，速度最快

class Solution3 {
    public boolean isAnagram(String s, String t) {
        int[] hashTable = new int[26];
        if(s.length()!=t.length()){
            return false;
        }
        for(int i=0;i<s.length();i++){
            int index1 = s.charAt(i)-'a';
            int index2 = t.charAt(i)-'a';
            hashTable[index1]++;
            hashTable[index2]--;
        }
        for(int i=0;i<hashTable.length;i++){
            if(hashTable[i]!=0){
                return false;
            }
        }
        return true;
    }
}