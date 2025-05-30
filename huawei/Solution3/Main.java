import java.util.*;

/**
 * 最小化各段樱桃数量标准差
 * 输入：
 *   n m
 *   a0 a1 ... a(n-1)
 * 输出：
 *   b0 b1 ... b(m-1)
 */

// 把 n 个数分成 m 段
// 使得各段的和的标准差最小
public class Main {

    static int n, m;
    static int[] prefix;          // 前缀和
    // 用来存储结果
    static int[] result;           // 最优解
    static double bestStd = Double.POSITIVE_INFINITY;

    // 当前的分割方案
    static int[] currentPlan;

    // 第 k 段（k 从 0 开始）里所有 A[i] 之和
    // 这个和上面含义类似，
    // currentPlan[k] 存的是这一个区间的长度
    // curW[k] 存的是这个区间的和
    static double[] curW;

    // 区间和 [l, r)
    // 不包含 r
    static int sum(int l, int r) {
        return prefix[r] - prefix[l];
    }

    static void dfs(int pos, int k) {
        // k  已经完成的段数  (0..m-1)
        if (k == m - 1) {                 // 只剩最后一段
            int len = n - pos;            // 长度固定
            currentPlan[k] = len;
            curW[k] = sum(pos, n);

            evaluateSolution();           // 计算 σ 并更新
            return;
        }

        // 剩余元素数
        int remain = n - pos;
        int remainSeg = m - k;

        // 可行性剪枝：每段至少 1，当前段最多能取的长度如下
        int maxLen = remain - (remainSeg - 1);
        for (int len = 1; len <= maxLen; len++) {
            currentPlan[k] = len;
            curW[k] = sum(pos, pos + len);

            dfs(pos + len, k + 1);
            // 回溯：这里不需要显式 pop，因为 curB[k]、curW[k] 会被下一次覆盖
        }
    }

    // 计算标准差，若更优则记录
    static void evaluateSolution() {
        double mean = 0;
        for (int i = 0; i < m; i++) mean += curW[i];
        mean /= m;

        double var = 0;
        for (int i = 0; i < m; i++) {
            double diff = curW[i] - mean;
            var += diff * diff;
        }
        var /= m;

        double std = Math.sqrt(var);
        if (std < bestStd - 1e-9) {           // EPS 防浮点噪声
            bestStd = std;
            result = Arrays.copyOf(currentPlan, m);   // 记录方案
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) return;
        n = sc.nextInt();
        m = sc.nextInt();

        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }

        // 前缀和
        prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + A[i];
        }

        // 用来存储结果
        result = new int[m];
        // 当前的分割方案
        currentPlan  = new int[m];
        curW  = new double[m];

        dfs(0, 0);

        // 输出
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (i > 0) sb.append(' ');
            sb.append(result[i]);
        }
        System.out.println(sb);
    }
}
