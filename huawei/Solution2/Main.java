import java.util.*;
public class Main {
    // 统计逆序对i<j,record[i]>record[j] 的个数，然后有个限制条件threshold
    // 归并排序，排的时候算
    // 递归统计：左对数 和右对数
    public static int solution(int[] arr, int left, int right, int threshold) {
        if (left >= right) {
            return 0;
        }
        int mid = (left + right) >> 1;
        int c1 = solution(arr,left, mid, threshold);
        int c2 = solution(arr,mid + 1, right, threshold);
        int cnt = c1 + c2;
        // 这里要计算跨越左右两边的逆序对
        // 此时左右两边已经排好序的了，升序
        // 双指针，i指向左边，j指向右边
        int j = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (j <= right && arr[i] - arr[j] > threshold) {
                //符合要求
                j++;
            }
            // 此时j之前的元素都符合要求

            // 对每个新i，都需要把之前的j都加上，所以没毛病
            cnt = cnt + j - (mid + 1);
        }
        merge(arr, left, mid, right);
        return cnt;
    }
    public static void merge(int[] arr, int left, int m, int right) {
        int l = left;
        int r = m + 1;
        int index = left;
        int[] arrCopy = new int[arr.length];
        while (l <= m && r <= right) {
            if(arr[l] <= arr[r]){
                arrCopy[index] = arr[l++];
            }else{
                arrCopy[index] = arr[r++];
            }
            index++;
        }
        while (l <= m) {
            arrCopy[index] = arr[l];
            index++;
            l++;
        }
        while (r <= right) {
            arrCopy[index] = arr[r];
            index++;
            r++;
        }
        System.arraycopy(arrCopy, left, arr, left, right - left + 1);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int threshold = in.nextInt();
        int size = in.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = in.nextInt();
        }
        int ret = solution(arr,0, size - 1, threshold);
        System.out.println(ret);
    }
}



public class Main2 {
    // 统计逆序对 i<j 且 arr[i] - arr[j] > threshold 的个数
    // 归并排序：在合并阶段直接计数
    public static int solution(int[] arr, int left, int right, int threshold) {
        if (left >= right) {
            return 0;
        }
        int mid = (left + right) >> 1;
        // 递归统计左半区和右半区
        int c1 = solution(arr, left,  mid, threshold);
        int c2 = solution(arr, mid+1, right, threshold);
        int cnt = c1 + c2;

        // 合并并计数：aux 暂存 merge 结果
        int[] aux = new int[right - left + 1];
        int i = left, j = mid + 1, idx = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                // 左边小，直接取左边
                aux[idx++] = arr[i++];
            } else {
                // arr[i] > arr[j]，此时合并要取右边，同时可能产生阈值逆序
                // 若 arr[i] - arr[j] > threshold，则对所有 p ∈ [i..mid] 都成立
                if (arr[i] - arr[j] > threshold) {
                    cnt += (mid - i + 1);
                }
                aux[idx++] = arr[j++];
            }
        }
        // 处理残余
        while (i <= mid)  {
            aux[idx++] = arr[i++];
        }
        while (j <= right) {
            aux[idx++] = arr[j++];
        }
        // 拷贝回原数组
        System.arraycopy(aux, 0, arr, left, aux.length);

        return cnt;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int threshold = in.nextInt();
        int size      = in.nextInt();
        int[] arr     = new int[size];
        for (int k = 0; k < size; k++) {
            arr[k] = in.nextInt();
        }
        int ret = solution(arr, 0, size - 1, threshold);
        System.out.println(ret);
    }
}


public class Main3 {
    public long countThresholdPairs(int[] arr, int threshold) {
        int n = arr.length;
        // 1. 离散化
        long[] all = new long[n * 2];
        for (int i = 0; i < n; i++) {
            all[i] = arr[i];
            all[i + n] = (long) arr[i] + threshold;
        }
        Arrays.sort(all);
        int m = 0;
        // 去重
        for (int i = 0; i < all.length; i++) {
            if (i == 0 || all[i] != all[i - 1]) {
                all[m++] = all[i];
            }
        }
        // rank 函数：返回 val 在 [0..m-1] 中的下标，再 +1 变成 1..m
        Map<Long, Integer> rank = new HashMap<>();
        for (int i = 0; i < m; i++) {
            rank.put(all[i], i + 1);
        }

        // 2. 初始化 BIT
        Fenwick bit = new Fenwick(m);

        // 3. 遍历并统计
        long ans = 0;
        for (int x : arr) {
            int posPlus = rank.get((long) x + threshold);
            // 查询所有已插入元素中 rank > posPlus
            ans += bit.query(m) - bit.query(posPlus);
            // 插入当前 x
            bit.update(rank.get((long) x), 1);
        }
        return ans;
    }

    // 经典 Fenwick / BIT 实现
    class Fenwick {
        int[] tree;
        int n;

        public Fenwick(int size) {
            n = size;
            tree = new int[n + 1];
        }

        private int lowbit(int x) {
            return x & -x;
        }

        // 把 idx 位置加上 v
        public void update(int idx, int v) {
            while (idx <= n) {
                tree[idx] += v;
                idx += lowbit(idx);
            }
        }

        // 求 [1..idx] 的前缀和
        public int query(int idx) {
            int s = 0;
            while (idx > 0) {
                s += tree[idx];
                idx -= lowbit(idx);
            }
            return s;
        }
    }
}
