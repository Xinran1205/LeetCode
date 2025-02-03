import java.util.*;
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> hashMap = new HashMap<>();
        for(int i=0;i<strs.length;i++){
            String str = strs[i];
            // 这个地方转换很重要，先变成char数组，然后排序，然后再转换成String
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            if(hashMap.containsKey(key)){
                hashMap.get(key).add(str);
            }else{
                List<String> newList = new ArrayList<>();
                newList.add(str);
                hashMap.put(key,newList);
            }
        }
        return new ArrayList<List<String>>(hashMap.values());
    }
}

// 方法2，思路其实和上面几乎完全一致，只是不排序，key是自己构造的包含字符和字符个数的字符串
// 例如"abc" -> "a1b1c1"
class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> hashMap = new HashMap<>();
        for(int i=0;i<strs.length;i++){
            String str = strs[i];
            int[] count = new int[26];
            for(int j=0;j<str.length();j++){
                count[str.charAt(j)-'a']++;
            }
            StringBuffer strBuf = new StringBuffer();
            for(int j=0;j<count.length;j++){
                if(count[j]!=0){
                    strBuf.append(j+'a');
                    strBuf.append(count[j]);
                }
            }
            String key = strBuf.toString();
            if(hashMap.containsKey(key)){
                hashMap.get(key).add(str);
            }else{
                List<String> newList = new ArrayList<>();
                newList.add(str);
                hashMap.put(key,newList);
            }
        }
        return new ArrayList<List<String>>(hashMap.values());
    }
}