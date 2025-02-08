// 正整数x，希望删除x中的一些数位，使得x变成5的倍数，最少要删除多少个数位？
// 每个测试文件包含多个测试点，第一行输入一个整数代表数据组数。
// 每组测试数据描述：每一个测试点，输出其代表的最少删除位数.
// 例子：3 154 100 1。输出：1，0，1

import java.util.Scanner;



public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while(n-- > 0) {
            String s = sc.next();
            int len = s.length();
            int i = len-1;
            for(; i >= 0; i--) {
                if((s.charAt(i) - '0') % 5 == 0) {
                    break;
                }
            }
            if(i==-1) {
                System.out.println(len);
            } else {
                System.out.println(len-i-1);
            }
        }
    }
}
