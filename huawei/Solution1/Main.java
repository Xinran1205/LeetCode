import java.util.*;
public class Main {
    public static void solution(String[] str,int m){
        // 一半选第一个，一半选第二个，之和最小
        // 默认选第一个，存储每一步如果选第二个的差值
        int[] diffArr = new int[m];
        int ret = 0;
        for(int i=0;i<m;i++){
            String[] q = str[i].split(",");
            int a = Integer.parseInt(q[0]);
            int b = Integer.parseInt(q[1]);
            ret += a;
            diffArr[i] = b-a;
        }
        // 排序差值
        // 然后选最小的一半的差值
        Arrays.sort(diffArr);
        int half = m/2;
        for(int i=0;i<half;i++){
            ret += diffArr[i];
        }
        System.out.println(ret);
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        // 读取个数。
        int m = in.nextInt();
        in.nextLine();
        // 读取这一整行
        String string = in.nextLine();
        // 先把头尾的[[和]]去掉
        // 只取中间部分
        int head = 2;
        int tail = string.length() - 2;
        string = string.substring(head, tail);
        String[] str = string.split("\\],\\[");
        solution(str, m);
    }
}