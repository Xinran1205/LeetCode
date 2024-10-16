
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

//这道题要用树状数组做，更简单


// 以下两个都是归并排序的方法，几乎完全一样
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
                // 这样就精确到第几个位置了
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

//树状数组实现！！
class Solution3 {
    public List<Integer> countSmaller(int[] nums) {
        // 从右往左找，先把nums变成排名,这几个api很重要，要记住
        int[] tmp = new int[nums.length];
        System.arraycopy(nums, 0, tmp, 0, nums.length);
        Arrays.sort(tmp);
        for (int i=0;i<nums.length;i++){
            nums[i] = Arrays.binarySearch(tmp, nums[i]) + 1;
        }

        bitTree obj = new bitTree(nums.length);
        int[] ret = new int[nums.length];
        // 必须从右往左找
        for(int i=nums.length-1;i>=0;i--){
            ret[i] = obj.query(nums[i]-1);
            obj.update(nums[i]);
        }
        // 数组转换成list，这个要记住
        List<Integer> list = new ArrayList<>(nums.length);
        for (int count : ret) {
            list.add(count);
        }
        return list;
    }
}

class bitTree{
    int[] bitArr;
    int size;

    bitTree(int size){
        this.size = size;
        bitArr = new int[size+1];
    }
    // 查出有多少个比这个排名小的数
    int query(int rank){
        int sum = 0;
        while(rank>=1){
            sum = sum + bitArr[rank];
            rank = rank - (rank&-rank);
        }
        return sum;
    }

    void update(int rank){
        while(rank<=size){
            bitArr[rank] = bitArr[rank]+1;
            rank = rank + (rank&-rank);
        }
    }
}
