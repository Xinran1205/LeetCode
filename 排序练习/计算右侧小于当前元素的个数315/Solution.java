
import java.util.ArrayList;
import java.util.List;


//以下两个都是归并排序的方法，几乎完全一样
// 这个方法是没有优化的归并排序
// 有一个代码很关键，就是拷贝别用for循环，用System.arraycopy方法！！！
class Solution {
    private int[] ret;
    private int[] indexes;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        ret = new int[n];
        indexes = new int[n];

        // 初始化索引数组
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }

        mergeSort(nums, 0, n - 1);

        // 将结果转换为列表
        List<Integer> list = new ArrayList<>();
        for (int count : ret) {
            list.add(count);
        }
        return list;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (right - left) / 2 + left;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] tmpIndexes = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            // 这一部分是我根据交易逆序对的思路写的，但是超时了
            if (nums[indexes[j]] < nums[indexes[i]]) {
                tmpIndexes[k++] = indexes[j++];
                for(int p =i;p<=mid;p++){
                    ret[indexes[p]] += 1;
                }
            } else {
                tmpIndexes[k++] = indexes[i++];
            }
        }

        while (i <= mid) {
            tmpIndexes[k++] = indexes[i++];
        }

        while (j <= right) {
            tmpIndexes[k++] = indexes[j++];
        }

        // 将合并后的索引更新回原索引数组
        System.arraycopy(tmpIndexes, 0, indexes, left, tmpIndexes.length);
    }
}

// 这个是优化过循环的
class Solution2 {
    private int[] ret;
    private int[] indexes;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        ret = new int[n];
        indexes = new int[n];
        // 使用一个类似二级索引
        // indexes数组存的是元素在nums原数组中的位置
        // nums数组不动，排序index数组，也就是排序索引
        // 比如index数组初始化是 0 1 2 3， 排序完可能是 2 1 0 3

        // 初始化索引数组
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }

        mergeSort(nums, 0, n - 1);

        // 将结果转换为列表
        List<Integer> list = new ArrayList<>();
        for (int count : ret) {
            list.add(count);
        }
        return list;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (right - left) / 2 + left;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] tmpIndexes = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        int rightNumber = 0;

        while (i <= mid && j <= right) {
            if (nums[indexes[j]] < nums[indexes[i]]) {
                tmpIndexes[k++] = indexes[j++];
                rightNumber++;
            } else {
                ret[indexes[i]] +=rightNumber;
                tmpIndexes[k++] = indexes[i++];
            }
        }

        while (i <= mid) {
            ret[indexes[i]] +=rightNumber;
            tmpIndexes[k++] = indexes[i++];
        }

        while (j <= right) {
            tmpIndexes[k++] = indexes[j++];
        }

        // 将合并后的索引更新回原索引数组
        System.arraycopy(tmpIndexes, 0, indexes, left, tmpIndexes.length);
    }
}

