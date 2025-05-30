class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 如果我们要求中位数，当两个数组总长度为奇数时，
        // 中位数就是第 (m+n+1)/2 小的数；当为偶数时，
        // 通常需要找到第 (m+n)/2 小和第 (m+n)/2+1 小的两个数，然后求平均值。
        int n = nums1.length;
        int m = nums2.length;
        // left代表靠左边的数
        int left = (n + m + 1) / 2;
        // right代表靠右边的数
        // 如果总数是奇数，那么left和right是一样的
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        // 我们等于求第left小的数和第right小的数。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) +
                getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        // 让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        // 这个感觉有点搞笑，不如直接两个if判断
        // if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if(len2==0){
            return nums1[start1+k-1];
        }
        if(len1==0){
            return nums2[start2 + k - 1];
        }

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        // Math.min(len1, key / 2) 的作用是：
        // 如果 nums1 剩下的元素（len1）少于 key/2 个，就用 len1 作为候选数目；
        // 否则就取 key/2。这保证了在计算候选位置时不会超过数组边界。
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
}

// 究极优化，数学方法！划分数组

//划分数组思路概述
//这个方法的核心思想是：把两个排序数组的所有元素划分为“左半部分”和“右半部分”，并且满足以下两个条件：
// 1. 左半部分和右半部分包含的元素总数相等（如果 m + n 为偶数），或者左半部分比右半部分多一个元素（如果 m + n 为奇数）。
//
//换句话说，当 (m + n) 是偶数时，左边有 (m + n) / 2 个元素，右边也有 (m + n) / 2 个元素；
//当 (m + n) 是奇数时，左边有 (m + n + 1) / 2 个元素，右边有 (m + n - 1) / 2 个元素。
//
// 2. 左半部分的所有元素都小于等于右半部分的所有元素。
//
//一旦满足上面这两个条件：
//
//        - 当 (m + n) 为奇数时，中位数就是左半部分的最大值（因为左边比右边多一个元素）。
//        - 当 (m + n) 为偶数时，中位数就是左半部分最大值和右半部分最小值的平均数。


//如何进行“划分”
//我们可以把两个数组分别记为：
//
//A[0..m-1]（长度为 m）
//B[0..n-1]（长度为 n）
//我们先“假设”只对其中一个数组做一个切分，然后依据需要再对另一个数组做对应的切分。
//
//步骤 1：固定左半部分的总长度
//设 leftTotal = (m + n + 1) // 2（这里的 // 表示整除，即向下取整）。
//
//如果 m + n 是奇数，(m + n + 1)//2 就是左半部分多的那个元素数；
//如果 m + n 是偶数，(m + n + 1)//2 跟 (m + n)//2 一样的结果。
//这个 leftTotal 表示左半部分需要包含的所有元素的个数。
//
//步骤 2：二分查找划分位置
//我们只在数组 A 上做一个“划分位置” i，其取值范围是从 0 到 m 之间；
//
//i 表示在 A 中左半部分拿了 i 个元素（A[0..i-1]），那么右半部分对应在 A 中拿的是 A[i..m-1]。
//如果 A 在左半部分取了 i 个，
// 那么 B 在左半部分就要取 leftTotal - i 个（B[0..(leftTotal - i)-1]）才能保证左半部分总元素数达到 leftTotal。
//因此对应的 j = leftTotal - i 就是 B 中左半部分拿的数量。自然，右半部分则是 B[j..n-1]。
//
//步骤 3：验证划分是否满足条件
//现在划分好后：
//
//Aleft = A[i-1] 表示数组 A 左半部分的最后一个元素（如果 i == 0，说明 A 左半部分是空的，就没有左边元素了）。
//Aright = A[i] 表示数组 A 右半部分的第一个元素（如果 i == m，则说明 A 右半部分空了）。
//Bleft = B[j-1] 表示数组 B 左半部分的最后一个元素（如果 j == 0，同理要处理空的情况）。
//Bright = B[j] 表示数组 B 右半部分的第一个元素（如果 j == n，同理要处理空的情况）。
//要保证“左半部分所有元素 ≤ 右半部分所有元素”，主要的边界条件是：
//
//Aleft <= Bright
//Bleft <= Aright
//因为：
//
//左半部分最大值其实是 max(Aleft, Bleft)
//右半部分最小值其实是 min(Aright, Bright)
//如果 Aleft > Bright，意味着 A 左半部分的最后一个元素比 B 右半部分的第一个元素还要大，
// 不符合“左 ≤ 右”的条件，说明 i 取多了（A 左边多拿了），我们应该让 i 小一点；
//如果 Bleft > Aright，意味着我们在 B 左半部分拿得太多了，也要反向调整。
//
//于是，可以用二分查找不断调整 i。
//
//如果 Aleft > Bright，就让 high = i - 1（往左找）；
//否则如果 Bleft > Aright，就让 low = i + 1（往右找）；
//否则就找到了满意的 i。
//
//步骤 4：得到中位数
//一旦找到了合适的 i，就相应能确定 j = leftTotal - i，并且满足：
//
//Aleft ≤ Bright 且 Bleft ≤ Aright
//这时左边所有元素都小于等于右边所有元素。则：
//
//若 (m + n) 为奇数：
//中位数就是左半部分最大值，也就是 max(Aleft,Bleft)。
//若 (m + n) 为偶数：
//中位数 = (max(Aleft,Bleft) + min(Aright,Bright)) / 2。
//
//当然，这里要特别注意 i = 0 或 i = m 时，Aleft 或 Aright 会“越界”的情况；
// 以及 j = 0 或 j = n 时 Bleft 或 Bright 越界的情况。通常代码里会给 Aleft 或 Bleft 在越界时赋 -∞，
// 给 Aright 或 Bright 在越界时赋 +∞，方便比较。

class Solution2 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 1. 保证 nums1 是短的那个
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int totalLen = m + n;
        // leftTotal 表示左半部分所应包含的元素个数
        // (m + n + 1) / 2 可以同时适用于奇数和偶数总长度
        // 这里的 (nums1.length + nums2.length + 1) / 2 表示左半部分应当包含的总元素个数。我们来分析一下它是如何起作用的：
        // 当总元素数为奇数时，例如总长度为 7：
        // (7 + 1) / 2 = 4
        // 这就意味着左半部分应该包含 4 个元素，而右半部分自然包含 7 - 4 = 3 个元素，也就是说左半部分比右半部分多一个元素。
        int leftTotal = (totalLen + 1) / 2;

        int low = 0, high = m;

        while (low <= high) {
            // i 在 [0..m] 中进行二分搜索
            int i = (low + high) / 2;
            int j = leftTotal - i;


            int Aleft  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int Aright = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int Bleft  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int Bright = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // 2. 显式判断：是否 Aleft/Bleft 已经与 Aright/Bright 形成正确划分？
            if (Aleft > Bright) {
                // 说明从 A 里拿的元素太多 (i 太大)
                // left 半部分里 A 的末尾居然比 B 的右半部分第一个都大 => 往左减小 i
                high = i - 1;
            } else if (Bleft > Aright) {
                // 说明从 A 里拿的元素太少 (i 太小)
                // left 半部分里 B 的末尾比 A 的右半部分第一个大 => 往右增大 i
                low = i + 1;
            } else {
                // 3. 找到正确划分
                // 这个就代表左边整个一半小于右边整个一半（仔细思考很简单！）
                // (Aleft <= Bright 并且 Bleft <= Aright)

                // 如果总长度是奇数，中位数就是左半部分的最大值
                int leftMax = Math.max(Aleft, Bleft);
                if ((m + n) % 2 == 1) {
                    return leftMax;
                }

                // 如果总长度是偶数，中位数是左半部分最大值和右半部分最小值的平均
                int rightMin = Math.min(Aright, Bright);
                return (leftMax + rightMin) / 2.0;
            }
        }
        // 理论上不会到这里
        return 0.0;
    }
}


// 双指针孬方法
class Solution3 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 双指针
        int i=0;
        int j=0;
        int len = nums2.length+nums1.length;
        int target = (len+2)/2;
        int count = 0;
        int cur = 0;
        int pre = 0;
        while(count!=target&&i<nums1.length&&j<nums2.length){
            pre = cur;
            if(nums1[i]<nums2[j]){
                cur = nums1[i];
                i++;
            }else{
                cur = nums2[j];
                j++;
            }
            count++;
        }
        while(count!=target&&i<nums1.length){
            pre = cur;
            cur = nums1[i];
            i++;
            count++;
        }
        while(count!=target&&j<nums2.length){
            pre = cur;
            cur = nums2[j];
            j++;
            count++;
        }
        if(len%2==0){
            return (double)(pre+cur)/2;
        }else{
            return cur;
        }
    }
}

