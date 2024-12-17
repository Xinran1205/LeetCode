class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int size = s.length();
        for(int i=0;i<size;i++){
            int suffixSize = i+1;
            if(size%suffixSize!=0){
                continue;
            }
            int j=i+1;
            for(;j<size;j++){
                if(s.charAt(j)!=s.charAt(j-suffixSize)){
                    break;
                }
                if(j==size-1){
                    return true;
                }
            }
        }
        return false;
    }
}