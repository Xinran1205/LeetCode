class Solution {
    public String convert(String s, int numRows) {
        if(numRows==1){
            return s;
        }
        StringBuilder[] sbArr = new StringBuilder[numRows];
        for(int i=0;i<numRows;i++){
            sbArr[i] = new StringBuilder();
        }
        int changeDir = -1;
        int row = 0;
        for(int i=0;i<s.length();i++){
            sbArr[row].append(s.charAt(i));
            if(row==numRows-1||row==0){
                changeDir = -changeDir;
            }
            row+=changeDir;
        }
        StringBuilder ret = new StringBuilder();
        for (StringBuilder sb : sbArr) {
            ret.append(sb);
        }
        return ret.toString();
    }
}