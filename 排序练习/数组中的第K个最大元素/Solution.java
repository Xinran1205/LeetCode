class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums,k,0,nums.length-1);
    }
    public int quickSort(int[] nums,int k,int left,int right){
// left == right：子数组中只有一个元素，这个元素就是当前子数组中的唯一候选，不需要进一步分割，直接返回该元素。
// left > right：虽然在这个具体实现中，left > right 的情况通常不会发生，但作为一个安全检查，可以防止在某些边界情况下出现错误。
        if(left<=right){
            int pivot = partition(nums,left,right);
            if(k==nums.length-pivot){
                return nums[pivot];
            }
            if(k<nums.length-pivot){
                return quickSort(nums,k,pivot+1,right);
            }else{
                return quickSort(nums,k,left,pivot-1);
            }
        }
        return -1;
    }
    public int partition(int[] nums,int left,int right){
        int pivot = nums[left];
        int l = left+1;
        int r = right;
        while(l<=r){
            while(l<=r&&nums[l]<pivot){
                l++;
            }
            while(l<=r&&nums[r]>pivot){
                r--;
            }
            if(l<=r){
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
                l++;
                r--;
            }
        }
        nums[left] = nums[r];
        nums[r] = pivot;
        return r;
    }
}


// 这个更吊！使用三项切分的快速排序
class Solution2 {
    public int findKthLargest(int[] nums, int k) {
        //快速排序
        return quickSort(nums,0,nums.length-1,k);
    }

    public int quickSort(int[] nums,int left,int right,int k){
        int l = left;
        int r = right;
        if(l==r){
            return nums[l];
        }
        //三项切分
        int pivotIndex = l + (int)(Math.random() * (r - l + 1));
        int tmp = nums[l];
        nums[l] = nums[pivotIndex];
        nums[pivotIndex] = tmp;
        int pivot = nums[l];
        int index = l;
        while(index<=r){
            if(nums[index]==pivot){
                index++;
            }else if(nums[index]<pivot){
                int tmp2 = nums[index];
                nums[index] = nums[l];
                nums[l] = tmp2;
                l++;
                index++;
            }else{
                int tmp2 = nums[index];
                nums[index] = nums[r];
                nums[r] = tmp2;
                r--;
            }
        }
        //划分完毕后，如果目标下标 target = nums.length - k 落在 [l, r] 内，就可以直接返回 pivot（或区间内任一元素）；否则：
// - 如果 target > r，说明目标在右侧，则递归处理右区间；
// - 如果 target < l，说明目标在左侧，则递归处理左区间。
        if(l<=nums.length-k&&r>=nums.length-k){
            return nums[l];
        }else if(r<nums.length-k){
            return quickSort(nums,r+1,right,k);
        }else{
            return quickSort(nums,left,l-1,k);
        }
    }
}


// 堆排序，比较慢
class Solution3 {
    public int findKthLargest(int[] nums, int k) {
        // 堆排序练习
        buildHeap(nums);
        for(int i=0;i<k-1;i++){
            nums[0] = Integer.MIN_VALUE;
            heapyfy(nums,0);
        }
        return nums[0];
    }
    public void buildHeap(int[] nums){
        //从第一个非叶子节点开始
        for(int i=nums.length/2-1;i>=0;i--){
            heapyfy(nums,i);
        }
    }
    public void heapyfy(int[] nums,int curIndex){
        int maxIndex = curIndex;
        int leftChild = 2*curIndex+1;
        int rightChild = 2*curIndex+2;
        if(leftChild<nums.length&&nums[leftChild]>nums[curIndex]){
            maxIndex = leftChild;
        }
        if(rightChild<nums.length&&nums[rightChild]>nums[maxIndex]){
            maxIndex = rightChild;
        }
        if(maxIndex!=curIndex){
            int tmp = nums[maxIndex];
            nums[maxIndex] = nums[curIndex];
            nums[curIndex] = tmp;
            heapyfy(nums,maxIndex);
        }
    }
}