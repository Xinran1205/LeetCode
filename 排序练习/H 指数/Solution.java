import java.util.Arrays;

class Solution {

// 看当前最大的未检查论文的引用次数 citations[i] 是否 严格大于 h。
// 如果 citations[i] > h，说明我们又找到了一篇“至少被引用 h+1 次”（因为 h + 1 ≤ citations[i]）的论文，于是 h++。
// 否则（citations[i] ≤ h），就无法再继续增大 h 了——因为剩下的论文引用次数更小，不可能满足更高的引用阈值。
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        int i = citations.length-1;
        while(i>=0&&citations[i]>h){
            h++;
            i--;
        }
        return h;
    }
}

// 这个方法更好！
class Solution2 {
    public int hIndex(int[] citations) {
        int n = citations.length, tot = 0;
        // 排序数组
        // 里面的值代表每个引用次数有几个
        // 例如counter[2] = 3代表引用2次的论文有3篇
        int[] counter = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n) {
                counter[n]++;
            } else {
                counter[citations[i]]++;
            }
        }
        // 维护一个累积变量 tot，让它始终表示「引用次数 ≥ 当前 i」的论文总数。
        for (int i = n; i >= 0; i--) {
            tot += counter[i];
            if (tot >= i) {
                return i;
            }
        }
        return 0;
    }
}