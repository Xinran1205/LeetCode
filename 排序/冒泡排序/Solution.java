import java.util.*;

public class Solution {

    public static int[] bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    // 这个IO的使用很典型！！！
    // https://blog.csdn.net/weixin_44299027/article/details/113715041
    // 这个链接详细说了/t制表符的使用
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        String[] items = input.split(",");
        int[] array = new int[items.length];
        for (int i = 0; i < items.length; i++) {
            array[i] = Integer.parseInt(items[i].trim());
        }

        array = bubbleSort(array);

        StringBuilder result = new StringBuilder();
        for (int num : array) {
            result.append(num).append("\t");
        }

        System.out.println(result.toString());
    }
}
