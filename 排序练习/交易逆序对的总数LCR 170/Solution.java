import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

//两种方法1.归并排序2.树状数组

// 建议使用树状数组，和逆序对那一题几乎完全一样，我一编过了！
class Solution3 {
    public List<Integer> countSmaller(int[] nums) {
        int[] tmp = new int[nums.length];
        System.arraycopy(nums, 0, tmp, 0, nums.length);
        Arrays.sort(tmp);
        for(int i=0;i<nums.length;i++){
            nums[i] = Arrays.binarySearch(tmp, nums[i]) + 1;
        }
        bitTree bitarr = new bitTree(nums.length);
        int [] ret = new int[nums.length];
        for(int i = nums.length-1;i>=0;i--){
            ret[i] = bitarr.query(nums[i]-1);
            bitarr.update(nums[i]);
        }
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
    bitTree(int n){
        this.bitArr = new int[n+1];
        this.size = n;
    }

    public int query(int val){
        int sum = 0;
        while(val>=1){
            sum = sum + bitArr[val];
            val = val-(val&-val);
        }
        return sum;
    }

    public void update(int val){
        while(val<=size){
            bitArr[val] = bitArr[val] + 1;
            val = val +(val&-val);
        }
    }
}


class Solution {
    public int reversePairs(int[] record) {
        //归并排序
        int sum = mergeSort(record,0,record.length-1);
        return sum;
    }

    public int mergeSort(int[] nums,int left,int right){
        if(left>=right){
            return 0;
        }
        int mid = (right-left)/2+left;
        int leftSum = mergeSort(nums,left,mid);
        int rightSum = mergeSort(nums,mid+1,right);
        int sum = leftSum+rightSum;

        int[] helpArr = new int[right-left+1];
        int i = left;
        int j = mid+1;
        int index = 0;
        while(i<=mid&&j<=right){
            if(nums[i]>nums[j]){
                sum = sum + (mid-i+1);
                helpArr[index] = nums[j];
                j++;
                index++;
            }else{
                helpArr[index] = nums[i];
                i++;
                index++;
            }
        }
        while(i<=mid){
            helpArr[index] = nums[i];
            i++;
            index++;
        }
        while(j<=right){
            helpArr[index] = nums[j];
            j++;
            index++;
        }

        //赋值回去
        for(int p=left;p<=right;p++){
            nums[p] = helpArr[p-left];
        }
        return sum;
    }

}

//方法2
class Solution2 {
    public int reversePairs(int[] record) {
        int n = record.length;
        int[] tmp = new int[n];
        System.arraycopy(record, 0, tmp, 0, n);
        // 离散化
        Arrays.sort(tmp);
        for (int i = 0; i < n; ++i) {
            // record[i]是否在数组tmp中，若在返回下标
            record[i] = Arrays.binarySearch(tmp, record[i]) + 1;
        }
        // 初始化树状数组
        BIT bit = new BIT(n);
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            //查询比当前元素 record[i] 小的元素数量，

            //我们的关键就是统计这个元素当前有多少个元素排名比他小，所以可以思考成线段数组的下标代表排名，值代表数量

            //所以想法就是树状数组从右（最高的）往左查，每次移动lowbit，树状数组每一位代表小于等于这个元素的数量
            //我们必须要从右开始处理数组，先查再加。因为我们题目想要的只是右边比他小的
            ans += bit.query(record[i] - 1);
            bit.update(record[i]);
        }
        return ans;
    }
}

class BIT {
    private int[] tree;
    private int n;

    public BIT(int n) {
        this.n = n;
        this.tree = new int[n + 1];
    }

    public static int lowbit(int x) {
        return x & (-x);
    }

    public int query(int x) {
        int ret = 0;
        while (x != 0) {
            ret += tree[x];
            x -= lowbit(x);
        }
        return ret;
    }

    public void update(int x) {
        while (x <= n) {
            ++tree[x];
            x += lowbit(x);
        }
    }
}