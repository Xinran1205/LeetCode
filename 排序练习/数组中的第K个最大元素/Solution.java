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
        return quickSort(nums,k,0,nums.length-1);
    }
    public int quickSort(int[] nums, int k, int left, int right){
        if(left==right){
            return nums[left];
        }
        int pivotIndex = left + (int)(Math.random() * (right - left + 1));
        int pivot = nums[pivotIndex];
        nums[pivotIndex] = nums[left];

        int i = left;
        int j = right;
        int index = i+1;
        while(index<=j){
            if(nums[index]==pivot){
                index++;
            }else if(nums[index]<pivot){
                int tmp = nums[index];
                nums[index] = nums[i];
                nums[i] = tmp;
                index++;
                i++;
            }else{
                int tmp = nums[index];
                nums[index] = nums[j];
                nums[j] = tmp;
                j--;
            }
        }
        if(i<=nums.length-k&&nums.length-k<=j){
            return pivot;
        }
        if(index>nums.length-k){
            return quickSort(nums, k, left, i - 1);
        }else{
            return quickSort(nums, k, j + 1, right);
        }
    }
}