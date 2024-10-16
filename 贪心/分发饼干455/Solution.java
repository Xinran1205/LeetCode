import java.util.Arrays;


class Solution {
    public int findContentChildren(int[] g, int[] s) {
        // g数组的长度是小孩的个数
        // 给g排序，给s排序
        Arrays.sort(g);
        Arrays.sort(s);

        int i=0;
        int j=0;
        int sum = 0;
        while(i<g.length&&j<s.length){
            if(s[j]>=g[i]){
                sum++;
                i++;
                j++;
            }else{
                j++;
            }
        }

        return sum;
    }
}